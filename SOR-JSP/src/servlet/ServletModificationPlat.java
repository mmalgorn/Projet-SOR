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
@WebServlet("/ModificationPlat")
public class ServletModificationPlat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	int id_plat;
	
    public ServletModificationPlat() {
        super();
        id_plat=-1;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Groupe> list = Manager.getGroupe();
		
		request.setAttribute("groupes", list);
		
		//int id_plat = (int) request.getAttribute("plat");
		
		//!!!!!!!!!!!!!! A enlever
		int id_plat = 29;
		
		ArrayList<Plat> plat = Manager.getPlat(id_plat);
		if(plat.size()>0){
			
			request.setAttribute("plat", plat.get(0));
			this.id_plat=plat.get(0).getPlat_id();
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/ModifPlat.jsp").forward(request, response);

		}else{
			request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutPlat.jsp").forward(request, response);

		}
		
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
		
		System.out.println(id_plat);
		ArrayList<Plat> listTest = Manager.getPlat(id_plat);
		
		System.out.println(listTest.size());
		if(listTest.size()>0){
			Plat plat = new Plat(name,description,prix,photo,id_groupe);
			plat.setPlat_id(id_plat);
			System.out.println(plat.getPlat_nom());
			System.out.println(plat.getPlat_description());
			System.out.println(plat.getPlat_id_groupe());
			System.out.println(plat.getPlat_prix());

			
			if(Manager.updatePlat(plat)){
				request.setAttribute("insert", 1);	
			}
			else {
				request.setAttribute("insert", 0);
			}
			
		}else{
			request.setAttribute("present", 1);
			
		}
		
		this.id_plat=-1;		
		doGet(request,response);
				
	}

}
