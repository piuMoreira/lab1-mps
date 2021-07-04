package business.services;

import business.services.reports.HTMLReportGenerator;
import business.services.reports.PDFReportGenerator;
import business.services.reports.ReportFormat;

public class GenerateReportService {

    public void execute(ReportFormat format, String userId, String outputFolderPath) throws Exception {
        if (format == ReportFormat.HTML) {
            HTMLReportGenerator reportGenerator = new HTMLReportGenerator(userId, outputFolderPath);
            reportGenerator.generateReport();
            return;
        }

        if (format == ReportFormat.PDF) {
            PDFReportGenerator reportGenerator = new PDFReportGenerator(userId, outputFolderPath);
            reportGenerator.generateReport();
            return;
        }

        throw new Exception("Invalid report format");
    }
}
