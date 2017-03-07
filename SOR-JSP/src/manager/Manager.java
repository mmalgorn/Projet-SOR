package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Admin;
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


	public ArrayList<Plat> getAllPlat() throws RemoteException{
			return serveur.getPlat();
		
	}
	
	public ArrayList<Admin> getAdmin(String name,String password) throws RemoteException{
			return serveur.getAdmin(name, password);
		
		
	}
	
	public boolean putAdmin(Admin a) throws RemoteException{
			return serveur.putAdmin(a);
		
	}
	
	public boolean updateAdmin(Admin a) throws RemoteException{
			return serveur.updateAdmin(a);
		
	}
}
