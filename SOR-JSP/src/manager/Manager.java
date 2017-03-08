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
			serveur = (ServeurRMI) registry.lookup("monserveurrmi");

		} catch (Exception e) {
			System.out.println("Erreur clientRMI " + e.getMessage());
		}
	}

	public static ArrayList<Admin> getAdmin(String name, String password) throws RemoteException {
		return serveur.getAdmin(name, password);
	}

	public static ArrayList<Groupe> getGroupe() throws RemoteException {
		return serveur.getGroupe();
	}

	public static ArrayList<Groupe> getGroupe(int id) throws RemoteException {
		return serveur.getGroupe(id);
	}
	
	public static ArrayList<Groupe> getGroupe(String name) throws RemoteException {
		return serveur.getGroupe(name);
	}

	public static ArrayList<Menu> getMenu() throws RemoteException {
		return serveur.getMenu();
	}

	public static ArrayList<Menu> getMenu(int id) throws RemoteException {
		return serveur.getMenu(id);
	}
	
	public static ArrayList<Menu> getMenu(String name) throws RemoteException {
		return serveur.getMenu(name);
	}

	public static ArrayList<Plat> getMenuPlat(int id) throws RemoteException {
		return serveur.getMenuPlat(id);
	}

	public static ArrayList<Plat> getPlat() throws RemoteException {
		return serveur.getPlat();
	}

	public static ArrayList<Plat> getPlat(int id) throws RemoteException {
		return serveur.getPlat(id);
	}
	
	public static ArrayList<Plat> getPlat(String name) throws RemoteException {
		return serveur.getPlat(name);
	}

	public static ArrayList<Menu> getPlatMenu(int id) throws RemoteException {
		return serveur.getPlatMenu(id);
	}

	public static boolean putAdmin(Admin a) throws RemoteException {
		return serveur.putAdmin(a);
	}

	public static boolean putGroupe(Groupe g) throws RemoteException {
		return serveur.putGroupe(g);
	}

	public static boolean putMenu(Menu m) throws RemoteException {
		return serveur.putMenu(m);
	}

	public static boolean putPlatMenu(int p, int m, int g) throws RemoteException {
		return serveur.putMenuPlat(p, m, g);

	}

	public static boolean putPlat(Plat p) throws RemoteException {
		return serveur.putPlat(p);
	}

	public static boolean updateAdmin(Admin a) throws RemoteException {
		return serveur.updateAdmin(a);
	}

	public static boolean updateGroupe(Groupe g) throws RemoteException {
		return serveur.updateGroupe(g);
	}

	public static boolean updateMenu(Menu m) throws RemoteException {
		return serveur.updateMenu(m);
	}

	public static boolean updatePlat(Plat p) throws RemoteException {
		return serveur.updatePlat(p);
	}

}
