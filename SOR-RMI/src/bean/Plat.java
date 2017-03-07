package bean;

import java.io.Serializable;
import annotation.Table;

@Table(name = "plat")
public class Plat implements Serializable {

	int		plat_id;
	String	plat_nom;
	String	plat_description;
	float	plat_prix;
	String	plat_photo;

	public Plat() {
	}

	public Plat(int plat_id, String plat_nom, String plat_description, float plat_prix, String plat_photo) {
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
		Plat p = (Plat) obj;
		if (this.plat_id != p.getPlat_id()) return false;
		if (!this.plat_nom.equals(p.getPlat_nom())) return false;
		if (!this.plat_description.equals(p.getPlat_description())) return false;
		if (this.plat_prix != p.getPlat_prix()) return false;
		if (!this.plat_photo.equals(p.getPlat_photo())) return false;
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

	public String getPlat_photo() {
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

	public void setPlat_photo(String plat_photo) {
		this.plat_photo = plat_photo;
	}

	public void setPlat_prix(float plat_prix) {
		this.plat_prix = plat_prix;
	}
}
