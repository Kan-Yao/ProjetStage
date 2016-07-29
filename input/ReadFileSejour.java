package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.CIM10;
import classes.DATE;
import classes.Sejours;
import classes.Service;

/**
 * Classe de lecture et construction des SÈjours
 * 
 * @author Kan YAO
 *
 */
public class ReadFileSejour {
	private static ArrayList<Sejours> mesSejours = new ArrayList<Sejours>();
	
	/**
	 * Constructeur vide de la classe Actes
	 * 
	 **/
	public ReadFileSejour(){
	
	}
	
	/**
	 * Cette fonction ouvre le fichier passÈ en argument et appelle la fonction de crÈation du ou des SEF
	 * 
	 * @param file  fichier ‡† lire
	 * 
	 **/
	public void LoadFile(String file){
		 boolean ok = true;
		 
		try{
             // Cr√©ation du flux buff√©ris√© sur un FileReader, imm√©diatement suivi par un
             // try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
             // est correctement instanci√© (√©vite les NullPointerException)
             BufferedReader buff = new BufferedReader(new FileReader(file));

             try {
                  String line;
                  // Lecture du fichier ligne par ligne. Cette boucle se termine
                  // quand la m√©thode retourne la valeur null.
                   while ((line = buff.readLine()) != null && ok) {
                            ok = parserLine(line);
                   }
             } finally {
                  // dans tous les cas, on ferme nos flux
                  buff.close();
             }
         } catch (IOException ioe) {
                  // erreur de fermeture des flux
                  System.out.println("Erreur --" + ioe.toString());
           }		
		System.out.println("\nFichier sejours.csv lu !");
		System.out.println("Nombre de sÈjours du fichier : "+mesSejours.size());

	}
	
	/**
	 * Parse la ligne en argument
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a ÈtÈ lu correctement ou false sinon
	 * 
	 **/
	public static boolean parserLine(String line) throws IOException {
		String delimiterPoint = ";";
		String[] temp;
		temp = line.split(delimiterPoint);
		
		if(line.contains("IDPatient")){
			System.out.println("Essaie");
			return true;
		}
		
		try{
			boolean trouve = false;
			int i = 0;
			ArrayList<Service> services = new ArrayList<Service>();
			ArrayList<Service> ser = new ArrayList<Service>();
			ReadFileService serv = new ReadFileService();
			
			services = serv.getServices();
			
			int j = 0;
			while(j < services.size()){
				if(Long.parseLong(temp[1]) == services.get(j).getIdSejour()){
					ser.add(services.get(j));
				}
				
				j++;
			}
			
					
			Sejours s = new Sejours(Long.parseLong(temp[0]), Long.parseLong(temp[1]), temp[2], normaliseDate(parseDate(reformDate(temp[3]))), normaliseDate(parseDate(reformDate(temp[4]))), parseDuree(temp[5]), Integer.parseInt(temp[6]), ser);
			
			while(i < mesSejours.size() && trouve == false){
				if(s.getIdSejour() == mesSejours.get(i).getIdSejour()){
					trouve = true;
					
				}else{
					trouve = false;
					i++;
				}
			}
			
			if(trouve == false){
				mesSejours.add(s);
				System.out.println(s.describeSejour());
			}
			
			return true;
	
		}catch (Exception e){
	   		 // On vide les points valides (vu que le format est incorrect) le clear est peut etre
	   		 // violent a verifier
	   		 
	   		 // Affichage du message d'erreur
	   		 System.out.println("\n\t!!! Erreur 1 !!!!\n Format du fichier incorrect !");
	   		 
	   		 // On quitte la boucle si y a une erreur (on arrete la lecture)
	   		 return false;
	   	 } 
	}
	
	/**
	 * Parse la ligne en argument
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a ÈtÈ lu correctement ou false sinon
	 * 
	 **/
	private static int parseDuree(String duree) {
		int d = 0;
		
		if(duree.length() == 0){
			d = 999999;
		}else{
			d = Integer.parseInt(duree);
		}
		return d;
			
	}

	/**
	 * Parse la ligne en argument
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a ÈtÈ lu correctement ou false sinon
	 * 
	 **/
	public static String parseDate(String dat){
			
			if(dat.length() < 2){
				dat = "00/00/0000 00:00";
				return dat;
				
			}else if(dat.length() <= 10){
				dat = dat +" 00:00";
				return dat;
			}
			
		return dat;
	}

	/**
	 * Normalise la date passÈe en argument
	 * 
	 * @param dat date ‡ normaliser
	 * 
	 * @return la date normalisÈe
	 * @throws ParseException
	 * 
	 **/
	public static DATE normaliseDate(String dat) throws ParseException {
	   	
	   	Date date = null ;
	 	DATE d = new DATE();
	 		
	   	 if(dat.equalsIgnoreCase("00/00/0000 00:00")){
	   		 d.setTimeDate(dat);
	   		 d.setDateConcat(0000000000);
	   		 
	   		 return d;
	   		 
	   	 }else{
	   	 
		    	SimpleDateFormat formatter = null ;
		 		
		 		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		 		date = formatter.parse(dat);
		 	    
		 	    d.setDATE(dat, date.getTime()/1000);
		 	    
		 	   return d;
		   }
	}
	
	public ArrayList<Sejours> getSejour(){
		return mesSejours;
	}
	
	/**
	 * Retourne la liste des sÈjours valide (Urgence -> Medecine interne, Urgence -> Medecine interne -> RÈa, Medecine Interne -> RÈa)
	 * 
	 * @return sejourValide 
	 * 
	 **/
	public ArrayList<Sejours> getSejourValide(){
		ArrayList<Sejours> sejourValide = new ArrayList<Sejours>();
				
		for(int i = 0; i < mesSejours.size(); i++){
			if(mesSejours.get(i).getService().size() != 0){
				sejourValide.add(mesSejours.get(i));
			}
		}
		
		return sejourValide;
	}
	
	/**
	 * Retourne la liste des sÈjours Invalide (Urgence -> RÈa)
	 * 
	 * @return sejourInvalide 
	 * 
	 **/
	public ArrayList<Sejours> getSejourInvalide(){
		ArrayList<Sejours> sejourInvalide = new ArrayList<Sejours>();
		
		for(int i = 0; i < mesSejours.size(); i++){
			if(mesSejours.get(i).getService().size() == 0){
				sejourInvalide.add(mesSejours.get(i));
			}
		}
		
		return sejourInvalide;
	}
	

	/**
	 * Formate la date
	 * 
	 * @param dat Date ‡ formater
	 * 
	 * @return La date formatÈe
	 *
	 **/
	public static String reformDate(String dat){
		
		if(dat.length() == 0){
			return "00/00/0000 00:00";
		}
		
		if(!dat.contains("-")){
			return dat;
		}
		String delimiterPoint1 = " ";
		String[] temp;
		String d = "";
		
		temp = dat.split(delimiterPoint1);		
		String delimiterPoint2 = "-";
		String[] tp;
		tp = temp[0].split(delimiterPoint2);
		
		if(temp.length == 2){
			
			d = tp[2]+"/"+tp[1]+"/"+tp[0]+" "+temp[1];
			
		}else{
			
			d = tp[2]+"/"+tp[1]+"/"+tp[0]+" 00:00:00";
		}
		
		return d;
	}
	
}
