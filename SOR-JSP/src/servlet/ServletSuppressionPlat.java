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
 * Servlet implementation class ServletSuppression
 */
@WebServlet("/SuppressionPlat")
public class ServletSuppressionPlat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuppressionPlat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Plat> plats = Manager.getPlat(id);
			if (plats.isEmpty()) {
				request.setAttribute("error", "Erreur lors de la suppression du plat. Le plat n'existe pas.");
			} else {
				if (Manager.delete(Plat.class, id))
					request.setAttribute("success", "Le plat a été supprimé avec succès.");
				else
					request.setAttribute("error", "Erreur lors de la suppression du plat.");
			}
		}
		request.getRequestDispatcher("Plat").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
