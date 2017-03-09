package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Menu;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletSuppressionMenu
 */
@WebServlet("/SuppressionMenu")
public class ServletSuppressionMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuppressionMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Menu> menus = Manager.getMenu(id);
			if (menus.isEmpty()) {
				request.setAttribute("error", "Erreur lors de la suppression du plat. Le plat n'existe pas.");
			} else {
				
				
				if (Manager.delete(Menu.class, id))
					request.setAttribute("success", "Le plat a été supprimé avec succès.");
				else
					request.setAttribute("error", "Erreur lors de la suppression du plat.");
			}
		}
		request.getRequestDispatcher("Menu").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
