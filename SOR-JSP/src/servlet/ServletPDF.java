package servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

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
		
		Document document = new Document();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			addMetaData(document);
			addTitlePage(document);
			ArrayList<Plat> plats = Manager.getPlat(false);
			addContent(document, plats);
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
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 18,
                    Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12,
                    Font.NORMAL, BaseColor.RED);
    private static Font headFont = new Font(Font.FontFamily.HELVETICA, 16,
                    Font.BOLD);
    private static Font cellFont = new Font(Font.FontFamily.HELVETICA, 12,
                    Font.NORMAL);

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
		addEmptyLine(preface, 1);
		document.add(preface);
	}

	private static void addContent(Document document, ArrayList<Plat> plats) throws DocumentException {
		document.add(createTable(plats));
	}

	private static PdfPTable createTable(ArrayList<Plat> plats) throws BadElementException {
		
		PdfPTable table = new PdfPTable(3);

		table.setWidthPercentage(100);
		try {
			table.setWidths(new float[] { 3, 3, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

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

		for(Plat p : plats) {
			table.addCell(p.getPlat_nom());
			table.addCell(p.getPlat_description());
			table.addCell(p.getPlat_prix() + " €");
		}

		return table;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
