package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.Admin;
import bean.Groupe;
import bean.Photo;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletAjoutPlat
 */
@WebServlet("/ModificationPlat")
@MultipartConfig
public class ServletModificationPlat extends HttpServlet {
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ServletModificationPlat() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		int id_plat = -1;
		
		if (request.getParameter("id") != null) id_plat = Integer.parseInt(request.getParameter("id"));
		else if (request.getAttribute("id") != null) id_plat = (int) request.getAttribute("id");
		
		if (request.getAttribute("insert") != null) {
			if ((int) request.getAttribute("insert") == 0) request.setAttribute("error", "Erreur lors de la modification du plat.");
			else if ((int) request.getAttribute("insert") == 2) request.setAttribute("error", "Certains champs ne sont pas valides.");
			else if ((int) request.getAttribute("insert") == 3) request.setAttribute("error", "Une erreur est survenue.");
			else request.setAttribute("success", "Plat modifié avec succès.");
		}

		//On r�cup�re le plat et on sauvegarde l'id du plat passer en param�tre afin de le retrouver dans le Post
		
		ArrayList<Groupe> list = Manager.getGroupe();
		request.setAttribute("groupes", list);
		if (request.getParameter("id") != null) id_plat = Integer.parseInt(request.getParameter("id"));
		else if (request.getAttribute("id") != null) id_plat = (int) request.getAttribute("id");
		System.out.println(id_plat);
		ArrayList<Plat> plat = Manager.getPlat(id_plat);
		System.out.println(plat.size());
		
		//SI la plat n'est pas pr�sent en base on renvoi vers la page AjoutPLat
		if (plat.size() > 0) {
			request.setAttribute("plat", plat.get(0));
			request.getServletContext().getRequestDispatcher("/WEB-INF/ModifPlat.jsp").forward(request, response);
		} else {
			request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutPlat.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		int id_plat = -1;
		try {
			id_plat = Integer.parseInt(request.getParameter("plat_id"));
		} catch(NumberFormatException e) {
			request.setAttribute("insert", 3);
			doGet(request, response);
			return;
		}
		
		request.setAttribute("id", id_plat);
		String name = (request.getParameter("nom") ==  null ? "" : request.getParameter("nom"));
		String description = (request.getParameter("description") == null ? "" : request.getParameter("description"));
		float prix = (request.getParameter("prix") == null ? null : Float.parseFloat(request.getParameter("prix")));
		int id_groupe = (request.getParameter("type") == null ? null : Integer.parseInt(request.getParameter("type")));
		Part filePart = request.getPart("photo");
		byte[] buffer = new byte[(int) filePart.getSize()];
		InputStream is = filePart.getInputStream();
		while (is.read(buffer) != -1)

		System.out.println(id_plat);
		ArrayList<Plat> listTest = Manager.getPlat(id_plat);
		
		//iso-8859-1 vers UTF-8
		name = new String(name.getBytes ("iso-8859-1"), "UTF-8");
		description = new String(description.getBytes ("iso-8859-1"), "UTF-8");

		System.out.println(listTest.size());
		if (!listTest.isEmpty()) {
			Photo p;
			
			if (buffer.length > 0) p = new Photo(buffer);
			else p = listTest.get(0).getPlat_photo();
			
			Plat plat = new Plat(name, description, prix, p, id_groupe);
			plat.setPlat_id(id_plat);
			
			if (!plat.checkFields()) {
				request.setAttribute("insert", 2);
				doGet(request, response);
				return;
			}

			if (Manager.updatePlat(plat)) request.setAttribute("insert", 1);
			else request.setAttribute("insert", 0);
		} else {
			request.setAttribute("present", 1);
		}
		
		doGet(request, response);
	}

}
