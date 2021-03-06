package database;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.rowset.serial.SerialBlob;

import annotation.Table;
import bean.Admin;
import bean.Groupe;
import bean.Log;
import bean.Menu;
import bean.Photo;
import bean.Plat;

public class Database {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static String			fData	= "data";
	static ResourceBundle	rb;
	static String			url, user, pwd;
	static Connection		connection;
	
	public static void main(String[] args) {
		Database db = new Database();
		
		db.open();
		db.close();
	}

	public Database() {
		rb = ResourceBundle.getBundle(fData);
		url = rb.getString("url");
		user = rb.getString("user");
		pwd = rb.getString("pwd");
		System.out.println(url+" "+user+" "+pwd);
		connection = null;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Supression g�n�rique d'un �l�ment d'une table
	public boolean delete(Class<?> c, int id) {
		Table table = (Table) c.getAnnotation(Table.class);
		String sql = "delete from " + table.name() + " where " + table.name() + "_id = ?";
		String sqlSupr;
		
		boolean res = true;
		try {
			PreparedStatement ps = null;
			PreparedStatement psSupr = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			if(table.name().equals("menu")){
				sqlSupr = "delete from menu_plat where mp_id_menu = ?";
				psSupr = connection.prepareStatement(sqlSupr);
				psSupr.setInt(1,id);
				psSupr.execute();
			}
		
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete " + table.name());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Suppression d'un plat pr�sent dans un menu 
	public boolean deleteMenuPlat(int id_menu, int id_plat)  {
		String sql = "delete from menu_plat where mp_id_menu = ? and mp_id_plat = ?";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_menu);
			ps.setInt(2, id_plat);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete menu_plat");
			e.printStackTrace();
			res = false;
		}

		return res;
	}
	
	//Supression d'un groupe et remplacement de ce groupe par un autre groupe passer en  param�tre
	public boolean deleteGroupe(int id, int idRemp) {
		String sql = "delete from groupe where groupe_id = ? ";
		String updateMenuPlatSql = "update menu_plat set mp_id_groupe = ? where mp_id_groupe = ? ";
		String updatePlatSql = " update plat set plat_id_groupe = ? where plat_id_groupe = ?";
		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			
			ps = connection.prepareStatement(updateMenuPlatSql);
			ps.setInt(1, idRemp);
			ps.setInt(2, id);
			ps.execute();
			
			ps = connection.prepareStatement(updatePlatSql);
			ps.setInt(1, idRemp);
			ps.setInt(2, id);
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete menu_plat");
			e.printStackTrace();
			res = false;
		}

		return res;
	}
	
