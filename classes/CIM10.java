package classes;

/**
 * Cette classe repr�sente les diff�rents diagnostics.
 * 
 * @param idSejour id s�jour
 * @param dateDiagnostic date du diagnostic
 * @param code code du diagnostic
 * @param libele libell� du diagnostic
 * @param typeDiagnostic type de diagnostic
 * 
 * @author Kan YAO
 *
 **/
public class CIM10 {
	private long idSejour;
	private DATE dateDiagnostic;
	private String code;
	private String libele;
	private String typeDiagnostic;
	
	
	/**
	 * Constructeur vide de la classe
	 */
	public CIM10(){
		
	}
	
	
	/**
	 * Constructeur de la classe avec l'ensemble de ses parametre 
	 * 
	 * @param idSej
	 * @param dateDiag
	 * @param cod
	 * @param lib
	 * @param typeDiag
	 * 
	 **/
	public CIM10(long idSej, DATE dateDiag, String cod, String lib, String typeDiag){
		idSejour = idSej;
		dateDiagnostic = dateDiag;
		code = cod;
		libele = lib;
		typeDiagnostic = typeDiag;
	}
	
	
	/**
	 * Retourne l'idSejour du CIM10 courant
	 * 
	 * @return idSejour
	 * 
	 **/
	public long getIdSejour(){
		return idSejour;
	}
	
	
	/**
	 * Retourne la dateDiagnostic du CIM10 courant
	 * 
	 * @return dateDiagnostic
	 * 
	 **/
	public DATE getDateDiag(){
		return dateDiagnostic;
	}
	
	
	/**
	 * Retourne le code du CIM10 courant
	 * 
	 * @return code
	 * 
	 */
	public String getCodeDiag(){
		return code;
	}
	
	
	/**
	 * Retourne le libel� du CIM10 courant 
	 * 
	 * @return libele
	 * 
	 **/
	public String getLibeleDiag(){
		return libele;
	}
	
	
	/**
	 * Retourne le typeDiagnostic du CIM10 courant
	 * 
	 * @return typeDiagnostic
	 * 
	 **/
	public String getTypeDiag(){
		return typeDiagnostic;
	}
	
	
	/**
	 * D�crit le CIM10 courant
	 * 
	 * @return
	 * 
	 **/
	public String describeCIM(){
		return "S�jour : "+idSejour+"\nType de diagnostic : "+typeDiagnostic+"\ncode : "+code+"\nlibel� : "+libele;
	}
}
