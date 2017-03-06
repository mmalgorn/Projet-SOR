package bean;

import annotation.Table;

@Table(name="menu")
public class Menu {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Menu other = (Menu) obj;
		if (menu_id == null) {
			if (other.menu_id != null) return false;
		} else if (!menu_id.equals(other.menu_id)) return false;
		if (menu_nom == null) {
			if (other.menu_nom != null) return false;
		} else if (!menu_nom.equals(other.menu_nom)) return false;
		return true;
	}

	Integer menu_id;
	String menu_nom;

	public Menu(){}
	
	public Menu(Integer menu_id, String menu_nom) {
		this.menu_id = menu_id;
		this.menu_nom = menu_nom;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_nom() {
		return menu_nom;
	}

	public void setMenu_nom(String menu_nom) {
		this.menu_nom = menu_nom;
	}
	
	
	
}
