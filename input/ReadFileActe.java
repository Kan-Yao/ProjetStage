package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.Actes;
import classes.CIM10;
import classes.DATE;
import classes.Sejours;

/**
 * Classe de lecture et construction des Actes
 * 
 * @author Kan YAO
 *
 */
public class ReadFileActe {
	private static ArrayList<Actes> mesActes = new ArrayList<Actes>();
	
	/**
	 * Constructeur à vide
	 **/
	public ReadFileActe(){
		
	}

	/**
	 * Cette fonction ouvre le fichier passé en argument et appelle la fonction de création du ou des SEF
	 * 
	 * @param file  fichier à  lire
	 * 
	 **/
	public void LoadFile(String file){
		 boolean ok = true;
		 
		try{
             // CrÃ©ation du flux buffÃ©risÃ© sur un FileReader, immÃ©diatement suivi par un
             // try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
             // est correctement instanciÃ© (Ã©vite les NullPointerException)
             BufferedReader buff = new BufferedReader(new FileReader(file));

             try {
                  String line;
                  // Lecture du fichier ligne par ligne. Cette boucle se termine
                  // quand la mÃ©thode retourne la valeur null.
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
		System.out.println("\nFichier actes.csv lu !");
		System.out.println(mesActes.size());
	}

	/**
	 * Parse les différentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a été lu correctement ou false sinon
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
			
			Actes c = new Actes(Long.parseLong(temp[0]), normaliseDate(parseDate(reformDate(temp[1]))), parseCode(temp[2]), parseLibele(temp[2]));
			mesActes.add(c);
			System.out.println(c.describeActes());
			
			
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
	 * @return True si la ligne a été lu correctement ou false sinon
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
	 * @return True si la ligne a été lu correctement ou false sinon
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
	 * @return True si la ligne a été lu correctement ou false sinon
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
	 * Normalise la date passée en argument
	 * 
	 * @param dat date à normaliser
	 * 
	 * @return la date normalisée
	 * @throws ParseException
	 * 
	 **/
	public static DATE normaliseDate(String dat) throws ParseException {
	   	
	   	Date date = null ;
	 	DATE d = new DATE();
	 		
	   	 if(dat.equalsIgnoreCase("00/00/0000 00:00")){
	   		 d.setTimeDate(dat);
	   		 d.setDateConcat(0000000000);
	   		 System.out.println(d.descriptionDate());
	   		 
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
	 * Crée  une liste d'acte correspondant a la liste de séjour en argument 
	 * 
	 * @param s Liste séjour
	 * 
	 * @return retourne la liste des actes correspondant aux séjours
	 * 
	 **/
	public ArrayList<Actes> getMesActes (ArrayList<Sejours> s){
		ArrayList<Actes> a = new ArrayList<Actes>();

		for(int i = 0; i < mesActes.size(); i++){
			for(int j = 0; j < s.size(); j++){
				if(mesActes.get(i).getIdSejour() == s.get(j).getIdSejour()){
					if(!a.contains(mesActes.get(i))){
						a.add(mesActes.get(i));
					}
				}
			}
		}
		return a;
	}
	
	/**
	 * Formate la date
	 * 
	 * @param dat Date à formater
	 * 
	 * @return La date formatée
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
