package main;

import input.ReadFileActe;
import input.ReadFileCIM10;
import input.ReadFileLAB;
import input.ReadFilePatient;
import input.ReadFileSejour;
import input.ReadFileService;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import classes.Actes;
import classes.CIM10;
import classes.LAB;
import classes.DATE;
import classes.Patient;
import classes.Sejours;
import classes.Service;


public class Test {	
	public static void main(String[] args) throws IOException, ParseException {
		
		/* Initialisation des variables de classe */
		ReadFileService services = new ReadFileService();
		ReadFileSejour sejours = new ReadFileSejour();
		ReadFilePatient patients = new ReadFilePatient();
		ReadFileCIM10 CIM = new ReadFileCIM10();
		ReadFileActe actes = new ReadFileActe();
		ReadFileLAB lab = new ReadFileLAB();
		
		/* Lesture des fichiers en entrée */
		services.LoadFile(args[0]);
		sejours.LoadFile(args[1]);
		patients.LoadFile(args[2]);
		CIM.LoadFile(args[3]);
		actes.LoadFile(args[4]);
		lab.LoadFile(args[5]);
		
		System.out.println(sejours.getSejourValide().size()+" sejours valides et "+sejours.getSejourInvalide().size()+" invalides");
		
		ArrayList<Sejours> mesSejoursInvalide = new  ArrayList<Sejours>();
		ArrayList<Sejours> mesSejoursValide = new  ArrayList<Sejours>();
		
		mesSejoursInvalide = sejours.getSejourInvalide();
		mesSejoursValide = sejours.getSejourValide();
		
		fichierFinalSejourValide(services.getMesServices(mesSejoursValide), patients.getPatientSejour(mesSejoursValide), mesSejoursValide, lab.getLabFinal(mesSejoursValide), actes.getMesActes(mesSejoursValide), CIM.getDiag(mesSejoursValide)); 
		fichierFinalSejourInvalide(services.getMesServices(mesSejoursInvalide), patients.getPatientSejour(mesSejoursInvalide), mesSejoursInvalide, lab.getLabFinal(mesSejoursInvalide), actes.getMesActes(mesSejoursInvalide), CIM.getDiag(mesSejoursInvalide)); 

		
		
	}

