package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.DATE;
import classes.Sejours;
import classes.Service;

/**
 * Classe de lecture et construction des Services
 * 
 * @author Kan YAO
 *
 */
public class ReadFileService {
	private static ArrayList<Service> mesServices = new ArrayList<Service>();
	
	/**
	 * Constructeur vide de la classe Actes
	 * 
	 **/
	public ReadFileService(){
		
	}
	
	/**
	 * Cette fonction ouvre le fichier pass√© en argument et appelle la fonction de cr√©ation du ou des SEF
	 * @param file : fichier √† lire
	 */
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
		System.out.println("\nFichier services.csv lu !");

	}

	/**
	 * Parse les diffÈrentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a ÈtÈ lu correctement ou false sinon
	 * 
	 * @throws IOException
	 * 
	 **/
	public static boolean parserLine(String line) throws IOException {
		String delimiterPoint = ";";
		String[] temp;
		temp = line.split(delimiterPoint);
		
		if(line.contains("IDVisite")){
			return true;
		}
		
		try{
			
			Service s = new Service(Long.parseLong(temp[0]), normaliseDate(parseDate(reformDate(temp[1]))), normaliseDate(parseDate(reformDate(temp[2]))), parseCode(temp[3]), parseLibele(temp[3]), parseDureeService(temp[4]));
			mesServices.add(s);
			System.out.println(s.describeService());
			
			return true;
	
		}catch (Exception e){
	   		 // On vide les points valides (vu que le format est incorrect) le clear est peut etre
	   		 // violent a verifier
	   		 //TODO verifier le clear
	   		 
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
	private static String parseLibele(String line) {
		String delimiterPoint = ":";
		String[] temp;
		temp = line.split(delimiterPoint);
		return temp[1];
	}

	/**
	 * Parse la ligne en argument
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a ÈtÈ lu correctement ou false sinon
	 * 
	 **/
	private static String parseCode(String line) {
		String delimiterPoint = ":";
		String[] temp;
		temp = line.split(delimiterPoint);
		return temp[0];
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
	
	
	/**
	 * Retourne la liste des services courant 
	 * 
	 * @return mesServices
	 */
	public ArrayList<Service> getServices(){
		return mesServices;
	}
	
	
	/**
	 * Construit la liste des services par sejour
	 * 
	 * @param s Liste des sÈjours
	 * 
	 * @return Liste des servives
	 * 
	 **/
	public ArrayList<Service> getMesServices(ArrayList<Sejours> s){
		ArrayList<Service> serv = new ArrayList<Service>();
		
		for(int i = 0; i < mesServices.size(); i++){
			for(int j = 0; j < s.size();j++){
				if(mesServices.get(i).getIdSejour() == s.get(j).getIdSejour()){
					if(!serv.contains(mesServices.get(i))){
						serv.add(mesServices.get(i));
					}
				}
			}
		}
		
		return serv;
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
	
	
	public static double parseDureeService(String duree){
		double d = 0.0;
		
		if(duree.equals("undefined")){
			d = 0.0000000;
		}else{
			d = Double.parseDouble(duree);
		}
		return d;
	}
}
