package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Menu;
import bean.Photo;
import bean.Plat;
import manager.Manager;

/**
 * Servlet implementation class ServletImage
 */
@WebServlet("/Image")
public class ServletImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("ref"));
		
		ArrayList<Plat> plats = Manager.getPlat(id);
		if(!plats.isEmpty()) {
			Photo p = plats.get(0).getPlat_photo();
			if (p.getImg() != null) {
				response.setContentType("image/jpeg");
				response.setContentLength(p.getImg().length);
				response.getOutputStream().write(p.getImg());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
