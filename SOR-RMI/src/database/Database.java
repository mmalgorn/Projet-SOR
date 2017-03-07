package database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.security.acl.Group;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import annotation.Table;
import bean.*;

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
		System.out.println(db.delete(Admin.class, 2));
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

	public boolean delete(Class<?> c, int id) {
		Table table = (Table) c.getAnnotation(Table.class);
		String sql = "delete from " + table.name() + " where " + table.name() + "_id = ?";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete " + table.name());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	public boolean deleteGroupePlat(int id_groupe, int id_plat) throws RemoteException {
		String sql = "delete from groupe_plat where mp_id_groupe = ? and mp_id_plat = ?";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_groupe);
			ps.setInt(1, id_plat);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete menu_plat");
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	public boolean deleteMenuPlat(int id_menu, int id_plat) throws RemoteException {
		String sql = "delete from menu_plat where mp_id_menu = ? and mp_id_plat = ?";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_menu);
			ps.setInt(1, id_plat);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.delete menu_plat");
			e.printStackTrace();
			res = false;
		}

		return res;
	}

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

	public ArrayList<Plat> getGroupePlat(Groupe g) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select plat.* from groupe_plat, " + table.name() + " where gp_id_groupe=? and gp_id_plat=plat_id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, g.getGroupe_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), rs.getString("plat_photo"));
				res.add(p);
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
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"));
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

	public ArrayList<Plat> getMenuPlat(Menu m) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select " + table.name() + ".* from menu_plat, " + table.name()
				+ " where mp_id_menu=? and mp_id_plat=plat_id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, m.getMenu_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), rs.getString("plat_photo"));
				res.add(p);
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

	public ArrayList<Plat> getPlat(String nom) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where plat_nom=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, nom);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), rs.getString("plat_photo"));
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

	public ArrayList<Plat> getPlat(String comparateur, float prix) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name() + " where plat_prix" + comparateur + "?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setFloat(1, prix);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), rs.getString("plat_photo"));
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

	public ArrayList<Groupe> getPlatGroupe(Plat p) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "select " + table.name() + ".* from groupe_plat, " + table.name()
				+ " where gp_id_plat=? and gp_id_groupe=groupe_id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Groupe> res = new ArrayList<Groupe>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, p.getPlat_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Groupe g = new Groupe(rs.getInt("groupe_id"), rs.getString("groupe_nom"));
				res.add(g);
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

	public ArrayList<Menu> getPlatMenu(Plat p) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "select " + table.name() + ".* from menu_plat, " + table.name()
				+ " where mp_id_plat=? and mp_id_menu=menu_id";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Menu> res = new ArrayList<Menu>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, p.getPlat_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"));
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

	public boolean putGroupePlat(Plat p, Groupe g) {
		String sql = "insert into groupe_plat (mp_id_groupe, mp_id_plat) values (?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, g.getGroupe_id());
			ps.setInt(2, p.getPlat_id());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putGroupeMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	public boolean putMenu(Menu m) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name() + " (menu_name) values (?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, m.getMenu_nom());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	public boolean putMenuPlat(Plat p, Menu m) {
		String sql = "insert into menu_plat (mp_id_menu, mp_id_plat) values (?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setInt(1, m.getMenu_id());
			ps.setInt(2, p.getPlat_id());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putPlatMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	public boolean putPlat(Plat p) {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "insert into " + table.name()
				+ " (plat_name, plat_description, plat_prix, plat_photo) values (?, ?, ?, ?)";

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getPlat_nom());
			ps.setString(2, p.getPlat_description());
			ps.setFloat(3, p.getPlat_prix());
			ps.setString(4, p.getPlat_photo());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.putPlat " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

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

	public boolean updateGroupe(Groupe g) {
		Table table = (Table) Groupe.class.getAnnotation(Table.class);
		String sql = "update " + table.name() + " set groupe_nom = ? where menu_id = " + g.getGroupe_id();

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

	public boolean updateMenu(Menu m) {
		Table table = (Table) Menu.class.getAnnotation(Table.class);
		String sql = "update " + table.name() + " set menu_nom = ? where menu_id = " + m.getMenu_id();

		boolean res = true;
		try {
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, m.getMenu_nom());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updateMenu " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}

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
			ps.setString(4, p.getPlat_photo());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erreur Base.updatePlat " + e.getMessage());
			e.printStackTrace();
			res = false;
		}

		return res;
	}
	
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
				Menu m = new Menu(rs.getInt("menu_id"), rs.getString("menu_nom"));
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

	public ArrayList<Plat> getPlat() {
		Table table = (Table) Plat.class.getAnnotation(Table.class);
		String sql = "select * from " + table.name();

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Plat> res = new ArrayList<Plat>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Plat p = new Plat(rs.getInt("plat_id"), rs.getString("plat_nom"), rs.getString("plat_description"),
						rs.getFloat("plat_prix"), rs.getString("plat_photo"));
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