	//Fonction permettant de renvoyer les logs en fonction de l'administrateur
	public ArrayList<Map.Entry<Log, Admin>> getLog() {
		Table table1 = (Table) Log.class.getAnnotation(Table.class);
		Table table2 = (Table) Admin.class.getAnnotation(Table.class);
		String sql = "select " + table1.name() + ".*, " + table2.name() + ".* from " + table1.name() + "," + table2.name() + " where admin_id = log_id_admin";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Map.Entry<Log, Admin>> res = new ArrayList<Map.Entry<Log, Admin>>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin a = new Admin(rs.getInt("admin_id"), rs.getString("admin_user"), rs.getString("admin_password"));
				Log l = new Log(rs.getInt("log_id"), rs.getInt("log_id_admin"), rs.getTimestamp("log_date"));
				res.add(new AbstractMap.SimpleEntry<Log, Admin>(l, a));
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getLog " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction pemettant de r�cuperer un administrateur en fonction de son mot de passe et nom
	public ArrayList<Admin> getAdmin(String name, String password) {
		Table table = (Table) Admin.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where admin_user=? and admin_password=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Admin> res = new ArrayList<Admin>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin a = new Admin(rs.getInt("admin_id"), rs.getString("admin_user"), rs.getString("admin_password"));
				res.add(a);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getAdmin " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fontcion permettant de r�cuperer un groupe en fonction de son id
	public ArrayList<Groupe> getGroupe(int id) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where groupe_id=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Groupe> res = new ArrayList<Groupe>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Groupe g = new Groupe(rs.getInt("groupe_id"), rs.getString("groupe_nom"));
				res.add(g);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getGroupe " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de r�cuperer un Menu en fonction de son id
	public ArrayList<Menu> getMenu(int id) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where menu_id=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Menu> res = new ArrayList<Menu>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"),rs.getString("menu_description"),rs.getDouble("menu_prix"));
				res.add(m);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getMenu " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de r�cuperer un liste des Plat associer � un menu en fonction de l'id du menu
	public ArrayList<Map.Entry<Plat, Groupe>> getMenuPlat(int id) {
		Table table1 = (Table) Plat.class.getAnnotation(Table.class);
		Table table2 = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select " + table1.name() + ".*, " + table2.name() + ".* from menu_plat, " + table1.name() + ","
				+ table2.name() + " where mp_id_menu=? and mp_id_plat=plat_id and mp_id_groupe=groupe_id order by mp_id_groupe";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Map.Entry<Plat, Groupe>> res = new ArrayList<Map.Entry<Plat, Groupe>>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), new Photo(rs.getBlob("plat_photo")), rs.getInt("plat_id_groupe"));
				Groupe g = new Groupe(rs.getInt("groupe_id"), rs.getString("groupe_nom"));
				res.add(new AbstractMap.SimpleEntry<Plat, Groupe>(p, g));
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getMenu " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de recuperer un plat en fonction de l'id
	public ArrayList<Plat> getPlat(int id) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where plat_id=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), new Photo(rs.getBlob("plat_photo")), rs.getInt("plat_id_groupe"));
				res.add(p);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getPlat " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de recuperer le menu associ� � un plat
	public ArrayList<Menu> getPlatMenu(int id) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "select " + table.name() + ".* from menu_plat, " + table.name()
				+ " where mp_id_plat=? and mp_id_menu=menu_id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Menu> res = new ArrayList<Menu>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"),rs.getString("menu_description"),rs.getDouble("menu_prix"));
				res.add(m);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getMenu " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean open() {
		boolean res = false;
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			res = true;
		} catch (SQLException e) {
			System.out.println("Erreur de connexion à la base de données :");
			e.printStackTrace();
		}
		return res;
	}
	
	//Fonction permettant d'ajouter un log
	public boolean putLog(Log l) {
		Table table = (Table) Log.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name() + " (log_id_admin, log_date) values (?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, l.getLog_id_admin());
			ps.setTimestamp(2, l.getLog_date());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putLog " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant d'ajouter un admin
	public boolean putAdmin(Admin a) {
		Table table = (Table) Admin.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name() + " (admin_user, admin_password) values (?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, a.getAdmin_user());
			ps.setString(2, a.getAdmin_password());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putAdmin " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant d'ajouter un groupe
	public boolean putGroupe(Groupe g) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name() + " (groupe_nom) values (?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, g.getGroupe_nom());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putPlat " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant d'ajouter un Menu
	public boolean putMenu(Menu m) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name() + " (menu_nom,menu_description,menu_prix) values (?,?,?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, m.getMenu_nom());
			ps.setString(2, m.getMenu_description());
			ps.setDouble(3, m.getMenu_prix());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant de rajouter un plat dans un menu en fonction du groupe associ�
	public boolean putMenuPlat(int id_plat, int id_menu, int id_groupe) {
		String sql = "insert into menu_plat (mp_id_menu, mp_id_plat, mp_id_groupe) values (?, ?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_plat);
			ps.setInt(2, id_menu);
			ps.setInt(3, id_groupe);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putPlatMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant d'ajouter un plat
	public boolean putPlat(Plat p) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name()
				+ " (plat_nom, plat_description, plat_prix, plat_photo, plat_id_groupe) values (?, ?, ?, ?,?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getPlat_nom());
			ps.setString(2, p.getPlat_description());
			ps.setFloat(3, p.getPlat_prix());
			ps.setBlob(4, p.getPlat_photo().getBlob());
			ps.setInt(5, p.getPlat_id_groupe());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putPlat " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant de mettre � jour un admin
	public boolean updateAdmin(Admin a) {
		Table table = (Table) Admin.class.getAnnotation(Table.class);
		String sql = "update " + table.name() + " set admin_user = ?, admin_password = ? where admin_id = "
				+ a.getAdmin_id();

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, a.getAdmin_user());
			ps.setString(2, a.getAdmin_password());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updateAdmin " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant de mettre � jour un groupe
	public boolean updateGroupe(Groupe g) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "update " + table.name() + " set groupe_nom = ? where groupe_id = " + g.getGroupe_id();

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, g.getGroupe_nom());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updateGroupe " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant de mettre � jour un Menu
	public boolean updateMenu(Menu m) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "update " + table.name() + " set menu_nom = ?,menu_description = ?,menu_prix = ? where menu_id = " + m.getMenu_id();

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, m.getMenu_nom());
			ps.setString(2, m.getMenu_description());
			ps.setDouble(3, m.getMenu_prix());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updateMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	//Fonction permettant de mettre � jour un plat
	public boolean updatePlat(Plat p) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "update " + table.name()
				+ " set plat_nom = ?, plat_description = ?, plat_prix = ?, plat_photo = ? where plat_id = "
				+ p.getPlat_id();

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getPlat_nom());
			ps.setString(2, p.getPlat_description());
			ps.setFloat(3, p.getPlat_prix());
			ps.setBlob(4, p.getPlat_photo().getBlob());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updatePlat " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}
	
	//Fonction permettant de r�cuperer la liste de tout les groupes
	public ArrayList<Groupe> getGroupe() {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name();

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Groupe> res = new ArrayList<Groupe>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Groupe g = new Groupe(rs.getInt("groupe_id"), rs.getString("groupe_nom"));
				res.add(g);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getPlat " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de r�cuperer la liste de tout les menus
	public ArrayList<Menu> getMenu() {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name();

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Menu> res = new ArrayList<Menu>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"),rs.getString("menu_description"),rs.getDouble("menu_prix"));
				res.add(m);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getMenu " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de r�cuperer la liste de tout les plats
	//Le param�tre permet de r�cuperer ou non les images associ�es au plat
	public ArrayList<Plat> getPlat(boolean images) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select " + (images ? "*" : "plat_id, plat_nom, plat_description, plat_prix, plat_id_groupe") + " from " + table.name();

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo;
				if (images)
					photo = new Photo(rs.getBlob("plat_photo"));
				else
					photo = new Photo(new byte[10]);
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), photo, rs.getInt("plat_id_groupe"));
				res.add(p);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getPlat " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de recuperer un groupe en fonction de son nom
	public ArrayList<Groupe> getGroupe(String name) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where groupe_nom=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Groupe> res = new ArrayList<Groupe>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Groupe g = new Groupe(rs.getInt("groupe_id"), rs.getString("groupe_nom"));
				res.add(g);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getGroupe " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de recuperer un menu en fonction de son nom	
	public ArrayList<Menu> getMenu(String name) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where menu_nom=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Menu> res = new ArrayList<Menu>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"),rs.getString("menu_description"),rs.getDouble("menu_prix"));
				res.add(m);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getMenu " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	//Fonction permettant de recuperer un plat en fonction de son nom
	public ArrayList<Plat> getPlat(String name) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where plat_nom=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), new Photo(rs.getBlob("plat_photo")), rs.getInt("plat_id_groupe"));
				res.add(p);
			}
		} catch (Exception e) {
			System.out.println("Erreur Base.getPlat " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	
	
}
