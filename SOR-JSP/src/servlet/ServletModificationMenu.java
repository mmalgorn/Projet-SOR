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
		
		//Si l'id passer en paramètre est incorrecte on redirige vers la page d'ajout de menu
		//Sinon on récupère les informations associés au menu
		if(request.getParameter("id")!=null){
			
			menuPlat = Manager.getMenuPlat(Integer.parseInt(request.getParameter("id")));
			menus = Manager.getMenu(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("menu", menus.get(0));
			request.setAttribute("Plat", list);		
			request.setAttribute("Groupe", listGroupe);
			request.setAttribute("menuPlat", menuPlat);
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/ModifMenu.jsp").forward(request, response);

		}else{
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

		//Recupération des paramètres
		if(request.getParameter("nom")!=null){
			nom = request.getParameter("nom");
		}
		if(request.getParameter("description")!=null){
			description = request.getParameter("description");
		}
		if(request.getParameter("prix")!=null){
			prix = Float.parseFloat(request.getParameter("prix"));
		}

		//On récupère l'ancien menu et on le met à jour
		menu = Manager.getMenu(Integer.parseInt(request.getParameter("menu"))).get(0);
		
		menu.setMenu_description(description);
		menu.setMenu_nom(nom);
		menu.setMenu_prix(prix);
		
		Manager.updateMenu(menu);

		retMenu = Manager.getMenu(menu.getMenu_nom());

		//On update la table menuPLat en fonction des case cocher
		//Afin d'éviter les erreur on supprime tout les plats présents dans un menu avant de les rajouter
		if(retMenu.size()>0){
			int idMenu = retMenu.get(0).getMenu_id();

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
		}else{
			//Menu pas ajouter
		}

	}

}
