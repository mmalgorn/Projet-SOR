package database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Array;

import annotation.Table;
import bean.Admin;

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

	public Database() {
		rb = ResourceBundle.getBundle(fData);
		url = rb.getString("url");
		user = rb.getString("user");
		pwd = rb.getString("pwd");
		connection = null;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean enregistrer(Object o) {
		boolean res = false;

		Class c = o.getClass();
		System.out.println("classe = " + c.getName());

		Table table = (Table) c.getAnnotation(Table.class);
		System.out.println("table " + table.name());

		String sql = "insert into " + table.name();

		Field[] fields = c.getDeclaredFields();
		int count = 0;
		String ln = ""; // liste des noms
		String lv = ""; // liste des valeurs
		for (Field f : fields) {
			System.out.println("attribut " + f.getName());
			if (count > 0) {
				ln += ",";
				lv += ",";
			}
			ln += f.getName();
			lv += "?";
			count++;
		}

		sql += "(" + ln + ") values (" + lv + ")";

		System.out.println("sql = " + sql);

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			for (int i = 1; i <= fields.length; i++) {
				Field f = fields[i - 1];
				System.out.print("nom = " + f.getName());
				String nomMethode = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				Method m = c.getMethod(nomMethode);
				System.out.print(" valeur = " + m.invoke(o));
				if (m.getReturnType() == Integer.class) {
					System.out.print(" type = Integer");
					ps.setInt(i, (Integer) m.invoke(o));
				} else if (m.getReturnType() == String.class) {
					System.out.print(" type = String");
					ps.setString(i, (String) m.invoke(o));
				} else {
					System.out.print(" type = inconnu");
				}
				System.out.println();
			}

			res = ps.execute();
		} catch (Exception e) {
			System.out.println("Erreur Base.enregistrer " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (ps != null) ps.close();
		} catch (Exception e) {
		}

		return res;
	}

	public ArrayList<Object> lire(Class c) {
		Table table = (Table) c.getAnnotation(Table.class);
		String sql = "select * from " + table.name();
		System.out.println(sql);
		Field[] fields = c.getDeclaredFields();

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Object> objects = new ArrayList<Object>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object o = c.newInstance();
				for (Field f : fields) {
					String nomMethode = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

					Method m = null;
					for (Method method : c.getMethods())
						if (method.getName().equals(nomMethode)) m = method;
					if(m == null) throw new NoSuchMethodException();
					System.out.println(nomMethode);
					if (m.getParameterTypes()[0] == Integer.class) {
						Integer i = rs.getInt(f.getName());
						m.invoke(o, i);
					} else if (m.getParameterTypes()[0] == String.class) {
						String s = rs.getString(f.getName());
						System.out.println(s);
						m.invoke(o, s);
					} else {
						System.out.print(" type = inconnu");
					}
				}
				objects.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	public boolean open() {
		boolean res = false;
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			res = true;
		} catch (SQLException e) {
			System.out.println("Erreur de connexion � la base de donn�es :");
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String[] args) {
		Database db = new Database();

		db.open();
		ArrayList<Object> a = db.lire(Admin.class);
		// System.out.println(((Admin)a.get(0)).getAdmin_password());
		db.close();
	}
}