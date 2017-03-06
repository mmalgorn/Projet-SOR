package bean;

import annotation.Table;

@Table(name="menu")
public class Menu {
	
	
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
