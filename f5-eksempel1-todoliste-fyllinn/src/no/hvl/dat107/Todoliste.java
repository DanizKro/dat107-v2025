package no.hvl.dat107;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "forelesning5_todoliste")
public class Todoliste {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String navn;
	
	@OneToMany(mappedBy = "liste", fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true) //Merger underliggende(barn) på Persist og Merge metoden

	
	List<Todo> todos;
	
	public Todoliste() {
		this("...");
	}
	
	public Todoliste(String navn) {
		this.navn = navn;
		todos = new ArrayList<>();
	}
	
	public void leggTil(Todo todo) {
		todos.add(todo);
	}
	
	public void fjern(Todo todo) {
		todos.remove(todo);
	}
	
	public int getId() {
		return id;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	@Override
	public String toString() {
		return "Todoliste [id=" + id + ", navn=" + navn + ", todos=" + todos + "]";
	}
}


