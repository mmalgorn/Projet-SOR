package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import bean.Plat;

public class ClientRMI {

	public static void main(String[] args) {

		int port = 20000;

		try {
			Registry registry = LocateRegistry.getRegistry(port);
			ServeurRMI serveur = (ServeurRMI) registry.lookup("monserveurrmi");
			ArrayList<Object> obj = serveur.lire(Plat.class);

			for (Object o : obj)
				System.out.println(((Plat) o).getPlat_nom());
		} catch (Exception e) {
			System.out.println("Erreur clientRMI " + e.getMessage());

		}
	}
}
