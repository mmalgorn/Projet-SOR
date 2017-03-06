package database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import bean.Groupe;
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
		Map<Object, ArrayList<Object>> hm = db.lire(Groupe.class, Plat.class);
		for (Object o : hm.keySet()) {
			System.out.println(((Groupe)o).getGroupe_nom());
			for (Object o1 : hm.get(o)) {
				System.out.println("\t" + ((Plat)o1).getPlat_nom());
			}
		}
		db.close();
	}

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
					if (m == null) throw new NoSuchMethodException();
					if (m.getParameterTypes()[0] == Integer.class) {
						Integer i = rs.getInt(f.getName());
						m.invoke(o, i);
					} else if (m.getParameterTypes()[0] == String.class) {
						String s = rs.getString(f.getName());
						m.invoke(o, s);
					} else {
						System.out.println("type = inconnu");
					}
				}
				objects.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	public Map<Object, ArrayList<Object>> lire(Class c1, Class c2) {
		Table table1 = (Table) c1.getAnnotation(Table.class);
		Table table2 = (Table) c2.getAnnotation(Table.class);
		String prefix = table1.name().substring(0, 1) + table2.name().substring(0, 1);
		String sql = "select " + table1.name() + ".*, " + table2.name() + ".* ";
		sql += "from " + table1.name() + ", " + table2.name() + ", " + table1.name() + "_" + table2.name();
		sql += " where " + prefix + "_id_" + table1.name() + "=" + table1.name() + "_id";
		sql += " and " + prefix + "_id_" + table2.name() + "=" + table2.name() + "_id";
		Field[] fields1 = c1.getDeclaredFields();
		Field[] fields2 = c2.getDeclaredFields();

		Map<Object, ArrayList<Object>> objects = new HashMap<Object, ArrayList<Object>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Object o1 = c1.newInstance();
				Object o2 = c2.newInstance();
				for (Field f : fields1) {
					String nomMethode = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

					Method m = null;
					for (Method method : c1.getMethods())
						if (method.getName().equals(nomMethode)) m = method;
					if (m == null) throw new NoSuchMethodException();
					if (m.getParameterTypes()[0] == Integer.class) {
						Integer i = rs.getInt(f.getName());
						m.invoke(o1, i);
					} else if (m.getParameterTypes()[0] == String.class) {
						String s = rs.getString(f.getName());
						m.invoke(o1, s);
					} else {
						System.out.print(" type = inconnu");
					}
				}
				for (Field f : fields2) {
					String nomMethode = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

					Method m = null;
					for (Method method : c2.getMethods())
						if (method.getName().equals(nomMethode)) m = method;
					if (m == null) throw new NoSuchMethodException();
					if (m.getParameterTypes()[0] == Integer.class) {
						Integer i = rs.getInt(f.getName());
						m.invoke(o2, i);
					} else if (m.getParameterTypes()[0] == String.class) {
						String s = rs.getString(f.getName());
						m.invoke(o2, s);
					} else {
						System.out.println("type = inconnu");
					}
				}
				boolean exists = false;
				for (Object o : objects.keySet()) {
					if (o1.equals(o)) {
						o1 = o;
						exists = true;
					}
				}
				if (!exists) objects.put(o1, new ArrayList<Object>());
				objects.get(o1).add(o2);
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
}