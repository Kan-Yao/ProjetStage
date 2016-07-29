package classes;

/**
 * Cette classe présente les résultats d'examens biologique et l'ensemble des methodes qui peuvent s'y appliquer
 * 
 * @param idSejour identifiant du séjour
 * @param datePrelev date de prélèvement
 * @param codePrelev code de l'analyse
 * @param libelePrelev libellé de l'analyse
 * @param valeurPrelev valeur du prélèvement
 * @param uniteMesure unité de mesure
 * 
 * @author Kan YAO
 *
 **/
public class LAB {
	private long idSejour;
	private DATE datePrelev;
	private String codePrelev;
	private String libelePrelev;
	private String valeurPrelev;
	private String uniteMesure;
	
	
	/**
	 * Constructeur vide de la classe
	 * 
	 **/
	public LAB(){
		
	}
	
	
	/**
	 * Constructeur de la classe avec l'ensemble de ses parametres
	 * 
	 * @param id
	 * @param dat
	 * @param codeP
	 * @param libeleP
	 * @param valeurP
	 * @param unite
	 * 
	 **/
	public LAB(long id, DATE dat, String codeP, String libeleP, String valeurP, String unite){
		idSejour = id;
		datePrelev = dat;
		codePrelev = codeP;
		libelePrelev = libeleP;
		valeurPrelev = valeurP;
		uniteMesure = unite;
	}
	
	/**
	 * Retourne l'idSejour 
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}

	/**
	 * Retourne le codePrelev
	 * 
	 * @return codePrelev
	 **/
	public String getCodePrelev(){
		return codePrelev;
	}
	
	/**
	 * Retourne la datePrelev
	 * 
	 * @return datePrelev
	 *
	 **/
	public DATE getDatePrelev(){
		return datePrelev;
	}
	
	/**
	 * Retourne la valeurPrelev
	 * 
	 * @return valeurPrelev
	 * 
	 **/
	public String getValeurPrelev(){
		return valeurPrelev;
	}
	
	/**
	 * Retourne le libelePrelev
	 * 
	 * @return libelePrelev
	 * 
	 **/
	public String getLibelePrelev(){
		return libelePrelev;
	}
	
	/**
	 * Retourne l'uniteMesure
	 * 
	 * @return l'uniteMesure
	 * 
	 **/
	public String getUnitePrelev(){
		return uniteMesure;
	}
	
	
	/**
	 * Retourne la description de l'objet courant avec l'ensemble de ses parametres
	 * 
	 * @return la description de la classe
	 * 
	 **/
	public String describeLAB(){
		return "\nIdSejour : "+idSejour+"\nDate : "+datePrelev.getDate()+"\nLibelé : "+libelePrelev+"\nValeur :"+valeurPrelev+" "+uniteMesure;
	}
}
