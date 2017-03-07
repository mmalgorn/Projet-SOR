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

		int port = 20000;

		ServeurImpl si = new ServeurImpl();
		ServeurRMI serveurRMI = null;

		Registry registry = null;

		db = new Database();
		db.open();
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur registry " + e.getMessage());
		}

		try {
			serveurRMI = (ServeurRMI) UnicastRemoteObject.exportObject(si, 0);
		} catch (RemoteException e) {
			System.out.println("Erreur exportObject " + e.getMessage());
		}

		try {
			registry.rebind("monserveurrmi", serveurRMI);
		} catch (RemoteException e) {
			System.out.println("Erreur rebind " + e.getMessage());
		}

		System.out.println("Serveur RMI lancé");
	}

	@Override
	public ArrayList<Admin> getAdmin(String name, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return db.getAdmin(name, password);
	}

	@Override
	public boolean putAdmin(Admin a) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAdmin(Admin a) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Plat> getPlat(String... arg) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putPlat(Plat p) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePlat(Plat p) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Menu> getMenu(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putMenu(Menu m) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMenu(Menu m) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Groupe> getGroupe(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putGroupe(Groupe g) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGroupe(Groupe g) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Plat> getMenuPlat(Menu m) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putMenuPlat(Menu m) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Menu> getPlatMenu(Plat p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putPlatMenu(Plat p, Menu m) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Plat> getGroupePlat(Groupe p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putGroupePlat(Plat p, Groupe g) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Groupe> getPlatGroupe(Plat p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
