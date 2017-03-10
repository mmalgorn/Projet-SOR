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
@WebServlet("/AjoutPlat")
@MultipartConfig
public class ServletAjoutPlat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutPlat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		ArrayList<Groupe> list = Manager.getGroupe();	
		request.setAttribute("groupes", list);
		if (request.getAttribute("insert") != null) {
			if ((int) request.getAttribute("insert") == 0) request.setAttribute("error", "Erreur lors de la création du plat.");
			else if ((int) request.getAttribute("insert") == 2) request.setAttribute("error", "Certains champs ne sont pas valides.");
			else request.setAttribute("success", "Plat ajouté avec succès.");
		}
		if (request.getAttribute("present") != null) {
			request.setAttribute("error", "Plat déja présent. Veuillez recommencer.");
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutPlat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		String name = request.getParameter("nom");
		String description = request.getParameter("description");
		float prix = Float.parseFloat(request.getParameter("prix"));
		int id_groupe = Integer.parseInt(request.getParameter("type"));
		Part filePart = request.getPart("photo");
		byte[] buffer = new byte[(int) filePart.getSize()];
		InputStream is = filePart.getInputStream();
		while(is.read(buffer) != -1);
		
		ArrayList<Plat> listTest = Manager.getPlat(name);
		
		System.out.println(listTest.size());
		if (listTest.size() == 0) {
			Plat plat = new Plat(name, description, prix, new Photo(buffer), id_groupe);
			System.out.println(plat.getPlat_nom());
			System.out.println(plat.getPlat_description());
			System.out.println(plat.getPlat_id_groupe());
			System.out.println(plat.getPlat_prix());
			
			if (!plat.checkFields()) {
				request.setAttribute("insert", 2);
				doGet(request, response);
				return;
			}

			if (Manager.putPlat(plat)) {
				request.setAttribute("insert", 1);
			} else {
				request.setAttribute("insert", 0);
			}
		} else {
			request.setAttribute("present", 1);
		}
		
		doGet(request,response);			
	}

}
