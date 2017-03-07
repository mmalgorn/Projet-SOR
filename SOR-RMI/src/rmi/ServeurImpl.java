package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bean.Admin;
import bean.Groupe;
import bean.Menu;
import bean.Plat;
import database.Database;

public class ServeurImpl implements ServeurRMI {

	static Database db;

	public static void main(String[] args) {
		

		final int port = 20000;

		final ServeurImpl si = new ServeurImpl();
		ServeurRMI serveurRMI = null;

		Registry registry = null;

		db = new Database();
		db.open();
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (final RemoteException e) {
			System.out.println("Erreur registry " + e.getMessage());
		}

		try {
			serveurRMI = (ServeurRMI) UnicastRemoteObject.exportObject(si, 0);
		} catch (final RemoteException e) {
			System.out.println("Erreur exportObject " + e.getMessage());
		}

		try {
			registry.rebind("monserveurrmi", serveurRMI);
		} catch (final RemoteException e) {
			System.out.println("Erreur rebind " + e.getMessage());
		}

		System.out.println("Serveur RMI lancé");
	}

	@Override
	public ArrayList<Admin> getAdmin(String name, String password) throws RemoteException {

		return db.getAdmin(name, password);
	}

	@Override
	public ArrayList<Groupe> getGroupe(String name) throws RemoteException {
		return db.getGroupe(name);
	}

	@Override
	public ArrayList<Plat> getGroupePlat(Groupe g) throws RemoteException {
		return db.getGroupePlat(g);
	}

	@Override
	public ArrayList<Menu> getMenu(String name) throws RemoteException {
		return db.getMenu(name);
	}

	@Override
	public ArrayList<Plat> getMenuPlat(Menu m) throws RemoteException {
		return db.getMenuPlat(m);
	}

	
	
	@Override
	public ArrayList<Plat> getPlat(String nom) throws RemoteException {
		return db.getPlat(nom);
	}

	@Override
	public ArrayList<Plat> getPlat(String comparateur, float prix) throws RemoteException {
		return db.getPlat(comparateur, prix);
	}

	@Override
	public ArrayList<Groupe> getPlatGroupe(Plat p) throws RemoteException {
		return db.getPlatGroupe(p);
	}

	@Override
	public ArrayList<Menu> getPlatMenu(Plat p) throws RemoteException {
		return db.getPlatMenu(p);
	}

	@Override
	public boolean putAdmin(Admin a) throws RemoteException {
		return db.putAdmin(a);
	}

	@Override
	public boolean putGroupe(Groupe g) throws RemoteException {
		return db.putGroupe(g);
	}

	@Override
	public boolean putGroupePlat(Plat p, Groupe g) throws RemoteException {
		return db.putGroupePlat(p, g);
	}

	@Override
	public boolean putMenu(Menu m) throws RemoteException {
		return db.putMenu(m);
	}

	@Override
	public boolean putMenuPlat(Plat p, Menu m) throws RemoteException {
		return db.putMenuPlat(p, m);
	}

	@Override
	public boolean putPlat(Plat p) throws RemoteException {
		return db.putPlat(p);
	}

	@Override
	public boolean updateAdmin(Admin a) throws RemoteException {
		return db.updateAdmin(a);
	}

	@Override
	public boolean updateGroupe(Groupe g) throws RemoteException {
		return db.updateGroupe(g);
	}

	@Override
	public boolean updateMenu(Menu m) throws RemoteException {
		return db.updateMenu(m);
	}

	@Override
	public boolean updatePlat(Plat p) throws RemoteException {
		return db.updatePlat(p);
	}

	@Override
	public boolean delete(Class<?> c, int id) throws RemoteException {
		return db.delete(c, id);
	}

	@Override
	public boolean deleteMenuPlat(int id_menu, int id_plat) throws RemoteException {
		return db.deleteMenuPlat(id_menu, id_plat);
	}

	@Override
	public boolean deleteGroupePlat(int id_groupe, int id_plat) throws RemoteException {
		return db.deleteGroupePlat(id_groupe, id_plat);
	}

	@Override
	public ArrayList<Groupe> getGroupe() throws RemoteException {
		return db.getGroupe();
	}

	@Override
	public ArrayList<Menu> getMenu() throws RemoteException {
		return db.getMenu();
	}

	@Override
	public ArrayList<Plat> getPlat() throws RemoteException {
		System.out.println("appel getPlat");
		return db.getPlat();
	}
}
