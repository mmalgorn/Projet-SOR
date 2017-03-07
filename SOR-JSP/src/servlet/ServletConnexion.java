package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import manager.Manager;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
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
		// TODO Auto-generated method stub
		request.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		connexion(request,response);
		response.sendRedirect("ServletAdministration");
	}
	
	protected void connexion(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		Manager manager = (Manager) request.getSession().getAttribute("Manager");
		String login = request.getParameter("user");
		String mdp = request.getParameter("password");
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(mdp.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			mdp = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		System.out.println(login+" "+mdp);
		
		ArrayList<Admin> admins = manager.getAdmin(login, mdp);
		
		System.out.println(admins.size());
		HttpSession session = null;
		System.out.println("Before admin");
		if(!admins.isEmpty()){
			session = request.getSession();
			session.setAttribute("admin", admins.get(0));
			System.out.println("admin");
		}
		
		//Admin a = (Admin)session.getAttribute("admin");
		//System.out.println(a.getAdmin_user());

		
		
		
		
	}

}
