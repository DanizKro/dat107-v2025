package no.hvl.dat107;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "forelesning3_one2many")
public class Karakter {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int karNr;
	
	private String emnekode;
	private LocalDate eksdato;
	private String bokstav;
	
	//ref til vitnemål ... ?
	//private int studNr;
	
	//MÅ alltid være med for mapping
	@ManyToOne
	@JoinColumn(name = "studnr")
	private Vitnemal vitnemal;
	
	public Karakter(){}

	public Karakter(String emnekode, LocalDate eksdato, String bokstav, Vitnemal vitnemal) {
		super();
		this.emnekode = emnekode;
		this.eksdato = eksdato;
		this.bokstav = bokstav;
		this.vitnemal = vitnemal;
	}



	//Vitnemål skal ikek være med fordi den har egen utskrift!
	@Override
	public String toString() {
		return "Karakter [karNr=" + karNr + ", emnekode=" + emnekode + ", eksdato=" + eksdato + ", bokstav=" + bokstav;
	}
	
	
}
