package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Menu;

public class ClientRMI {

	public static void main(String [] args) {
		
		int port = 20000;
		
		try {
			Registry registry = LocateRegistry.getRegistry(port);
			ServeurRMI serveur = (ServeurRMI)registry.lookup("monserveurrmi");
					ArrayList<Object> al = serveur.lire(Menu.class);
					
					for(Object o : al){
						System.out.println(((Menu)o).getMenu_nom());
					}
				 	
		}
		catch (Exception e) {
			System.out.println("Erreur clientRMI "+e.getMessage());
		}
	}
}
