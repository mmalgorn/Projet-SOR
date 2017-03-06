package bean;

import annotation.Table;

@Table(name="plat")
public class Plat {

	Integer plat_id;
	String plat_nom;
	String plat_description;
	String plat_photo;
	
	public Plat() { }
	
	public Plat(Integer plat_id, String plat_nom, String plat_description, String plat_photo) {
		this.plat_id = plat_id;
		this.plat_nom = plat_nom;
		this.plat_description = plat_description;
		this.plat_photo = plat_photo;
	}

	public Integer getPlat_id() {
		return plat_id;
	}

	public void setPlat_id(Integer plat_id) {
		this.plat_id = plat_id;
	}

	public String getPlat_nom() {
		return plat_nom;
	}

	public void setPlat_nom(String plat_nom) {
		this.plat_nom = plat_nom;
	}

	public String getPlat_description() {
		return plat_description;
	}

	public void setPlat_description(String plat_description) {
		this.plat_description = plat_description;
	}
	
	public String getPlat_photo() {
		return plat_photo;
	}

	public void setPlat_photo(String plat_photo) {
		this.plat_photo = plat_photo;
	}
	
	
	
}
