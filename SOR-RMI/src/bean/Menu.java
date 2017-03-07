package bean;

import java.io.Serializable;
import annotation.Table;

@Table(name = "menu")
public class Menu implements Serializable {

	int		menu_id;
	String	menu_nom;

	public Menu() {
	}

	public Menu(int menu_id, String menu_nom) {
		this.menu_id = menu_id;
		this.menu_nom = menu_nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Menu m = (Menu) obj;
		if (this.menu_id != m.getMenu_id()) return false;
		if (!this.menu_nom.equals(m.getMenu_nom())) return false;
		return true;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public String getMenu_nom() {
		return menu_nom;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public void setMenu_nom(String menu_nom) {
		this.menu_nom = menu_nom;
	}

}
