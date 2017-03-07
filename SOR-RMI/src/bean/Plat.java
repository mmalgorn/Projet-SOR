package bean;

import java.io.Serializable;

import annotation.Table;

@Table(name="plat")
public class Plat implements Serializable {

	Integer plat_id;
	String plat_nom;
	String plat_description;
	String plat_photo;
	Float plat_prix;
	
	public Plat() { }
	
	public Plat(Integer plat_id, String plat_nom, String plat_description, Float plat_prix, String plat_photo) {
		this.plat_id = plat_id;
		this.plat_nom = plat_nom;
		this.plat_description = plat_description;
		this.plat_prix = plat_prix;
		this.plat_photo = plat_photo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Plat other = (Plat) obj;
		if (plat_description == null) {
			if (other.plat_description != null) return false;
		} else if (!plat_description.equals(other.plat_description)) return false;
		if (plat_id == null) {
			if (other.plat_id != null) return false;
		} else if (!plat_id.equals(other.plat_id)) return false;
		if (plat_nom == null) {
			if (other.plat_nom != null) return false;
		} else if (!plat_nom.equals(other.plat_nom)) return false;
		if (plat_photo == null) {
			if (other.plat_photo != null) return false;
		} else if (!plat_photo.equals(other.plat_photo)) return false;
		return true;
	}

	public String getPlat_description() {
		return plat_description;
	}

	public Integer getPlat_id() {
		return plat_id;
	}

	public String getPlat_nom() {
		return plat_nom;
	}

	public String getPlat_photo() {
		return plat_photo;
	}

	public Float getPlat_prix() {
		return plat_prix;
	}

	public void setPlat_description(String plat_description) {
		this.plat_description = plat_description;
	}

	public void setPlat_id(Integer plat_id) {
		this.plat_id = plat_id;
	}

	public void setPlat_nom(String plat_nom) {
		this.plat_nom = plat_nom;
	}
	
	public void setPlat_photo(String plat_photo) {
		this.plat_photo = plat_photo;
	}

	public void setPlat_prix(Float plat_prix) {
		this.plat_prix = plat_prix;
	}
}
