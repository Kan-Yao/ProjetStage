package classes;

/**
 * Cette classe représente les services ayant accueilli le patient durant son séjour.
 * 
 * @param idSejour Identifiant du séjour
 * @param debutService date d'entrée dans le service
 * @param finService date de sortie
 * @param codeService code du service
 * @param libeleService libellé du service
 * @param dureeService durée dans le service
 * 
 * @author Kan YAO
 *
 **/
public class Service {
	private long idSejour;
	private DATE debutService;
	private DATE finService;
	private String codeService;
	private String libeleService;
	private double dureeService;
	
	
	/**
	 * Constructeur vide de la classe
	 **/
	public Service(){
		
	}
	
	
	/**
	 * Constructeur avec les &eacute;l&eacute;ment d&eacute;crivant la classe
	 * 
	 * @param cod
	 * @param lib
	 * 
	 **/
	public Service(String cod, String lib){
		codeService = cod;
		libeleService = lib;
	}
	
	
	/**
	 * Constructeur avec tous les variables d&eacute;finissant cette classe
	 * 
	 * @param sejour
	 * @param debut
	 * @param fin
	 * @param cod
	 * @param lib
	 * @param duree
	 * 
	 **/
	public Service(long sejour, DATE debut, DATE fin, String cod, String lib, double duree){
		idSejour = sejour;
		debutService = debut;
		finService = fin;
		codeService = cod;
		libeleService = lib;
		dureeService = duree;
		
	}
	
	/**
	 * Renvoie le num&eacute;ro de s&eacute;jour
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}
	
	
	/**
	 * Renvoie la date du d&eacute;but de prise en charge dans le service
	 * @return debutService
	 * 
	 **/
	public DATE getDebutService(){
		return debutService;
	}
	
	
	/**
	 * Renvoie la date de fin de prise en charge dans le service
	 * 
	 * @return dateFIn
	 * 
	 **/
	public DATE getFinService(){
		return finService;
	}
	
	/**
	 * Renvoie le code du service
	 * 
	 * @return code
	 * 
	 **/
	public String getCode(){
		return codeService;
	}
	
	
	/**
	 * Renvoie le lib&eacute;l&eacute; du service
	 * 
	 * @return libele
	 * 
	 **/
	public String getLibele(){
		return libeleService;
	}
	
	
	/**
	 * Remplace le code du service courant par la valeur en param&egrave;tre
	 * 
	 * @param cod
	 * 
	 **/
	public void setCode(String cod){
		codeService = cod;
	}
	
	
	/**
	 * Remplace le lib&eacute;l&eacute; du service courant par celui pass&eacute; en argument
	 * 
	 * @param lib
	 * 
	 **/
	public void setLibele(String lib){
		libeleService = lib;
	}
	
	
	/**
	 * Renvoie la dur&eacute;e de la visite dans le servce
	 * 
	 * @return dureeServce
	 * 
	 **/
	public double getDureeService(){
		return dureeService;
	}
	
	/**
	 * D&eacute;crit le service en affichant le lib&eacute;l&eacute; du service
	 * 
	 * @return Synthèse du passage du patient dans le service 
	 * 
	 **/
	public String describeService(){
		return "\nInformation service :\nidSejour : "+idSejour+"\nCode : "+ codeService +"; Libele : "+libeleService+"\nDurée dans le service : "+dureeService;
	}

}
