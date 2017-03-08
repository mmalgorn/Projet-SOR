package bean;

import java.io.Serializable;
import annotation.Table;

@Table(name = "menu")
public class Menu implements Serializable {

	int		menu_id;
	String	menu_nom;
	String  menu_description;
	double  menu_prix;

	public Menu() {
	}
	
	public Menu(int menu_id, String menu_nom, String menu_description, double menu_prix) {
		this.menu_id = menu_id;
		this.menu_nom = menu_nom;
		this.menu_description = menu_description;
		this.menu_prix = menu_prix;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Menu m = (Menu) obj;
		if (this.menu_id != m.getMenu_id()) return false;
		if (!this.menu_nom.equals(m.getMenu_nom())) return false;
		if (!this.menu_description.equals(m.getMenu_description())) return false;
		if (!(this.menu_prix==m.getMenu_prix())) return false;
				
		return true;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_nom() {
		return menu_nom;
	}

	public void setMenu_nom(String menu_nom) {
		this.menu_nom = menu_nom;
	}

	public String getMenu_description() {
		return menu_description;
	}

	public void setMenu_description(String menu_description) {
		this.menu_description = menu_description;
	}

	public double getMenu_prix() {
		return menu_prix;
	}

	public void setMenu_prix(double menu_prix) {
		this.menu_prix = menu_prix;
	}
	
	

	
}
