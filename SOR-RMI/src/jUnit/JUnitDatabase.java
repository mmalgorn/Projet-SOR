package jUnit;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import bean.Admin;
import bean.Groupe;
import bean.Log;
import bean.Menu;
import bean.Photo;
import bean.Plat;
import database.Database;

public class JUnitDatabase {

	Database db;
	Menu m1;
	Plat p1;
	Plat p2;
	Groupe g1;
	Groupe g2;
	Admin a1;

	@Before
	public void setUp() throws Exception {
		db = new Database();
		db.open();
		
		m1 = new Menu("TestM1","Test",1000);
		p1 = new Plat("TestP1","Test",1000, new Photo(new byte[10]), 0);
		p2 = new Plat("TestP2","Test",1000, new Photo(new byte[10]), 0);
		g1 = new Groupe(100, "TestG1");
		g2 = new Groupe(101,"TestG2");
		a1 = new Admin(100, "Testa", "Test");
		
	}




	@Test
	public void testDelete() {
		db.putMenu(m1);
		ArrayList<Menu> menu = db.getMenu(m1.getMenu_nom());
		db.delete(Menu.class, menu.get(0).getMenu_id());
		ArrayList<Menu> retMenu = db.getMenu(menu.get(0).getMenu_id());
		assertTrue(retMenu.size()==0); 
		
		db.putPlat(p1);
		ArrayList<Plat> plat = db.getPlat(p1.getPlat_nom());
		db.delete(Plat.class, plat.get(0).getPlat_id());
		ArrayList<Menu> retPlat = db.getMenu(menu.get(0).getMenu_id());
		assertTrue(retPlat.size()==0); 
		
	}

	@Test
	public void testDeleteMenuPlat() {
		db.putMenu(m1);
		db.putPlat(p1);
		db.putPlat(p2);
		
		ArrayList<Menu> menu = db.getMenu(m1.getMenu_nom());
		ArrayList<Plat> plat1 = db.getPlat(p1.getPlat_nom());
		ArrayList<Plat> plat2 = db.getPlat(p2.getPlat_nom());
		
		db.putMenuPlat(menu.get(0).getMenu_id(), plat1.get(0).getPlat_id(), 100);
		db.putMenuPlat(menu.get(0).getMenu_id(), plat2.get(0).getPlat_id(), 100);
		
		db.deleteMenuPlat(menu.get(0).getMenu_id(), plat1.get(0).getPlat_id());
		db.deleteMenuPlat(menu.get(0).getMenu_id(), plat2.get(0).getPlat_id());
		
		ArrayList<Entry<Plat,Groupe>> menuPlat = db.getMenuPlat(menu.get(0).getMenu_id());
		
		assertTrue(menuPlat.size()==0);
		
		db.delete(Menu.class,menu.get(0).getMenu_id());
		db.delete(Plat.class, plat1.get(0).getPlat_id());
		db.delete(Plat.class, plat2.get(0).getPlat_id());
			
	}

	@Test
	public void testDeleteGroupe() {
		db.putGroupe(g1);
		
		ArrayList<Groupe> groupe = db.getGroupe(g1.getGroupe_nom());
		p1.setPlat_id_groupe(groupe.get(0).getGroupe_id());
		p2.setPlat_id_groupe(groupe.get(0).getGroupe_id());
		
		db.putMenu(m1);
		db.putPlat(p1);
		db.putPlat(p2);
		
		ArrayList<Menu> menu = db.getMenu(m1.getMenu_nom());
		ArrayList<Plat> plat1 = db.getPlat(p1.getPlat_nom());
		ArrayList<Plat> plat2 = db.getPlat(p2.getPlat_nom());
		
		db.putMenuPlat( menu.get(0).getMenu_id(),plat1.get(0).getPlat_id(), groupe.get(0).getGroupe_id());
		db.putMenuPlat( menu.get(0).getMenu_id(),plat2.get(0).getPlat_id(), groupe.get(0).getGroupe_id());
		
		db.deleteGroupe(groupe.get(0).getGroupe_id(), 1);
		
		ArrayList<Groupe> retGroupe = db.getGroupe(g1.getGroupe_nom());
		assertTrue(retGroupe.size()==0);
		
		ArrayList<Plat> retPlat = db.getPlat(p1.getPlat_nom());
		
		assertTrue(retPlat.get(0).getPlat_id_groupe()==1);
		
		ArrayList<Entry<Plat,Groupe>> retMenuPlat = db.getMenuPlat(menu.get(0).getMenu_id());
		
		for(int i =0; i<retMenuPlat.size()-1;i++){
			assertTrue(retMenuPlat.get(i).getValue().getGroupe_id()==1);
		}
		
		db.deleteMenuPlat(menu.get(0).getMenu_id(), plat1.get(0).getPlat_id());
		db.deleteMenuPlat(menu.get(0).getMenu_id(), plat2.get(0).getPlat_id());
		db.delete(Plat.class, plat1.get(0).getPlat_id());
		db.delete(Plat.class, plat2.get(0).getPlat_id());
		db.delete(Menu.class, menu.get(0).getMenu_id());
	}

	

	@Test
	public void testGetAdmin() {
		db.putAdmin(a1);
		
		ArrayList<Admin> admin = db.getAdmin(a1.getAdmin_user(), a1.getAdmin_password());
		
		assertTrue(admin.size()>0);
		assertTrue(admin.get(0).getAdmin_user().equals(a1.getAdmin_user()));
		assertTrue(admin.get(0).getAdmin_password().equals(a1.getAdmin_password()));
		
		db.delete(Admin.class, admin.get(0).getAdmin_id());
	}

