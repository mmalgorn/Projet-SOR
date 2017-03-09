package servlet;

import java.io.IOException;
import java.rmi.RemoteException;
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
 * Servlet implementation class ServletAjoutModifGroupe
 */
@WebServlet("/AjoutModifGroupe")
public class ServletAjoutModifGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	int id_groupe;
	
    public ServletAjoutModifGroupe() {
        super();
        id_groupe=-1;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);		
		
		if(request.getParameter("id")!=null)
			id_groupe = Integer.parseInt(request.getParameter("id"));
			ArrayList<Groupe> groupe = Manager.getGroupe(id_groupe);
		if(groupe.size()>0){
			
			request.setAttribute("grp", groupe.get(0));
			this.id_groupe=groupe.get(0).getGroupe_id();
		}
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/AjoutGroupe.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		String name = request.getParameter("nom");
		
		if(request.getParameter("id")!=null)
			id_groupe = Integer.parseInt(request.getParameter("id"));
		
		int ret = -10;
		if(id_groupe>=0){
			ret = modification(name);
			if(ret == 1) request.setAttribute("successM", 1);
			else request.setAttribute("absent", 1);
			
		}else{
			ret = creation(name);
			if(ret==1) request.setAttribute("successC", 1);
			else request.setAttribute("present", 1);
					
		}
		
		this.id_groupe=-1;		
		doGet(request,response);
			
	}

	private int creation(String name) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Creation");
		ArrayList<Groupe> listTest = Manager.getGroupe(name);
		System.out.println(name);
		System.out.println(listTest.size());
		if(listTest.size()==0){
			Groupe groupe = new Groupe(0, name);
			if(Manager.putGroupe(groupe)){
				return 1;
			}			
		}
		return 0;
		
	}

	private int modification(String name) throws RemoteException {
		System.out.println("modification");
		ArrayList<Groupe> listTest = Manager.getGroupe(id_groupe);
		if(listTest.size()>0){
			Groupe groupe = new Groupe(id_groupe,name);
						
			if(Manager.updateGroupe(groupe)){
				return 1;
			}
			
			
		}
		return 0;
		

		
	}


}
