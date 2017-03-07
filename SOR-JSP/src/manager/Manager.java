package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Admin;
import bean.Groupe;
import bean.Menu;
import bean.Plat;
import rmi.ServeurRMI;

public class Manager {

	static ServeurRMI serveur;
	
	static {
		int port = 20000;

		try {
			Registry registry = LocateRegistry.getRegistry(port);
			serveur = (ServeurRMI)registry.lookup("monserveurrmi");

		}catch (Exception e) {
			System.out.println("Erreur clientRMI "+e.getMessage());
		}
	}


	public ArrayList<Admin> getAdmin(String name, String password) throws RemoteException{
		
		return serveur.getAdmin(name, password);
	}

	public ArrayList<Groupe> getGroupe(String name) throws RemoteException{
		
		return serveur.getGroupe(name);
	}

	public ArrayList<Plat> getGroupePlat(Groupe g) throws RemoteException{
		
		return serveur.getGroupePlat(g);
	}

	public ArrayList<Menu> getMenu(String name) throws RemoteException{
		
		return serveur.getMenu(name);
	}

	public ArrayList<Plat> getMenuPlat(Menu m) throws RemoteException{
		
		return serveur.getMenuPlat(m);
	}

	public static ArrayList<Plat> getPlat() throws RemoteException{
		
		return serveur.getPlat();
	}
	public ArrayList<Plat> getPlat(String nom) throws RemoteException{
		
		return serveur.getPlat(nom);
	}

	public ArrayList<Plat> getPlat(String comparateur, float prix) throws RemoteException{
	
		//return serveur.getPlat(comparateur,prix);
		return new ArrayList<Plat>();
				
	}

	public ArrayList<Groupe> getPlatGroupe(Plat p) throws RemoteException{
		
		return serveur.getPlatGroupe(p);
	}

	public ArrayList<Menu> getPlatMenu(Plat p) throws RemoteException{
		
		return serveur.getPlatMenu(p);
	}

	public boolean putAdmin(Admin a) throws RemoteException{
		
		return serveur.putAdmin(a);
	}

	public boolean putGroupe(Groupe g) throws RemoteException{
		
		return serveur.putGroupe(g);
	}

	public boolean putGroupePlat(Plat p, Groupe g) throws RemoteException{
		
		return serveur.putGroupePlat(p, g);
	}

	public boolean putMenu(Menu m) throws RemoteException{
		
		return serveur.putMenu(m);
	}

	public boolean putPlatMenu(Plat p, Menu m) throws RemoteException{
		
		//return  serveur.MenuPlat(p, m);
		return false;
	}

	public boolean putPlat(Plat p) throws RemoteException{
		
		return serveur.putPlat(p);
	}

	public boolean updateAdmin(Admin a) throws RemoteException{
		
		return serveur.updateAdmin(a);
	}

	public boolean updateGroupe(Groupe g) throws RemoteException{
		
		return serveur.updateGroupe(g);
	}

	public boolean updateMenu(Menu m) throws RemoteException{
		
		return serveur.updateMenu(m);
	}

	public boolean updatePlat(Plat p) throws RemoteException{
		
		return serveur.updatePlat(p);
	}

	
	
}
