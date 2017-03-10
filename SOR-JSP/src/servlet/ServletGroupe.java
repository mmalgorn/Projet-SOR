package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groupe;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletGroupe
 */
@WebServlet("/Groupe")
public class ServletGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGroupe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Message d'erreur si non connecter
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		
		ArrayList<Groupe> list = Manager.getGroupe();
		request.setAttribute("Groupe", list);
		request.getServletContext().getRequestDispatcher("/WEB-INF/Groupe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
