package com.controller.ExportFile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;



@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ExportFileController {

	
}

class DownloadPdf extends AbstractPdfView {
	String Type = "";
	List<String> TH;
	DownloadPdf(String Type,List<String> TH){
		this.Type = Type;
		this.TH = TH;
	}
	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Image logo = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo =  request.getRealPath("/")+"admin\\login\\dgis-logo.png";
			logo = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(20);
		logo.scaleAbsoluteWidth(20);
		logo.scalePercent(30);
		Chunk chunk = new Chunk(logo, 0, -4);

		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String indian_Army =  request.getRealPath("/")+"admin\\login\\indian_Army.jpg";
			logo2 = Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(20);
		logo2.scaleAbsoluteWidth(20);
		logo2.scalePercent(22);
		Chunk chunk2 = new Chunk(logo2, 10, -4);

		Phrase p = new Phrase();
		p.add(chunk);
		p.add("MANAGEMENT INFORMATION SYSTEM AND ORGANISATION");
		p.add(chunk2);

		HeaderFooter header = new HeaderFooter(p, false);
		// header.setAlignment(Element.ALIGN_CENTER);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(1);
		document.setHeader(header);

		HeaderFooter footer = new HeaderFooter(new Phrase("Restricted"), false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);

		document.setPageCount(1);
		
		if(Type.equals("L")) {
			document.setPageSize(PageSize.A4.rotate()); // set document landscape
		}
		//document.setPageSize(PageSize.A4.rotate()); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"user_list.pdf\"");
		
		
		
		
		PdfPTable table = new PdfPTable(7);
		Paragraph p;

		table.setWidthPercentage(100);
		table.setWidths(new int[] {1, 3, 3, 3 , 3,3,3 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
	//	List<String> l1 = aList.get(0);
		//int counmSize = l1.size(); // get Colunm Size

		
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table.addCell(p);
		}
		
		
		
		/*p = new Paragraph("USERNAME",fontTableHeading);
		table.addCell(p);

		p = new Paragraph("FIRST NAME",fontTableHeading);
		table.addCell(p);

		p = new Paragraph("LAST NAME",fontTableHeading);
		table.addCell(p);

		p = new Paragraph("ADDRESS",fontTableHeading);
		table.addCell(p);
		
		p = new Paragraph("PIN",fontTableHeading);
		table.addCell(p);
		
		p = new Paragraph("PHONE",fontTableHeading);
		table.addCell(p);*/
		table.setHeaderRows(1); // table first row will be repeated in all pages

		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			for(int j = 0;j<l.size();j++) {
				table.addCell(l.get(j));
			}
		}
		
		/*for (List<String> list : aList) {
			table.addCell(list.get(0));
			table.addCell(list.get(1));
			table.addCell(list.get(2));
			table.addCell(list.get(3));
			table.addCell(list.get(4));
			table.addCell(list.get(5));
			table.addCell(list.get(6));
		}*/
		
		PdfPTable table1 = new PdfPTable(1);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(table);
		table1.setTableEvent(new ImageBackgroundEvent(request));
		table1.addCell(cell1);

		document.add(table1);
		
		super.buildPdfMetadata(model, document, request);
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;
		
		ImageBackgroundEvent(HttpServletRequest request){
			this.request = request;
		}
		
		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,PdfContentByte[] canvases) {
			String ip = "";
			if (request != null) {
		        ip = request.getHeader("X-FORWARDED-FOR");
		        if (ip == null || "".equals(ip)) {
		            ip = request.getRemoteAddr();
		        }
		    }
			Date now = new Date();
			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
			String watermark = " Generated by Admin on "+dateString+" with IP " +ip ;
			
			Image img = null;
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.lightGray);
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
			graphics.drawString(watermark+watermark,0, 20);

			try {
				try {
					img = com.lowagie.text.Image.getInstance(bufferedImage, null);
				} catch (IOException e) {
				}
			} catch (BadElementException e) {
			}
			this.image = img;

			try {

				

				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
				// Portrait Page size 700 * 523
				// Landscape page size 453 * 770

				int tableWidth = (int) table.getTotalWidth();
				int first = 0;
				if (tableWidth == 523) {
					first = 750;
				}
				if (tableWidth == 770) {
					first = 500;
				}

				int loop = (int) table.getRowHeight(0);
				int last = first - (int) table.getRowHeight(0);
				while (first > last) {
					image.setAbsolutePosition(30, first);
					cb.addImage(image, false);
					first -= 30;
				}
			} catch (DocumentException e) {
				throw new ExceptionConverter(e);
			}
		}
	}
}