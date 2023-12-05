package it.exolab.netfilm.jpa.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "regista")
public class Regista implements Serializable {
	
	private static final long serialVersionUID = 6822254710013267483L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REGISTA")
	private Integer idRegista;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "COGNOME")
	private String cognome;
	
	@Column(name = "ANNO_DI_NASCITA")
	private Integer annoDiNascita;
	
	@Column(name = "ANNO_DI_MORTE")
	private Integer annoDiMorte;
	
	/*
	 * In una qualsiasi relazione, occorre un solo mappaggio, da effettuare in uno dei due model. Nell'altro, occorre inserire
	 * "mappedBy" ed il riferimento all'oggetto dove c'è l'annotation con il relativo mappaggio, in questo caso Regista regista.
	 */
	@OneToMany(mappedBy = "regista")
	private List<Film> listaFilm;
	
	public Regista() {
		super();
	}

	public Integer getIdRegista() {
		return idRegista;
	}

	public void setIdRegista(Integer idRegista) {
		this.idRegista = idRegista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getAnnoDiNascita() {
		return annoDiNascita;
	}

	public void setAnnoDiNascita(Integer annoDiNascita) {
		this.annoDiNascita = annoDiNascita;
	}

	public Integer getAnnoDiMorte() {
		return annoDiMorte;
	}

	public void setAnnoDiMorte(Integer annoDiMorte) {
		this.annoDiMorte = annoDiMorte;
	}

	public List<Film> getListaFilm() {
		return listaFilm;
	}

	public void setListaFilm(List<Film> listaFilm) {
		this.listaFilm = listaFilm;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRegista == null) ? 0 : idRegista.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regista other = (Regista) obj;
		if (idRegista == null) {
			if (other.idRegista != null)
				return false;
		} else if (!idRegista.equals(other.idRegista))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Regista [idRegista=" + idRegista + ", nome=" + nome + ", cognome=" + cognome + ", annoDiNascita="
				+ annoDiNascita + ", annoDiMorte=" + annoDiMorte + ", listaFilm.size=" + (listaFilm != null ? listaFilm.size() : "0") + "]";
	}
	
}
