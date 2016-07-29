package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import classes.Patient;
import classes.Sejours;

/**
 * Classe de lecture et construction des Patients
 * 
 * @author Kan YAO
 *
 */
public class ReadFilePatient {
	private static ArrayList<Patient> mesPatients = new ArrayList<Patient>();
	
	/**
	 * Constructeur � vide
	 **/
	public ReadFilePatient(){
		
	}
	
	/**
	 * Cette fonction ouvre le fichier pass� en argument et appelle la fonction de cr�ation du ou des SEF
	 * 
	 * @param file  fichier � lire
	 * 
	 **/
	public void LoadFile(String file){
		 boolean ok = true;
		 
		try{
             // Création du flux bufférisé sur un FileReader, immédiatement suivi par un
             // try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
             // est correctement instancié (évite les NullPointerException)
             BufferedReader buff = new BufferedReader(new FileReader(file));

             try {
                  String line;
                  // Lecture du fichier ligne par ligne. Cette boucle se termine
                  // quand la méthode retourne la valeur null.
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
		System.out.println("\nFichier patients.csv lu !");
		System.out.println(mesPatients.size()+" patients");
	}
	
	/**
	 * Parse les diff�rentes ligne du fichier
	 * 
	 * @param line
	 * 
	 * @return True si la ligne a �t� lu correctement ou false sinon
	 * 
	 * @throws IOException
	 * 
	 **/
	public static boolean parserLine(String line) throws IOException {
		String delimiterPoint = ";";
		String[] temp;
		temp = line.split(delimiterPoint);
		
		if(line.contains("IDPatient")){
			return true;
		}
		
		try{
			boolean trouve = false;
			int i = 0;
					
			Patient p = new Patient(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), temp[3], Integer.parseInt(temp[2]));
			
			while(i < mesPatients.size() && trouve == false){
				if(p.getIdPatient() == mesPatients.get(i).getIdPatient()){
					trouve = true;
					
				}else{
					trouve = false;
					i++;
				}
			}
			
			if(trouve == false){
				mesPatients.add(p);
				System.out.println(p.describePatient());
			}
			
			return true;
	
		}catch (Exception e){
	   		 // On vide les points valides (vu que le format est incorrect) le clear est peut etre
	   		 // Affichage du message d'erreur
	   		 System.out.println("\n\t!!! Erreur 1 !!!!\n Format du fichier incorrect !");
	   		 
	   		 // On quitte la boucle si y a une erreur (on arrete la lecture)
	   		 return false;
	   	 } 
	}

	/**
	 * Retourne la liste des patients � partir d'une liste de s�jour 
	 * 
	 * @param s Liste S�jour
	 * 
	 * @return liste de patient
	 **/
	public ArrayList<Patient> getPatientSejour(ArrayList<Sejours> s){
		ArrayList<Patient> p = new ArrayList<Patient>();
		for(int i = 0; i < mesPatients.size(); i++){
			for(int j = 0; j < s.size(); j++){
				if(mesPatients.get(i).getIdPatient() == s.get(j).getIdPatient()){
					if(!p.contains(mesPatients.get(i))){
						p.add(mesPatients.get(i));
					}
					
				}
			}
		}
		return p;
	}
}
