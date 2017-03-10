package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class ServletMenu
 */
@WebServlet("/Menu")
public class ServletMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("plats", null);
		//S'il n'y a pas de parametre ref on affiche par defaut la liste des menus
		//Sinon on affiche le menu passer en paramètre
		if(request.getParameter("ref")!=null){
			int id = Integer.parseInt(request.getParameter("ref"));
			ArrayList<Menu> menus = Manager.getMenu(id);
			Menu menu = null;
			if(menus.size() > 0) menu = menus.get(0);
			ArrayList<Map.Entry<Plat, Groupe>> pgList = Manager.getMenuPlat(id);
			
			request.setAttribute("plats", pgList);
			request.setAttribute("menu", menu);
			request.getServletContext().getRequestDispatcher("/WEB-INF/Menu.jsp").forward(request, response);
		}else{
			ArrayList<Menu> menus = Manager.getMenu();
			request.setAttribute("Menus", menus);
			request.getServletContext().getRequestDispatcher("/WEB-INF/Menus.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
