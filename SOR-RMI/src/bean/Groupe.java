package bean;

import java.io.Serializable;
import annotation.Table;

@Table(name = "groupe")
public class Groupe implements Serializable {

	int		groupe_id;
	String	groupe_nom;

	public Groupe() {
	}

	public Groupe(int groupe_id, String groupe_nom) {
		this.groupe_id = groupe_id;
		this.groupe_nom = groupe_nom;
	}

	public int getGroupe_id() {
		return groupe_id;
	}

	public void setGroupe_id(int groupe_id) {
		this.groupe_id = groupe_id;
	}

	public String getGroupe_nom() {
		return groupe_nom;
	}

	public void setGroupe_nom(String groupe_nom) {
		this.groupe_nom = groupe_nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Groupe g = (Groupe) obj;
		if (this.groupe_id != g.getGroupe_id()) return false;
		if (!this.groupe_nom.equals(g.getGroupe_nom())) return false;
		return true;
	}
	
	public boolean checkFields() {
		if (groupe_id < 0) return false;
		if (groupe_nom.length() > 25 || groupe_nom.length() < 4) return false;
		return true;
	}
}
