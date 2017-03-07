package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import bean.*;

public interface ServeurRMI extends Remote {

	public ArrayList<Admin> getAdmin(String name, String password) throws RemoteException;
	
	public ArrayList<Groupe> getGroupe() throws RemoteException;

	public ArrayList<Groupe> getGroupe(String name) throws RemoteException;

	public ArrayList<Plat> getGroupePlat(Groupe g) throws RemoteException;
	
	public ArrayList<Menu> getMenu() throws RemoteException;

	public ArrayList<Menu> getMenu(String name) throws RemoteException;

	public ArrayList<Plat> getMenuPlat(Menu m) throws RemoteException;

	public ArrayList<Plat> getPlat() throws RemoteException;
	
	public ArrayList<Plat> getPlat(String nom) throws RemoteException;

	public ArrayList<Plat> getPlat(String comparateur, float prix) throws RemoteException;

	public ArrayList<Groupe> getPlatGroupe(Plat p) throws RemoteException;

	public ArrayList<Menu> getPlatMenu(Plat p) throws RemoteException;

	public boolean putAdmin(Admin a) throws RemoteException;

	public boolean putGroupe(Groupe g) throws RemoteException;

	public boolean putGroupePlat(Plat p, Groupe g) throws RemoteException;

	public boolean putMenu(Menu m) throws RemoteException;

	public boolean putMenuPlat(Plat p, Menu m) throws RemoteException;

	public boolean putPlat(Plat p) throws RemoteException;

	public boolean updateAdmin(Admin a) throws RemoteException;

	public boolean updateGroupe(Groupe g) throws RemoteException;

	public boolean updateMenu(Menu m) throws RemoteException;

	public boolean updatePlat(Plat p) throws RemoteException;
	
	public boolean delete(Class<?> c, int id) throws RemoteException;
	
	public boolean deleteMenuPlat(int id_menu, int id_plat) throws RemoteException;
	
	public boolean deleteGroupePlat(int id_groupe, int id_plat) throws RemoteException;
}
