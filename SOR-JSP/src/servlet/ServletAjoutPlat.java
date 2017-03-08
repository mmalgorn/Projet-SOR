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
import bean.Groupe;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletAjoutPlat
 */
@WebServlet("/AjoutPlat")
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
		// TODO Auto-generated method stub
		
		ArrayList<Groupe> list = Manager.getGroupe();
		
		request.setAttribute("groupes", list);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutPlat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("nom");
		String description = request.getParameter("description");
		float prix = Float.parseFloat(request.getParameter("prix"));
		String photo = request.getParameter("photo");
		int id_groupe = Integer.parseInt(request.getParameter("type"));
		
		ArrayList<Plat> listTest = Manager.getPlat(name);
		
		System.out.println(listTest.size());
		if(listTest.size()==0){
			Plat plat = new Plat(name,description,prix,photo,id_groupe);
			System.out.println(plat.getPlat_nom());
			System.out.println(plat.getPlat_description());
			System.out.println(plat.getPlat_id_groupe());
			System.out.println(plat.getPlat_prix());

			if(Manager.putPlat(plat)){
				request.setAttribute("insert", 1);	
			}
			else {
				request.setAttribute("insert", 0);
			}
			
		}else{
			request.setAttribute("present", 1);
			
		}
		
		doGet(request,response);
				
	}

}