	@Test
	public void testGetGroupeInt() {
		
		db.putGroupe(g1);
		
		ArrayList<Groupe> g = db.getGroupe(g1.getGroupe_nom());
		
		ArrayList<Groupe> groupe = db.getGroupe(g.get(0).getGroupe_id());
		
		assertTrue(groupe.get(0).getGroupe_nom().equals(g1.getGroupe_nom()));
		
		db.delete(Groupe.class, groupe.get(0).getGroupe_id());
	}

	@Test
	public void testGetMenuInt() {
		
		db.putMenu(m1);
		
		ArrayList<Menu> m = db.getMenu(m1.getMenu_nom());
		
		ArrayList<Menu> menu = db.getMenu(m.get(0).getMenu_id());
		
		assertTrue(menu.get(0).getMenu_nom().equals(m1.getMenu_nom()));
		
		db.delete(Menu.class, menu.get(0).getMenu_id());
	}


	@Test
	public void testGetPlatInt() {
		
		db.putPlat(p1);
		
		ArrayList<Plat> p = db.getPlat(p1.getPlat_nom());
		
		ArrayList<Plat> plat = db.getPlat(p.get(0).getPlat_id());
		
		assertTrue(plat.get(0).getPlat_nom().equals(p1.getPlat_nom()));
		
		db.delete(Plat.class, plat.get(0).getPlat_id());
	}

	

	@Test
	public void testPutAdmin() {
		
		db.putAdmin(a1);
		ArrayList<Admin> admin = db.getAdmin(a1.getAdmin_user(), a1.getAdmin_password());
		assertTrue(admin.get(0).getAdmin_user().equals(a1.getAdmin_user()));
		assertTrue(admin.get(0).getAdmin_password().equals(a1.getAdmin_password()));
		
		db.delete(Admin.class, admin.get(0).getAdmin_id());
	}

	@Test
	public void testPutGroupe() {
		db.putGroupe(g1);
		ArrayList<Groupe> groupe = db.getGroupe(g1.getGroupe_nom());
		assertTrue(groupe.get(0).getGroupe_nom().equals(g1.getGroupe_nom()));
		
		db.delete(Groupe.class, groupe.get(0).getGroupe_id());
		
	}

	@Test
	public void testPutMenu() {
		db.putMenu(m1);
		ArrayList<Menu> menu = db.getMenu(m1.getMenu_nom());
		assertTrue(menu.get(0).getMenu_nom().equals(m1.getMenu_nom()));
		assertTrue(menu.get(0).getMenu_description().equals(m1.getMenu_description()));
		assertTrue(menu.get(0).getMenu_prix()==m1.getMenu_prix());
		db.delete(Menu.class, menu.get(0).getMenu_id());
	}


	@Test
	public void testPutPlat() {
		db.putPlat(p1);
		ArrayList<Plat> plat = db.getPlat(p1.getPlat_nom());
		assertTrue(plat.get(0).getPlat_nom().equals(p1.getPlat_nom()));
		assertTrue(plat.get(0).getPlat_description().equals(p1.getPlat_description()));
		assertTrue(plat.get(0).getPlat_prix()==p1.getPlat_prix());
		db.delete(Plat.class, plat.get(0).getPlat_id());
	}

	@Test
	public void testUpdateAdmin() {
		db.putAdmin(a1);
		
		ArrayList<Admin> admin = db.getAdmin(a1.getAdmin_user(), a1.getAdmin_password());
		
		admin.get(0).setAdmin_user("New");
		
		db.updateAdmin(admin.get(0));
		
		ArrayList<Admin> retAdmin = db.getAdmin(admin.get(0).getAdmin_user(),admin.get(0).getAdmin_password());
		
		assertTrue(retAdmin.size()>0);
		
		db.delete(Admin.class, retAdmin.get(0).getAdmin_id());
		
		
	}

	@Test
	public void testUpdateGroupe() {
		db.putGroupe(g1);
		
		ArrayList<Groupe> groupe = db.getGroupe(g1.getGroupe_nom());
		
		groupe.get(0).setGroupe_nom("New");
		
		db.updateGroupe(groupe.get(0));
		
		ArrayList<Groupe> retGroupe = db.getGroupe(groupe.get(0).getGroupe_nom());
		
		assertTrue(retGroupe.size()>0);
						
		db.delete(Groupe.class, retGroupe.get(0).getGroupe_id());
		
	}

	@Test
	public void testUpdateMenu() {
		db.putMenu(m1);
		
		ArrayList<Menu> menu = db.getMenu(m1.getMenu_nom());
		
		menu.get(0).setMenu_nom("New");
		
		db.updateMenu(menu.get(0));
		
		ArrayList<Menu> retMenu = db.getMenu(menu.get(0).getMenu_nom());
		
		assertTrue(retMenu.size()>0);
						
		db.delete(Menu.class, retMenu.get(0).getMenu_id());
	}

	@Test
	public void testUpdatePlat() {
		db.putPlat(p1);
		
		ArrayList<Plat> plat = db.getPlat(p1.getPlat_nom());
		
		plat.get(0).setPlat_nom("New");
		
		db.updatePlat(plat.get(0));
		
		ArrayList<Plat> retPlat = db.getPlat(plat.get(0).getPlat_nom());
		
		assertTrue(retPlat.size()>0);
						
		db.delete(Plat.class, retPlat.get(0).getPlat_id());
	}



}
