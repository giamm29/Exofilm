package it.exolab.netfilm.jpa.models;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "film")
public class Film implements Serializable {

	private static final long serialVersionUID = 2331240197102872672L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FILM")
	private Integer idFilm;
	
	@Column(name = "TITOLO")
	private String titolo;
	
	@Column(name = "ID_REGISTA")
    private Integer idRegista;
	
	/*
	 * Fate attenzione al parametro insertable=false qui sotto: se è false, così come updatable, in fase di insert o di update, non verrà
	 * valorizzato (né nella tabella regista, né l'id_regista in film). Per questo è presente idRegista come attributo di classe.
	 * Al contrario, mettendolo/i true, non c'è bisogno di idRegista come parametro.
	 * 
	 * Su nullable: nullable = true (opzione di default) vuol dire che non c'è un vincolo not null nella colonna della foreign key,
	 * al contrario se = false -> esiste vincolo di not null sulla foreign key.
	 * 
	 * In più: con nullable = true -> esegue una left join, nullable = false -> esegue una inner join
	 */
	@ManyToOne
	@JoinColumn(name = "ID_REGISTA", nullable = false, insertable = false, updatable = false)
	@JsonbTransient
	private Regista regista;
	
	/*
	 * La differenza tra FetchType.LAZY e FetchType.EAGER è quanto segue:
	 * - con EAGER, hibernate viene istruito ad eseguire query aggiuntive al momento dell'esecuzione della select per trovare i film, al fine di
	 *   caricare da subito i modelli della lista di categorie.
	 * - con LAZY, hibernate viene istruito ad eseguire le query aggiuntive per caricare i dati delle categorie solo al momento in cui
	 *   sono effettivamente richiesti, al momento cioé in cui io chiamo formalmente .getListaCategorie(). Se per qualche motivo non 
	 *   fosse mai chiamato, la lista non verrebbe mai riempita e questo equivarrebbe ad un risparmio di risorse.
	 *   
	 *   Per quanto riguarda del cascade, la notazione {} equivale a specificare una lista di cascadeType accettati.
	 *   CascadeType indica in questo caso quali operazioni propagare all'entità correlata.
	 *   Vale a dire: con CascadeType.PERSIST, se persisto un film che contiene una entità nuova, verrà eseguito l'insert
	 *   della nuova entità. Al contrario, con CascadeType.REMOVE, qualora cancellassi un film, verrebbero cancellate anche tutte le
	 *   Categoria a lui associate.
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
    	name = "film_categoria",
    	joinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName = "ID_FILM"),
        inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    )
	private List<Categoria> listaCategorie;
	
	public Film() {
		super();
	}

	public Integer getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(Integer idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getIdRegista() {
		return idRegista;
	}

	public void setIdRegista(Integer idRegista) {
		this.idRegista = idRegista;
	}

	public Regista getRegista() {
		return regista;
	}

	public void setRegista(Regista regista) {
		this.regista = regista;
	}

	public List<Categoria> getListaCategorie() {
		return listaCategorie;
	}

	public void setListaCategorie(List<Categoria> listaCategorie) {
		this.listaCategorie = listaCategorie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFilm == null) ? 0 : idFilm.hashCode());
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
		Film other = (Film) obj;
		if (idFilm == null) {
			if (other.idFilm != null)
				return false;
		} else if (!idFilm.equals(other.idFilm))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", titolo=" + titolo + ", idRegista=" + idRegista + ", regista=" + regista
				+ ", listaCategorie=" + listaCategorie + "]";
	}
	
}
