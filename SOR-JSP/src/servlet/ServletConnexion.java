package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import bean.Admin;
import bean.Log;
import manager.Manager;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		request.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//SI la connexion fonctionne on redirige vers la page administration sinon on affiche un message d'erreur
		if (connexion(request,response)) {
			request.removeAttribute("error");
			response.sendRedirect("Administration");
		} else {
			request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
			request.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
		}
	}
	
	protected boolean connexion(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String login = request.getParameter("user");
		String mdp = request.getParameter("password");
		
		//Cryptage du mot de passe
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(mdp.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest)
				sb.append(String.format("%02x", b & 0xff));
			mdp = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//On récupère l'admin
		ArrayList<Admin> admins = Manager.getAdmin(login, mdp);
		HttpSession session = null;
		//Si l'admin est present on le connecte en ajouter une varriable de session
		if(admins.isEmpty()) {
			return false;
		} else {
			Admin a = admins.get(0);
			session = request.getSession();
			session.setAttribute("admin", a.getAdmin_user());
			Manager.putLog(new Log(a.getAdmin_id(), new Timestamp(System.currentTimeMillis())));
			return true;
		}
	}
}
