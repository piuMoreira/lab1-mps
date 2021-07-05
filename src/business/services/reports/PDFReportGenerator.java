package business.services.reports;

public class PDFReportGenerator extends AReportGenerator{
    public PDFReportGenerator(String userId, String outputFolderPath) {
        super(userId, outputFolderPath);
    }

    @Override
    protected void generateHeader() {
        this.header = "pdf header";
    }

    @Override
    protected void generateBody() {
        this.body = "pdf body";
    }

    @Override
    public void writeToFile() {
        System.out.println(String.format("Printing PDF to %s", this.outputFolderPath));
    }
}
