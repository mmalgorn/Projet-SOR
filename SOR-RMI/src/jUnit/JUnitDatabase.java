package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import bean.Groupe;
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

	@Before
	public void setUp() throws Exception {
		db = new Database();
		db.open();
		
		m1 = new Menu("TestM1","Test",1000);
		p1 = new Plat("TestP1","Test",1000, new Photo(new byte[10]), 0);
		p2 = new Plat("TestP2","Test",1000, new Photo(new byte[10]), 0);
		g1 = new Groupe(100, "TestG1");
		g2 = new Groupe(101,"TestG2");
	}


	@Test
	public void testDatabase() {
		fail("Not yet implemented");
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
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
		db.delete(Menu.class, plat.get(0).getPlat_id());
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
		db.delete(Plat.class, plat1.get(0).getPlat_id());
			
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
		
	}

	@Test
	public void testGetLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroupeInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuPlat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlatInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlatMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpen() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutGroupe() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutMenuPlat() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutPlat() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGroupe() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePlat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroupe() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlatBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroupeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlatString() {
		fail("Not yet implemented");
	}

}
