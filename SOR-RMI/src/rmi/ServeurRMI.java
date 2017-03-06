package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServeurRMI extends Remote {

	public ArrayList<Object> lire(Class c) throws RemoteException;
	
}
