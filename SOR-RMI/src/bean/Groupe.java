package bean;

import annotation.Table;

@Table(name="groupe")
public class Groupe {

	Integer groupe_id;
	String groupe_nom;
	
	public Groupe() { }
	
	public Groupe(Integer groupe_id, String groupe_nom) {
		this.groupe_id = groupe_id;
		this.groupe_nom = groupe_nom;
	}

	public Integer getGroupe_id() {
		return groupe_id;
	}

	public void setGroupe_id(Integer groupe_id) {
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
		Groupe other = (Groupe) obj;
		if (groupe_id == null) {
			if (other.groupe_id != null) return false;
		} else if (!groupe_id.equals(other.groupe_id)) return false;
		if (groupe_nom == null) {
			if (other.groupe_nom != null) return false;
		} else if (!groupe_nom.equals(other.groupe_nom)) return false;
		return true;
	}	
}
