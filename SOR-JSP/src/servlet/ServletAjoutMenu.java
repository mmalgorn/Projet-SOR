package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

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
@WebServlet("/AjoutMenu")
public class ServletAjoutMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutMenu() {
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
		
		request.setAttribute("Plat", list);		
		request.setAttribute("Groupe", listGroupe);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutMenu.jsp").forward(request, response);
		
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

		if(request.getParameter("nom")!=null){
			nom = request.getParameter("nom");
		}
		if(request.getParameter("description")!=null){
			description = request.getParameter("description");
		}
		if(request.getParameter("prix")!=null){
			prix = Float.parseFloat(request.getParameter("prix"));
		}

		menu = new Menu(nom, description, prix);

		Manager.putMenu(menu);

		retMenu = Manager.getMenu(menu.getMenu_nom());

		if(retMenu.size()>0){
			int idMenu = retMenu.get(0).getMenu_id();
			ArrayList<Plat> plat = Manager.getPlat(false);
			ArrayList<Plat> platMenu = new ArrayList<Plat>();

			if(request.getParameter("i")!=null){
				nbPlat = Integer.parseInt(request.getParameter("i"));
			}

			for(int i = 0 ;i<=nbPlat;i++){
				if((request.getParameter(""+i)!=null)&&(request.getParameter("type"+i)!=null)){

					System.out.println("Plat : "+i+" "+request.getParameter(""+i));
					System.out.println("Groupe : "+request.getParameter("type"+i));
					
					Manager.putPlatMenu(idMenu,i, Integer.parseInt(request.getParameter("type"+i)));
				}
			}
		}else{
			//Menu pas ajouter
		}

	}

}
