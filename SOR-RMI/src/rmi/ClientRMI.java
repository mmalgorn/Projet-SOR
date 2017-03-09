package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;

import bean.Log;

public class ClientRMI {

	public static void main(String[] args) {

		int port = 20000;

		try {
			Registry registry = LocateRegistry.getRegistry(port);
			ServeurRMI serveur = (ServeurRMI) registry.lookup("monserveurrmi");
			
		} catch (Exception e) {
			System.out.println("Erreur clientRMI " + e.getMessage());
		}
	}
}
