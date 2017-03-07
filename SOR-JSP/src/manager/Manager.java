package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Plat;
import rmi.ServeurRMI;

public class Manager {

	ServeurRMI serveur;
	
	public Manager(){
		int port = 20000;

		try {
			Registry registry = LocateRegistry.getRegistry(port);
			serveur = (ServeurRMI)registry.lookup("monserveurrmi");

		}catch (Exception e) {
			System.out.println("Erreur clientRMI "+e.getMessage());
		}
	}


	public ArrayList<Object> getAllPlat(){
		try {
			return serveur.lire(Plat.class);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
