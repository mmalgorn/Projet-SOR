package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import bean.Menu;
import manager.Manager;

/**
 * Servlet implementation class ServletSuppressionGroupe
 */
@WebServlet("/SuppressionGroupe")
public class ServletSuppressionGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuppressionGroupe() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		id = -1;
		ArrayList<Groupe> groupe = Manager.getGroupe();
		request.setAttribute("Groupe", groupe);
		if(request.getParameter("id")!=null)
			id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id",id);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/SuppressionGroupe.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		if (id!=-1 && request.getParameter("type") != null) {
			
			int idRemp = Integer.parseInt(request.getParameter("type"));
			ArrayList<Groupe> groupe = Manager.getGroupe(id);
			
			if (groupe.isEmpty()) {
				request.setAttribute("error", "Erreur lors de la suppression du plat. Le plat n'existe pas.");
			} else {
				if (Manager.deleteGroupe(id,idRemp))
					request.setAttribute("success", "Le plat a été supprimé avec succès.");
				else
					request.setAttribute("error", "Erreur lors de la suppression du plat.");
			}
		}
		
		request.getRequestDispatcher("Groupe").forward(request, response);
	}

}
