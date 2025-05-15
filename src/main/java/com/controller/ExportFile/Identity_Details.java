package com.controller.ExportFile;



import java.util.Arrays;

import java.util.List;

import java.util.Map;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



import org.springframework.web.servlet.view.document.AbstractPdfView;



import com.controller.DateWithTimestamp.DateWithTimeStampController;

import com.lowagie.text.Chunk;

import com.lowagie.text.Document;

import com.lowagie.text.Element;

import com.lowagie.text.Font;

import com.lowagie.text.FontFactory;

import com.lowagie.text.Image;

import com.lowagie.text.PageSize;

import com.lowagie.text.Paragraph;

import com.lowagie.text.Rectangle;

import com.lowagie.text.pdf.PdfPCell;

import com.lowagie.text.pdf.PdfPTable;

import com.lowagie.text.pdf.PdfWriter;



public class Identity_Details extends AbstractPdfView



{



	String Type = "";

	List<String> TH;

	String Heading = "";

	String username = "";

	String foot="";

	final static String USER_PASSWORD = "user";

	final static String OWNER_PASSWORD = "owner";

	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	

	public Identity_Details(String Type,List<String> TH,String Heading,String username,String foot){

		this.Type = Type;

		this.TH = TH;

		this.Heading = Heading;

		this.username = username;

		this.foot=foot;

	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		document.open();

		document.setPageCount(1);

		

			document.setPageSize(PageSize.A5.rotate()); // set document landscape

	

		



		super.buildPdfMetadata(model, document, request);

	}



	@Override

	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,

			HttpServletRequest request, HttpServletResponse response) throws Exception {



		

		

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();

		String file_name = datetimestamp.currentDateWithTimeStampString();

		

		// TODO Auto-generated method stub

		response.setContentType("application/pdf");

		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");

		

		@SuppressWarnings("unchecked")

		List<Map<String, Object>> aList = (List<Map<String, Object>>) model.get("userList"); 

		Map<String, Object> l1 =( Map<String, Object>) aList.get(0);

		int colunmSize = l1.size(); // get Colunm Size

	

		float[] relativeWidths;

		relativeWidths = new float[1];

		Arrays.fill(relativeWidths, 0, 1, 1);

		

		

		

		PdfPTable table = new PdfPTable(1);

		Paragraph p1;

	

		table.setWidthPercentage(100);

		table.getDefaultCell().setPadding(5);

		

		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

	

		

		Font fontTablesubHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);

	



			Map<String, Object> l = (Map<String, Object>) aList.get(0);

			

					

					

						String str="No: "+l.get("id_card_no").toString()+"\nRank: "+l.get("description").toString()+"\n";

						str+="Name: "+l.get("name").toString()+"\nDate of Birth: "+l.get("date_of_birth").toString()+"\n";

						p1 = new Paragraph(str);

						p1.setAlignment(Element.ALIGN_LEFT);

						

						

						

						



		PdfPTable table1 = new PdfPTable(3);



		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		table1.setWidthPercentage(100);

		table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph p;

		PdfPCell cellhead;

		cellhead = new PdfPCell();

		cellhead.setColspan(3);

		cellhead.setBorder(Rectangle.NO_BORDER);

		

		Chunk p89 = new Chunk("IDENTITY CARD DETAILS\n\n", fontTablesubHeading);

		p89.setUnderline(0.1f, -2f);

		p=new Paragraph(p89);

		p.setAlignment(Element.ALIGN_CENTER);

		cellhead.addElement(p);

	

		cellhead.setPadding(10);

		table1.addCell(cellhead);

		PdfPCell cellimage = new PdfPCell();

		try {

			Image idimage = Image.getInstance(l.get("path").toString());

			idimage.scaleAbsoluteHeight(150);

			idimage.scaleAbsoluteWidth(150);



			

			cellimage.setBorder(Rectangle.NO_BORDER);

			cellimage.addElement(idimage);

		} catch (Exception e) {

			cellimage.addElement(new Paragraph("No Image"));

		}



		table1.addCell(cellimage);

		

		PdfPCell cellData;

		cellData = new PdfPCell();

		cellData.setColspan(2);

		cellData.setBorder(Rectangle.NO_BORDER);

		cellData.addElement(p1);

		table1.addCell(cellData);

		

		



	

		

		PdfPTable MstTable = new PdfPTable(1);

		MstTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		MstTable.setWidthPercentage(100);

		MstTable.getDefaultCell().setBorder(Rectangle.BOX);

		MstTable.addCell(table1);

		document.add(MstTable);

		

		



	

		

		

		super.buildPdfMetadata(model, document, request);

	}







	

}







