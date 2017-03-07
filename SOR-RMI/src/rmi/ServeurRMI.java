package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import bean.*;

public interface ServeurRMI extends Remote {

	public ArrayList<Admin> getAdmin(String name, String password) throws RemoteException;
	
	public boolean putAdmin(Admin a) throws RemoteException;
	
	public boolean updateAdmin(Admin a) throws RemoteException;
	
	public ArrayList<Plat> getPlat(String ...arg) throws RemoteException;
	
	public boolean putPlat(Plat p) throws RemoteException;
	
	public boolean updatePlat(Plat p) throws RemoteException;
	
	public ArrayList<Menu> getMenu(String name) throws RemoteException;
	
	public boolean putMenu(Menu m) throws RemoteException;
	
	public boolean updateMenu(Menu m) throws RemoteException;
	
	public ArrayList<Menu> getGroupe(String name) throws RemoteException;
	
	public boolean putGroupe(Groupe g) throws RemoteException;
	
	public boolean updateGroupe(Groupe g) throws RemoteException;
	
	public ArrayList<Plat> getMenuPlat(Menu m) throws RemoteException;
	
	public ArrayList<Menu> getPlatMenu(Plat p) throws RemoteException;
	
	public ArrayList<Plat> getGroupePlat(Groupe p) throws RemoteException;
	
	public ArrayList<Groupe> getPlatGroupe(Plat p) throws RemoteException;
}