	public static void fichierFinalSejourInvalide(ArrayList<Service> serv, ArrayList<Patient> p, ArrayList<Sejours> s, ArrayList<LAB> lab, ArrayList<Actes> a, ArrayList<CIM10> cim) throws IOException{
		PrintWriter ecrivain;
		String entete = "nda; ipp; sexe; age; statutVital; entree; sortie; duhosp; statutVitalSejour; ageSejour; dateHémoglobine; Hémoglobine; dateVGM; VGM; dateRéticulocytes; Réticulocytes; dateLeucocytes; Leucocytes; datePlaquettes; Plaquettes; dateFerritine; Ferritine; dateTransferrine; Transferrine; dateCST; CST; dateProtéineCR; protéineCR; dateCréatinine; Créatinine; dateDFG; DFG; date_Folates; Folates; dateCobalamines; Cobalamines; dateAlpha1G; Alpha1G; dateGammaglobulines; Gammaglobulines; dateThyréostimunline; Thyréostimunline; dateTrio-iodothyronine; Trio-iodothyronine; dateThyroxine; Thyroxine; dateLDH; LDH; dateBilirubine; Bilirubine; dataTransfusion; Transfusion";
		String line = "";
		
		ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter("Fichier_Synthese_Sejours_Invalides.csv")));
	    ecrivain.println(entete);
	    
	    for(int i = 0; i < s.size(); i++){
	    	 
	    	 line = getLine(serv, p, s.get(i), lab, a, cim);
	    	 
	    	ecrivain.println(line);
	     }
	    
	    ecrivain.close();
		
	}

	public static void fichierFinalSejourValide(ArrayList<Service> serv, ArrayList<Patient> p, ArrayList<Sejours> s, ArrayList<LAB> lab, ArrayList<Actes> a, ArrayList<CIM10> cim) throws IOException{
		PrintWriter ecrivain;
		String entete = "nda; ipp; sexe; age; statutVital; entree; sortie; duhosp; statutVitalSejour; ageSejour; dateHémoglobine; Hémoglobine; dateVGM; VGM; dateRéticulocytes; Réticulocytes; dateLeucocytes; Leucocytes; datePlaquettes; Plaquettes; dateFerritine; Ferritine; dateTransferrine; Transferrine; dateCST; CST; dateProtéineCR; protéineCR; dateCréatinine; Créatinine; dateDFG; DFG; date_Folates; Folates; dateCobalamines; Cobalamines; dateAlpha1G; Alpha1G; dateGammaglobulines; Gammaglobulines; dateThyréostimunline; Thyréostimunline; dateTrio-iodothyronine; Trio-iodothyronine; dateThyroxine; Thyroxine; dateLDH; LDH; dateBilirubine; Bilirubine; dataTransfusion; Transfusion";
		String line = "";
		
		ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter("Fichier_Synthese_Sejours_Valides.csv")));
	    ecrivain.println(entete);
	    
	    for(int i = 0; i < s.size(); i++){
	    	 
	    	 line = getLine(serv, p, s.get(i), lab, a, cim);
	    	 
	    	ecrivain.println(line);
	     }
	    
	    ecrivain.close();
		
	}

	private static String getLine(ArrayList<Service> serv, ArrayList<Patient> p, Sejours s, ArrayList<LAB> lab, ArrayList<Actes> a, ArrayList<CIM10> cim){
		String line = "";
		
		line += getIdentifiant(s, p);
		line += getInfoSejour(s);
		line += getHemoglobineLine(s,lab);
		line += getVGM(s,lab);
		line += getReticulocytes(s,lab);
		line += getLeucocytesLine(s,lab);
		line += getPlaquettesLine(s,lab);
		line += getFerritineLine(s, lab);
		line += getTransferrineLine(s, lab);
		line += getCSTLine(s, lab);
		line += getProteineCRLine(s,lab);
		line += getCreatinineLine(s, lab);
		line += getDFGLine(s,lab);
		line += getFolatesLine(s,lab);
		line += getCobalaminesLine(s,lab);
		line += getAlpha1GLine(s,lab);
		line += getGammaglobulinesLine(s,lab);
		line += getThyreostimunlineLine(s,lab);
		line += getTrioiodothyronineLine(s,lab);
		line += getThyroxineLine(s,lab);
		line += getLDHLine(s,lab);
		line += getBilirubineLine(s,lab);
		line += getTransfusionLine(s, a, cim);
		
		return line;
	}

	private static String getIdentifiant(Sejours s, ArrayList<Patient> p) {
		String line = "";
	
		int i;
		
		line += s.getIdSejour()+";";
		line += s.getIdPatient()+";";
		
		for(i = 0; i < p.size(); i++) {
			if(s.getIdPatient()== p.get(i).getIdPatient()){
				line += p.get(i).getSexPatient()+";";
				line += p.get(i).getAge()+";";
				line += p.get(i).getStatutVital()+";";
				
			}
		}
		
		return line;
	}
	
	private static String getInfoSejour(Sejours s) {
		String line ="";
		
		line += s.getDebutSejour().getDate()+";";
		line += s.getFinSejour().getDate()+";";
		line += s.getDureeSejour()+";";
		line += s.getStatutVital()+";";
		line += s.getAgeSejour()+";";
		
		
		return line;
	}
	
	private static String getHemoglobineLine(Sejours s, ArrayList<LAB> lab) {
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean test = false;

		int j = 0;
		double small = 50.0;
		
		for(j =  0; j < lab.size(); j++){					
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0163")){
				if(small > Double.parseDouble(lab.get(j).getValeurPrelev())){
					small = Double.parseDouble(lab.get(j).getValeurPrelev());
					date = lab.get(j).getDatePrelev();
					valeur = lab.get(j).getValeurPrelev();
					test = true;
					
				}			
			}
		}
				
		if(test != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}

	private static String getVGM(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0292")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}
	
	private static String getReticulocytes(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0253")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
	
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}
	
	private static String getLeucocytesLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0174")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}
	
	private static String getPlaquettesLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0230")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}	
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}
	
	private static String getFerritineLine(Sejours s, ArrayList<LAB> lab) {
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("E9865")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}else if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0123")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}	
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}
	
	private static String getTransferrineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("E2074")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}

	private static String getCSTLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0278")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getProteineCRLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0248")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getCreatinineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0983")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getDFGLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("F8160")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getFolatesLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A1124")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getCobalaminesLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A3840")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getAlpha1GLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0007")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getGammaglobulinesLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0129")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getThyreostimunlineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A1831")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getTrioiodothyronineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A1918")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}

	private static String getThyroxineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A2925")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}

	private static String getLDHLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0170")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getBilirubineLine(Sejours s, ArrayList<LAB> lab){
		String line ="";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int j = 0;
		
		while(j < lab.size() && find == false){
			if(s.getIdSejour() == lab.get(j).getIdSejour() && lab.get(j).getCodePrelev().contains("A0029")){
				date = lab.get(j).getDatePrelev();
				valeur = lab.get(j).getValeurPrelev();
				find = true;
			}
			
			j++;
		}
		
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
		
	}
	
	private static String getTransfusionLine(Sejours s, ArrayList<Actes> a, ArrayList<CIM10> c) {
		String line = "";
		DATE date = new DATE();
		String valeur = "";
		boolean find = false;
		int i = 0;
		
		while(find == false && i < a.size()){
			if(s.getIdSejour() == a.get(i).getIdSejour()){
				if(a.get(i).getCodeActe().contains("Z513")){
					date = a.get(i).getDateActe();
					valeur ="1";
					find = true;
				}
			}
			
			i++;
		}
		
		if(find != false){
			line += date.getDate()+";";
			line += valeur+";";
			System.out.println("\nId : "+s.getIdSejour()+"\nDatePrélèvement : "+date.getDate()+"\nValeur :"+valeur);
			
		}else{
			line +=";";
			line +=";";
		}
		
		return line;
	}

} 