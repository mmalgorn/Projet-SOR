package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurImpl implements ServeurRMI{

	@Override
	public String meth() throws RemoteException {
		System.out.println("Exec meth");
		return "valeur retournée par meth";
	}
	
	public static void main(String [] args) {
		
		int port = 20000;
		
		ServeurImpl si = new ServeurImpl();
		ServeurRMI serveurRMI = null;
		
		Registry registry = null;
		
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur registry "+e.getMessage());
		}

		try {
			serveurRMI = 
				(ServeurRMI)UnicastRemoteObject.exportObject(si,0);
		} catch (RemoteException e) {
			System.out.println("Erreur exportObject "+e.getMessage());
		}
		
		try {
			registry.rebind("monserveurrmi", serveurRMI);
		} catch (RemoteException e) {
			System.out.println("Erreur rebind "+e.getMessage());
		}
		System.out.println("Serveur RMI lancé");
	}

}
