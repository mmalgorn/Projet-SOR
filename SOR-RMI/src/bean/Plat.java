package bean;

import java.io.Serializable;
import java.sql.Blob;

import annotation.Table;

@Table(name = "plat")
public class Plat implements Serializable {

	int		plat_id;
	String	plat_nom;
	String	plat_description;
	float	plat_prix;
	Photo	plat_photo;
	int 	plat_id_groupe;

	public Plat() {
	}

	public Plat(int plat_id, String plat_nom, String plat_description, float plat_prix, Photo plat_photo, int plat_id_groupe) {
		this.plat_id = plat_id;
		this.plat_nom = plat_nom;
		this.plat_description = plat_description;
		this.plat_prix = plat_prix;
		this.plat_photo = plat_photo;
		this.plat_id_groupe = plat_id_groupe;
	}
	
	public Plat(String plat_nom, String plat_description, float plat_prix, Photo plat_photo, int plat_id_groupe) {
		this.plat_nom = plat_nom;
		this.plat_description = plat_description;
		this.plat_prix = plat_prix;
		this.plat_photo = plat_photo;
		this.plat_id_groupe = plat_id_groupe;
	}

	public int getPlat_id_groupe() {
		return plat_id_groupe;
	}

	public void setPlat_id_groupe(int plat_id_groupe) {
		this.plat_id_groupe = plat_id_groupe;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Plat p = (Plat) obj;
		if (this.plat_id != p.getPlat_id()) return false;
		if (!this.plat_nom.equals(p.getPlat_nom())) return false;
		if (!this.plat_description.equals(p.getPlat_description())) return false;
		if (this.plat_prix != p.getPlat_prix()) return false;
		if (!this.plat_photo.equals(p.getPlat_photo())) return false;
		if (this.plat_id_groupe != p.getPlat_id_groupe()) return false;
		return true;
	}

	public String getPlat_description() {
		return plat_description;
	}

	public int getPlat_id() {
		return plat_id;
	}

	public String getPlat_nom() {
		return plat_nom;
	}

	public Photo getPlat_photo() {
		return plat_photo;
	}

	public float getPlat_prix() {
		return plat_prix;
	}

	public void setPlat_description(String plat_description) {
		this.plat_description = plat_description;
	}

	public void setPlat_id(int plat_id) {
		this.plat_id = plat_id;
	}

	public void setPlat_nom(String plat_nom) {
		this.plat_nom = plat_nom;
	}

	public void setPlat_photo(Photo plat_photo) {
		this.plat_photo = plat_photo;
	}

	public void setPlat_prix(float plat_prix) {
		this.plat_prix = plat_prix;
	}
	
	public boolean checkFields() {
		if (plat_id < 0) return false;
		if (plat_nom.length() < 4 || plat_nom.length() > 25) return false;
		if (plat_description == null || plat_description.length() > 1000) return false;
		if (plat_prix > 100000 || plat_prix < 0) return false;
		return true;
	}
}
