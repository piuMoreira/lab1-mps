package business.services.reports;

public class HTMLReportGenerator extends AReportGenerator{
    public HTMLReportGenerator(String userId,String outputFolderPath) {
        super(userId, outputFolderPath);
    }

    @Override
    protected void generateHeader() {
        this.header = "html header";
    }

    @Override
    protected void generateBody() {
        this.body = "html body";
    }

    @Override
    public void writeToFile() {
        System.out.println(String.format("Printing HTML to %s", this.outputFolderPath));
    }
    
    
}
