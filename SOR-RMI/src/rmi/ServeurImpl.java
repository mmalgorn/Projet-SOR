package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import database.Database;

public class ServeurImpl implements ServeurRMI {
	
	static Database db;	

	public static void main(String[] args) {

		int port = 20000;

		ServeurImpl si = new ServeurImpl();
		ServeurRMI serveurRMI = null;

		Registry registry = null;

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
		
		db = new Database();
		db.open();
		
		System.out.println("Serveur RMI lancé");
	}



	@Override
	public ArrayList<Object> lire(Class c) throws RemoteException {
		return db.lire(c);
	}

}
