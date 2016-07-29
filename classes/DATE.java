package classes;

/**
 * <P>Cette classe pr&eacute;sente l'objet date et sa repr&eacute;sentation.<br/>Une date dans l'ensemble de ce projet sera pr&eacute;sent&eacute;e
 * sous le format suivant : <br/> timedate : jj/mm/aaaa HH:MM<br/>dateConcat : 000000000000 (timestamp de la date)</P>
 * 
 * @param timeDate 
 * @param dateConcat
 * 
 * @author Kan YAO
 * 
 **/
public class DATE {
	private String timeDate;
	private long dateConcat;
	
	
	/**
	 * <P>Constructeur vide de la classe DATE</P>
	 **/
	public DATE(){
		
	}
	
	
	/**
	 * <p>Constructeur de la classe DATE avec l'initialisation des variables la repr&eacute;sentant.</p>
	 * 
	 * @param d1 : String - repr&eacute;sente la date au format jj/mm/aaaa HH:MM
	 * @param d2 : long - repr&eacute;sente le timestamp de la date
	 * 
	 **/
	public DATE(String d1, long d2){
		timeDate = d1;
		dateConcat = d2;
	}

	
	/**
	 * <P>Modifie la date avec la variable en param&egrave;tre</P>
	 * 
	 * @param timedate
	 * 
	 **/
	public void setTimeDate(String timedate){
		timeDate = timedate;
	}
	
	
	/**
	 * <p>Modifie le timestamp de la date avec la valeur passée en param&egrave;tre</p>
	 * 
	 * @param dateconcat
	 * 
	 **/
	public void setDateConcat(long dateconcat){
		dateConcat = dateconcat;
	}
	
	
	/**
	 * <p>Modifie le timestamp et la date par les valeurs en param&egrave;tre</p>
	 * 
	 * @param timedate
	 * @param timestamp
	 * 
	 **/
	public void setDATE(String timedate, long timestamp){
		timeDate = timedate;
		dateConcat = timestamp;
	}
	
	
	/**
	 * <p>Retourne la description de la date. C'est &agrave; dire renvoie la date et sa valeur en timestamp</p>
	 * 
	 * @return description
	 * 
	 **/
	public String descriptionDate(){
		return "Date : "+timeDate+"; Timestamp : "+dateConcat;
		
	}
	
	
	/**
	 * <p>Renvoie le timestamp de la date calcul&eacute;</>
	 * 
	 * @return dateConcat
	 * 
	 **/
	public long getTimestamp(){
		return dateConcat;
	}
	
	
	/**
	 * <p>Renvoie la date au format jj/mm/aaaa HH:MM</p>
	 * 
	 * @return timeDate
	 * 
	 **/
	public String getDate(){
		return timeDate;
	}
}
