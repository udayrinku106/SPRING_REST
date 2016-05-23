package com.javahonk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class GeneratePDF {

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(
			Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static Document createPDF(String file) throws MalformedURLException,
			IOException {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			// add the images
			addCompanyLogo(document);

			addMetaData(document);
			

			addALine(document);

			addTitlePage(document);

			createTable(document);

			document.close();

		} catch (FileNotFoundException | DocumentException e) {

			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on "
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);
	}

	private static void addTitlePage(Document document)
			throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("PDF Report", TIME_ROMAN));

		document.add(preface);

	}

	private static void addALine(Document document) throws DocumentException {
		LineSeparator ls = new LineSeparator();
		document.add(new Chunk(ls));
	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void addCompanyLogo(Document document)
			throws DocumentException, MalformedURLException, IOException {
		Image companyLogo = Image.getInstance("D:\\WALLPAPERS\\logo.jpg");
		/*
		 * companyLogo.setAbsolutePosition(30, 50);
		 */
		companyLogo.setAlignment(Element.ALIGN_LEFT);
		companyLogo.scalePercent(40);
		document.add(companyLogo);
	}

	private static void createTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Last Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Test"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for (int i = 0; i < 5; i++) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell("Sony");
			table.addCell("Vaio");
			table.addCell("Success");
		}

		document.add(table);
	}

}
