package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.DATE;
import classes.LAB;
import classes.Sejours;

/**
 * Classe de lecture et construction des LAB
 * 
 * @author Kan YAO
 *
 */
public class ReadFileLAB {
	private static ArrayList<LAB> mesLAB = new ArrayList<LAB>();
		
		/**
		 * Constructeur à vide
		 **/
		public ReadFileLAB(){
			
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
	              // CrÃ©ation du flux buffrisÃ© sur un FileReader, immÃ©diatement suivi par un
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
			System.out.println("\nFichier LAB.csv lu !");
	
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
				LAB l = new LAB(Long.parseLong(temp[0]), normaliseDate(parseDate(reformDate(temp[1]))), parseCodeLibele(temp[2])[0], parseCodeLibele(temp[2])[1], temp[3], temp[4]);
				mesLAB.add(l);
				System.out.println(l.describeLAB());
								
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
		 * @return True si la ligne a été lu correctement ou false sinon
		 * 
		 **/
		private static String[] parseCodeLibele(String line) {
			String delimiterPoint = ":";
			String[] temp;
			temp = line.split(delimiterPoint);
			return temp;
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
		 * Retourne la liste des LAB du séjour courant
		 * 
		 * @param s liste séjours
		 * 
		 * @return liste des LAB du séjour
		 */
		public ArrayList<LAB> getLabFinal(ArrayList<Sejours> s){
			ArrayList<LAB> l = new ArrayList<LAB>();
			
			for(int i = 0; i < mesLAB.size(); i++){
				for(int j = 0; j < s.size();j++){
					if(mesLAB.get(i).getIdSejour() == s.get(j).getIdSejour()){
						if(!l.contains(mesLAB.get(i))){
							l.add(mesLAB.get(i));
						}
					}
				}
			}
			
			return l;
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
