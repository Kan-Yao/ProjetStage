package classes;

import classes.DATE;

/**
 * Cette classe pr�sente l'objet Actes avec les diff�rentes m�thode qui peuvent lui �tre appliqu�es.
 * 
 * @param idSejour id s�jour
 * @param dateActe date de l'acte
 * @param codeActe code de l'acte
 * @param libeleActe libell� de l'acte
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
	 * Un acte peut �tre initialiser par l'identifiant du s�jour
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
	 * Retourne l'identifiant du s�jour courant
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}
	
	
	/**
	 * Retourne la date de r�alisation de l'acte courant
	 * 
	 * @return dateActe
	 * 
	 **/
	public DATE getDateActe(){
		return dateActe;
	}
	
	
	/**
	 * Retourne le libell� de l'acte courant
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
	 * Modifie le num�ro de l'identifiant de l'acte courant
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
	 * Modifie le libele de l'acte courant avec celui pass� en parametre
	 * 
	 * @param libele
	 * 
	 **/
	public void setLibeleActe(String libele){
		libeleActe = libele;
	}
	

	
	/**
	 * Decrit l'acte courant.
	 * Elle en affiche le num�ro de s�jour, le code du donneur d'ordre, la date de r�alisation de l'acte, la description de l'acte r�alis� et la provenance.
	 * 
	 **/
	public String describeActes(){
		return "IdSejour : "+idSejour+"\nDate acte :"+dateActe.getDate()+"\nCodeActe : "+codeActe+"\nLibel� : "+libeleActe;
	}
}
