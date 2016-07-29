package classes;

/**
 * Cette classe modélise les patients
 * 
 * @param idPatient identifiant patient
 * @param sexePatient sexe du patient
 * @param agePatient age du patient
 * @param statutVital statut vital du patient
 * @param codePostal code postal du patient
 * 
 * @author Kan YAO
 *
 **/
public class Patient {
	private long idPatient;
	private String sexePatient;
	private int agePatient;
	private String statutVital;
	private int codePostal;
	
	/**
	 * Constructeur à vide de la classe
	 * 
	 **/
	public Patient(){
		
	}
	
	
	/**
	 * Constructeur de la classe avec uniquement l'id du patient
	 *   
	 * @param idP
	 * 
	 **/
	public Patient(long idP){
		idPatient = idP;
	}
	
	
	/**
	 * Constructeur avec l'ensemble des parametres de la classe
	 * 
	 * @param idP
	 * @param sexeP
	 * @param ageP
	 * @param statutV
	 * @param codeP
	 * 
	 **/
	public Patient(long idP, String sexeP, int ageP, String statutV, int codeP){
		idPatient = idP;
		sexePatient = sexeP;
		agePatient = ageP;
		statutVital = statutV;
		codePostal = codeP;
	}
	
	
	/**
	 * Retourne l'id du patient
	 * 
	 * @return idPatient
	 * 
	 **/
	public long getIdPatient(){
		return idPatient;
	}
	
	
	/**
	 * Retourne l'age du patient
	 * 
	 * @return agePatient
	 * 
	 **/
	public int getAge(){
		return agePatient;
	}
	
	/**
	 * Retourne le sexe du patient
	 * 
	 * @return sexePatient
	 * 
	 **/
	public String getSexPatient(){
		return sexePatient;
	}
	
	
	/**
	 * Retourne le statutVital du patient courant 
	 * 
	 * @return statutVital
	 * 
	 **/
	public String getStatutVital(){
		return statutVital;
	}
	
	
	/**
	 * Décrit le patient avec l'ensemble de ses informations
	 * 
	 * @return la description du patient
	 * 
	 **/
	public String describePatient(){
		return "\nInformations patient :\nidP : "+idPatient+"\nsexe : "+sexePatient+"\nage : "+agePatient+"\nstatutVital : "+statutVital+"\ncodePostal : "+codePostal;
	}
	
}
