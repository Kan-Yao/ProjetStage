package classes;

import java.util.ArrayList;

/**
 * Classe représentant les séjours du patient
 * 
 * @param idPatient identifiant patient
 * @param idSejour identifiant du séjour
 * @param statutVital statut vital lors du séjour
 * @param debutSejour date du début du séjour
 * @param finSejour fin date de fin du séjour
 * @param dureeSejour durée du séjour
 * @param ageSejour age durant le séjour
 * @param sevicesSejours liste des services ayant reçu le patient
 * 
 * @author Kan YAO
 *
 **/
public class Sejours {
	private long idPatient;
	private long idSejour;
	private String statutVital;
	private DATE debutSejour;
	private DATE finSejour;
	private int dureeSejour;
	private int ageSejour;
	private ArrayList<Service> sevicesSejours;
	
	
	/**
	 * Constructeur vide
	 * 
	 **/
	public Sejours(){
		
	}
	
	
	/**
	 * Constructeur avec initialisation du num&eacute;ro de patient et du num&eacute;ro de séjour
	 * 
	 * @param id_patient
	 * @param id_sejour
	 * 
	 **/
	public Sejours(long id_patient, long id_sejour){
		idPatient = id_patient;
		idSejour = id_sejour;
		sevicesSejours = new ArrayList<Service>();
	} 
	
	
	/**
	 * Constructeur avec l'initialisation de tous les &eacute;l&eacute;ments repr&eacute;sentant le s&eacute;jour
	 * 
	 * @param id_patient
	 * @param id_sejour
	 * @param statut_vital
	 * @param debut
	 * @param fin
	 * @param duree_sejour
	 * @param age
	 * @param listeService
	 * 
	 **/
	public Sejours(long id_patient, long id_sejour, String statut_vital, DATE debut, DATE fin, int duree_sejour, int age, ArrayList<Service> listeSejour){
		idPatient = id_patient;
		idSejour = id_sejour;
		statutVital = statut_vital;
		dureeSejour = duree_sejour;
		finSejour = fin;
		debutSejour = debut;
		ageSejour = age;
		sevicesSejours = new ArrayList<Service>();
		sevicesSejours = listeSejour;
	}
	
	
	/**
	 * Renvoie l'identifiant du patient
	 * 
	 * @return idPatient
	 * 
	 **/
	public long getIdPatient(){
		return idPatient;
	}
	
	
	/**
	 * Renvoie le num&eacute;ro du s&eacute;jour
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}
	
	
	/**
	 * Renvoie la dur&eacute;e du s&eacute;jour
	 * @return dureeSejour
	 */
	public int getDureeSejour(){
		return dureeSejour;
	}
	
	
	/**
	 * Renvoie la date de d&eacute;but du s&eacute;jour
	 * 
	 * @return debutSejour
	 * 
	 **/
	public DATE getDebutSejour(){
		return debutSejour;
	}
	
	
	
	/**
	 * Renvoie la date de fin de s&eacute;jour
	 * 
	 * @return finSejour
	 * 
	 **/
	public DATE getFinSejour(){
		return finSejour;
	}
	
		
	/**
	 * Renvoie le statut vital du patient
	 * @return statutVital
	 */
	public String getStatutVital(){
		return statutVital;
	}
	
	
	
	/**
	 * Renvoie l'&acirc;ge du patient durant le s&eacute;jour
	 * 
	 * @return ageSejour
	 */
	public int getAgeSejour(){
		return ageSejour;
	}
	
	
	
	/**
	 * Renvoie la liste des services parcouru par le patient
	 * 
	 * @return sevicesSejours
	 */
	public ArrayList<Service> getService(){
		return sevicesSejours;
	}
	
	
	/**
	 * Modifie l'identifiant du patient par la valeur en param&egrave;tre
	 * 
	 * @param id_patient
	 **/
	public void setIdPatient(long id_patient){
		idPatient = id_patient;
	}
	
	
	
	/**
	 * Modifie l'identifiant du s&eacute;jour par la valeur en param&egrave;tre
	 * 
	 * @param id_sejour
	 * 
	 **/
	public void setIdSejour(long id_sejour){
		idSejour = id_sejour;
	}
	
	
	
	/**
	 * Modifie la dur&eacute;e du séjour par la valeur en param&egrave;tre
	 * 
	 * @param duree
	 * 
	 **/
	public void setDureeSejour(int duree){
		dureeSejour = duree;
	}
	
	
	
	/**
	 * Modifie la date du d&eacute;bur de s&eacute;jour par la valeur en param&egrave;tre
	 * 
	 * @param debut
	 * 
	 **/
	public void setDebutSejour(DATE debut){
		debutSejour = debut;
	}
	
	
	
	/**
	 * Modifie la date de fin de sejour par la valeur en param&egrave;tre
	 * 
	 * @param fin
	 * 
	 **/
	public void setFinSejour(DATE fin){
		finSejour = fin;
	}
	
	
	
	/**
	 * Modifie le statut vital du patient par la valeur en param&egrave;tre
	 * 
	 * @param statut_vital
	 * 
	 **/
	public void setStatutVital(String statut_vital){
		statutVital = statut_vital;
	}
	
	
	
	/**
	 * Modifie l'age du patient durant le s&eacute;jour par la valeur en param&egrave;tre
	 * 
	 * @param age
	 * 
	 **/
	public void setAgeSejour(int age){
		ageSejour = age;
	}
	
	
	
	/**
	 * Modifie la liste des services par la valeur en param&egrave;tre
	 * 
	 * @param listeService
	 * 
	 **/
	public void setListeService(ArrayList<Service>listeService){
		sevicesSejours = listeService;
	}
	
	
	/**
	 * Renvoie les informations concernant le s&eacute;jour sous forme de cha&icirc;ne de caract&egrave;re
	 * 
	 * @return la description du séjour
	 * 
	 **/
	public String describeSejour(){
		return "\nInformations séjour :\nIdPatient : "+idPatient+"\nIdSejour : "+idSejour+"\nDure : "+dureeSejour+
				"\nDebut : "+debutSejour.getDate()+"\nFin : "+finSejour.getDate()+"\nStatut vital : "+statutVital+"\nAge durant le séjour : "+ageSejour+"\nServices :"+sevicesSejours.size();
				
	}
	
}
