package no.hvl.dat107;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		VitnemalDAO vitnemalDAO = new VitnemalDAO();
		
		//a) Søke opp vitnemålet til en gitt student.
//		Vitnemal vm = vitnemalDAO.hentVitnemalForStudent(123456);
//		System.out.print(vm);

		
		//b) Søke opp karakteren til en gitt student i et gitt kurs.
//		Karakter k = vitnemalDAO.hentKarakterForStudentIEmne(123456, "DAT107");
//		System.out.print(k);
		
		//c) Registrere en ny karakter for en gitt student 
		//c-2.0 når karakter ikke finnes fra før.
		vitnemalDAO.registrerKarakterForStudent(123456, "Dat108", LocalDate.now(), "C");
		Vitnemal vm = vitnemalDAO.hentVitnemalForStudent(123456);
		System.out.print(vm);															//Er den endret nå?
		
		
		//d) Registrere en ny karakter for en gitt student
		//		når karakter finnes fra før. Samme metode som i sted.
		
		
		//"TEST"
//		List<Karakter> hmmm = vitnemalDAO.hentKarakterlisteForFerdige("DAT107");
//		hmmm.forEach(System.out::println);
	}
	
//	private static Scanner scanner = new Scanner(System.in);
//	private static void pauseOgSjekkDatabasen(String prompt) {
//		System.out.println(prompt);
//		System.out.println("Trykk \"ENTER\" for å fortsette ...");
//		scanner.nextLine();
//	}
	

}





