package business.services;

import business.services.reports.HTMLReportGenerator;
import business.services.reports.PDFReportGenerator;

public class ReportAdapter {

	public void toPDFReport(HTMLReportGenerator htmlReport) {
		PDFReportGenerator newPDFReport = new PDFReportGenerator(htmlReport.getUserId(), htmlReport.getOutputFolderPath());
		
		String[] oldHeader = htmlReport.getHeader().split(" ");
		oldHeader[0] = "pdf";
		String newHeader = String.join(" ", oldHeader);
		
		String[] oldBody = htmlReport.getBody().split(" ");
		oldBody[0] = "pdf";
		String newBody = String.join(" ", oldBody);
		
		newPDFReport.setHeader(newHeader);
		newPDFReport.setBody(newBody);
		
		newPDFReport.writeToFile();		
	}
	
	public void toHTMLReport(HTMLReportGenerator pdfReport) {
		HTMLReportGenerator newHTMLReport = new HTMLReportGenerator(pdfReport.getUserId(), pdfReport.getOutputFolderPath());
		
		String[] oldHeader = pdfReport.getHeader().split(" ");
		oldHeader[0] = "html";
		String newHeader = String.join(" ", oldHeader);
		
		String[] oldBody = pdfReport.getBody().split(" ");
		oldBody[0] = "html";
		String newBody = String.join(" ", oldBody);
		
		newHTMLReport.setHeader(newHeader);
		newHTMLReport.setBody(newBody);
		
		newHTMLReport.writeToFile();		
	}
	
}
