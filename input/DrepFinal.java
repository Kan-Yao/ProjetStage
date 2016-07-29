package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Classe de lecture et construction des différents objets
 * 
 * @author Kan YAO
 *
 */
public class DrepFinal {
	private static ArrayList<String> mesLignes = new ArrayList<String>();
	private static ArrayList<String> bioCh = new ArrayList<String>();
	private static ArrayList<String> bioHe = new ArrayList<String>();
	
	/**
	 * Constructeur à vide de la classe
	 */
	public DrepFinal(){
		
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
		System.out.println("\nFichier DrepFinal1.csv lu !");
		System.out.println("Nombre de ligne : "+mesLignes.size());
	}

	/**
	 * Parse les différentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a été lu correctement ou false sinon
	 * 
	 * @throws IOException
	 **/
	public static boolean parserLine(String line) throws IOException {
		String delimiterPoint = ",";
		String[] temp;
		String line2; 
		
		temp = line.split(delimiterPoint);
		
		if(line.contains("nda")){
			return true;
		}
		try{
			line2 = temp[0]+",";
			line2 += temp[1]+",";
			line2 += temp[2]+",";
			line2 += temp[3]+",";
			line2 += temp[4]+",";
			line2 += temp[5]+",";
			line2 += temp[6]+",";
			line2 += temp[7]+",";
			line2 += temp[8]+",";
			line2 += temp[9]+",";
			line2 += temp[10]+",";
			line2 += temp[11]+",";
			
			if(temp[12].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[12]+",";
				line2 += temp[13]+" mmol/L,";
			}
			
			if(temp[14].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[14]+",";
				line2 += temp[15]+" U/L,";
			}
			
			if(temp[16].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[16]+",";
				line2 += temp[17]+" x10*9/L,";
			}
			
			if(temp[18].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[18]+",";
				line2 += temp[19]+" g/dL,";
			}
			
			if(temp[20].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[20]+",";
				line2 += temp[21]+" mmol/L,";
			}
			
			if(temp[22].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[22]+",";
				line2 += temp[23]+" x10*9/L,";
			}
			
			if(temp[24].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[24]+",";
				line2 += temp[25]+" x10*9/L,";
			}
			
			if(temp[26].equals("0")){
				line2 += ",";
				line2 +=",";
			}else{
				line2 += temp[26]+",";
				line2 += temp[27]+" mmol/L,";
			}
			
			if(temp[28].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[28]+",";
				line2 += temp[29]+" Âµmol/L,";
			}
			
			if(temp[30].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[30]+",";
				line2 += temp[31]+" U/L,";
			}
			
			if(temp[32].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[32]+",";
				line2 += temp[33]+" U/L,";
			}
			
			if(temp[34].equals("0")){
				line2 += ",";
				line2 += ",";
			}else{
				line2 += temp[34]+",";
				line2 += temp[35]+" U/L,";
			}
			
			line2 += temp[36]+",";
			line2 += temp[37];
			
			System.out.println(line2);
			mesLignes.add(line2);
			
			return true;
			
		}catch (Exception e){
	   		 // Affichage du message d'erreur
	   		 System.out.println("\n\t!!! Erreur 1 !!!!\n Format du fichier incorrect !");
	   		 
	   		 // On quitte la boucle si y a une erreur (on arrete la lecture)
	   		 return false;
	   	 } 
	}
	
	/**
	 * Retourne la liste des ligne du fichier
	 * 
	 * @return mesLignes
	 **/
	public ArrayList<String> getLignes(){
		return mesLignes;
	}
	
	/**
	 * Ecrit les lignes dans un fichier CSV
	 * 
	 * @param lignes liste de ligne à ecrire
	 * @throws IOException
	 * 
	 **/
	public void saveLignes(ArrayList<String> lignes) throws IOException{
		PrintWriter ecrivain;
		String entete = "nda, ipp, sexe, ddn, entree, sortie, age, dcd, duhop, duserv, sta, rea, date_na, na, date_ldh, ldh, date_gb, gb, date_hb, hb, date_uree, uree, date_pla, pla, date_reticu, reticu, date_bicar, bicar, date_bi, bi, date_alat, alat, date_gama, gama, date_phos, phos, date_transfu, transfu";
		String line ="";
    	    	
	     ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter("Drepf1.csv")));
	     ecrivain.println(entete);
	     
	     for(int i = 0; i <lignes.size(); i++){
	    	 
	    	 line = lignes.get(i);
	    	 
	    	 ecrivain.println(line);
	     }
	    
	     //ecrivain.println(line);
	     ecrivain.close();
	}
	
	/**
	 * Lecteur de fichier
	 * 
	 * @param file
	 */
	public void LoadFileBioch(String file){
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
                            ok = parserLineBioCh(line);
                   }
             } finally {
                  // dans tous les cas, on ferme nos flux
                  buff.close();
             }
         } catch (IOException ioe) {
                  // erreur de fermeture des flux
                  System.out.println("Erreur --" + ioe.toString());
           }		
		System.out.println("\nFichier bioch.csv lu !");
		System.out.println("Nombre de ligne : "+bioCh.size());
	}

	/**
	 * Parse les différentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a été lu correctement ou false sinon
	 * 
	 **/
	private boolean parserLineBioCh(String line) {
		
		if(line.contains("NDA")){
			return true;
		}
		try{
			bioCh.add(line);
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
	 * Lecteur de fichier
	 * 
	 * @param file
	 */
	public void LoadFileBioHemato(String file){
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
                            ok = parserLineBioH(line);
                   }
             } finally {
                  // dans tous les cas, on ferme nos flux
                  buff.close();
             }
         } catch (IOException ioe) {
                  // erreur de fermeture des flux
                  System.out.println("Erreur --" + ioe.toString());
           }		
		System.out.println("\nFichier bioHemato.csv lu !");
		System.out.println("Nombre de ligne : "+bioHe.size());
	}

	/**
	 * Parse les différentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a été lu correctement ou false sinon
	 * 
	 **/
	private boolean parserLineBioH(String line) {
		
		if(line.contains("NDA")){
			return true;
		}
		try{
			bioHe.add(line);
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
	 * retourne la liste des résultats de biochimie
	 * 
	 * @return bioCh
	 * 
	 **/
	public ArrayList<String> getBioch(){
		return bioCh;
	}
	
	/**
	 * retourne la liste de BioHemato
	 * 
	 * @return BioHemato
	 * 
	 **/
	public ArrayList<String> getBioHemato(){
		return bioHe;
	}
	
	
	/**
	 * 
	 * @param ch liste de Biochimie
	 * @param he liste de BioHemato
	 * @throws IOException
	 * 
	 **/
	public void saveBioChHe(ArrayList<String> ch, ArrayList<String> he) throws IOException{
		PrintWriter ecrivain;
		String entete = "nda, ipp, sexe, ddn, date_pvt, na, ldh, gb, hb, uree, pla, reticu, bicar, bi, alat, gama, phos";
    	
	    ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter("BIOCHHE.csv")));
	    ecrivain.println(entete);
	     
	    for(int i = 0; i < he.size(); i++){
	    		System.out.println(he.get(i));
	    		ecrivain.println(he.get(i));
	    }
	    
	    for(int i = 0; i < ch.size(); i++){
    		System.out.println(ch.get(i));
    		ecrivain.println(ch.get(i));
    }
	     
	    ecrivain.close();
	}
}
