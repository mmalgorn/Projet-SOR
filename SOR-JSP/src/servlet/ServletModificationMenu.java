package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import bean.Menu;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletAjoutMenu
 */
@WebServlet("/ModificationMenu")
public class ServletModificationMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificationMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		ArrayList<Plat> list = Manager.getPlat(false);
		ArrayList<Groupe> listGroupe = Manager.getGroupe();
		ArrayList<Menu> menus = null;
		ArrayList<Entry<Plat,Groupe>> menuPlat = null;
		
		int id_menu = -1;
		
		if (request.getParameter("id") != null) id_menu = Integer.parseInt(request.getParameter("id"));
		else if (request.getAttribute("id") != null) id_menu = (int) request.getAttribute("id");
		
		System.out.println(id_menu);
		
		if (request.getAttribute("insert") != null) {
			if ((int) request.getAttribute("insert") == 0) request.setAttribute("error", "Erreur lors de la modification du menu.");
			else if ((int) request.getAttribute("insert") == 2) request.setAttribute("error", "Certains champs ne sont pas valides.");
			else request.setAttribute("success", "Menu modifié avec succès.");
		}
		
		//Si l'id passer en param�tre est incorrecte on redirige vers la page d'ajout de menu
		//Sinon on r�cup�re les informations associ�s au menu
		if(id_menu != -1) {
			menuPlat = Manager.getMenuPlat(id_menu);
			menus = Manager.getMenu(id_menu);
			request.setAttribute("menu", menus.get(0));
			request.setAttribute("Plat", list);		
			request.setAttribute("Groupe", listGroupe);
			request.setAttribute("menuPlat", menuPlat);
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/ModifMenu.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("AjoutMenu").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		int nbPlat = 0;
		String nom= null;
		String description= null;
		float prix = 0;
		Menu menu = null;
		ArrayList<Menu> retMenu;
		ArrayList<Entry<Plat,Groupe>> menuPlat = null;

		//Recup�ration des param�tres
		if(request.getParameter("nom")!=null){
			nom = request.getParameter("nom");
		}
		if(request.getParameter("description")!=null){
			description = request.getParameter("description");
		}
		if(request.getParameter("prix")!=null){
			prix = Float.parseFloat(request.getParameter("prix"));
		}

		int id_menu = Integer.parseInt(request.getParameter("menu"));
		
		//On r�cup�re l'ancien menu et on le met � jour
		menu = Manager.getMenu(id_menu).get(0);
		
		menu.setMenu_description(description);
		menu.setMenu_nom(nom);
		menu.setMenu_prix(prix);
		
		request.setAttribute("id", id_menu);
		
		if (!menu.checkFields()) {
			request.setAttribute("insert", 1);
			doGet(request, response);
			return;
		}
		
		Manager.updateMenu(menu);

		retMenu = Manager.getMenu(menu.getMenu_nom());

		//On update la table menuPLat en fonction des case cocher
		//Afin d'�viter les erreur on supprime tout les plats pr�sents dans un menu avant de les rajouter
		if(retMenu.size() > 0){
			int idMenu = retMenu.get(0).getMenu_id();
			menuPlat = Manager.getMenuPlat(idMenu);

			if(request.getParameter("i")!=null){
				nbPlat = Integer.parseInt(request.getParameter("i"));
			}

			for(int j=0;j<menuPlat.size();j++){
				Manager.deleteMenuPlat(idMenu, menuPlat.get(j).getKey().getPlat_id());
			}
			
			for(int i = 0 ;i<=nbPlat;i++){
				if((request.getParameter(""+i)!=null)&&(request.getParameter("type"+i)!=null)){

					Manager.putPlatMenu(idMenu,i, Integer.parseInt(request.getParameter("type"+i)));
				}
			}
			
			request.setAttribute("insert", 1);
		} else {
			request.setAttribute("present", 1);
		}
		
		request.setAttribute("id", id_menu);
		doGet(request, response);
	}

}
