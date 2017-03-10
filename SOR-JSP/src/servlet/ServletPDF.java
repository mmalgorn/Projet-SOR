package servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bean.Plat;
import bean.Groupe;
import manager.Manager;

/**
 * Servlet implementation class ServletPDF
 */
@WebServlet("/PDF")
public class ServletPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPDF() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("admin") == null)
			request.getServletContext().getRequestDispatcher("/WEB-INF/NotConnected.jsp").forward(request, response);
		
		Document document = new Document();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			addMetaData(document);
			addTitlePage(document);
			
			Map<Groupe, ArrayList<Plat>> groupePlat = new HashMap<Groupe, ArrayList<Plat>>();
			String id = request.getParameter("groupe");
			ArrayList<Plat> plats = Manager.getPlat(false);
			ArrayList<Groupe> groupes = Manager.getGroupe();
			for(Groupe g : groupes) {
				if (!id.equals("all") && g.getGroupe_id() != Integer.parseInt(request.getParameter("groupe"))) continue;
				groupePlat.put(g, new ArrayList<Plat>());
				for(int i=plats.size()-1; i>=0; i--) {
					if (plats.get(i).getPlat_id_groupe() == g.getGroupe_id())
						groupePlat.get(g).add(plats.remove(i));
				}
			}
			
			createTable(document, groupePlat);
			document.close();
			
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
            os.flush();
            os.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 18,
                    Font.BOLD);
    private static Font tableNameFont = new Font(Font.FontFamily.HELVETICA, 14,
                    Font.BOLD);

	private static void addMetaData(Document document) {
		document.addTitle("Plats");
		document.addSubject("Plats");
		document.addKeywords("Java, PDF, Samba, Palet");
		document.addAuthor("La samba du palet");
		document.addCreator("La samba du palet");
	}

	private static void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		preface.add(new Paragraph("Nos Plats", catFont));
		document.add(preface);
	}


	private static void createTable(Document document, Map<Groupe, ArrayList<Plat>> groupePlat) throws DocumentException {
		
		for(Groupe g : groupePlat.keySet()) {
			if (groupePlat.get(g).isEmpty()) continue;

			Paragraph para = new Paragraph(g.getGroupe_nom(), tableNameFont);
			para.setLeading(30);
			document.add(para);
			para = new Paragraph(" ");
			para.setLeading(10);
			document.add(para);
			
			PdfPTable table = new PdfPTable(3);
	
			table.setWidthPercentage(100);
			table.setWidths(new float[] { 3, 3, 1 });
	
			PdfPCell c1 = new PdfPCell(new Phrase("Nom du plat"));
			c1.setColspan(1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			c1 = new PdfPCell(new Phrase("Description"));
			c1.setColspan(1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			c1 = new PdfPCell(new Phrase("Prix"));
			c1.setColspan(1);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			
			for(Plat p : groupePlat.get(g)) {
				table.addCell(p.getPlat_nom());
				table.addCell(p.getPlat_description());
				table.addCell(p.getPlat_prix() + " €");
			}
			
			document.add(table);
		}

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
