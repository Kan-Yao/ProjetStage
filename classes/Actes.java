package classes;

import classes.DATE;

/**
 * Cette classe présente l'objet Actes avec les différentes méthode qui peuvent lui être appliquées.
 * 
 * @param idSejour id séjour
 * @param dateActe date de l'acte
 * @param codeActe code de l'acte
 * @param libeleActe libellé de l'acte
 * @param typeActe type de l'acte
 * 
 * @author Kan YAO
 * @version 1.0
 * 
 **/
public class Actes {
	private long idSejour;
	private DATE dateActe;
	private String codeActe;
	private String libeleActe;
	private String typeActe;
	
	
	/**
	 * Constructeur vide de la classe Actes
	 * 
	 **/
	public Actes(){
		
	}
	
	/**
	 * Constructeur de la classe Actes.
	 * Un acte peut être initialiser par l'identifiant du séjour
	 * 
	 * @param id_sejour
	 * 
	 * */
	public Actes(long id_sejour){
		idSejour = id_sejour;
	}
	
	/**
	 * Constructeur de la classe Actes.
	 * 
	 * @param id_sejour
	 * @param id_provider
	 * @param date_acte
	 * @param codeActe
	 * @param libele_acte
	 * 
	 **/
	public Actes(long id_sejour, DATE date_acte, String code, String libele_acte){
		idSejour = id_sejour;
		dateActe = date_acte;
		codeActe = code;
		libeleActe = libele_acte;
	}
	
	
	/**
	 * Retourne l'identifiant du séjour courant
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}
	
	
	/**
	 * Retourne la date de réalisation de l'acte courant
	 * 
	 * @return dateActe
	 * 
	 **/
	public DATE getDateActe(){
		return dateActe;
	}
	
	
	/**
	 * Retourne le libellé de l'acte courant
	 * 
	 * @return libeleActe
	 * 
	 **/
	public String getLibeleActe(){
		return libeleActe;
	}
	
	
	/**
	 * Retourne le code de l'acte courant
	 * 
	 * @return codeActe
	 * 
	 **/
	public String getCodeActe(){
		return codeActe;
	}
	

	/**
	 * Modifie le numéro de l'identifiant de l'acte courant
	 * 
	 * @param long ids
	 * 
	 **/
	public void setIdSejour(long ids){
		idSejour = ids;
	}
	
	
	/**
	 * Modifie la date de realisation de l'acte courant avec la date en argument
	 * 
	 * @param d
	 * 
	 **/
	public void setDateActe(DATE d){
		dateActe = d;
	}
	
	
	/**
	 * Modifie le libele de l'acte courant avec celui passé en parametre
	 * 
	 * @param libele
	 * 
	 **/
	public void setLibeleActe(String libele){
		libeleActe = libele;
	}
	

	
	/**
	 * Decrit l'acte courant.
	 * Elle en affiche le numéro de séjour, le code du donneur d'ordre, la date de réalisation de l'acte, la description de l'acte réalisé et la provenance.
	 * 
	 **/
	public String describeActes(){
		return "IdSejour : "+idSejour+"\nDate acte :"+dateActe.getDate()+"\nCodeActe : "+codeActe+"\nLibelé : "+libeleActe;
	}
}
