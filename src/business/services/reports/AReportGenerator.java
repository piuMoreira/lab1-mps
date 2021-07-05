package business.services.reports;

public abstract class AReportGenerator {
    protected String outputFolderPath;
    private String userId;
    protected String header;
    protected String body;
    private String reportData;

    public AReportGenerator(String userId, String outputFolderPath) {
        this.outputFolderPath = outputFolderPath;
        this.userId = userId;
    }

    public final void generateReport() {
        this.generateData();
        this.generateHeader();
        this.generateBody();
        this.writeToFile();
    }

    private void generateData() {
        this.reportData = String.format("Some data for %s", this.userId);
    }

    protected abstract void generateHeader();
    protected abstract void generateBody();
    protected abstract void writeToFile();

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOutputFolderPath() {
		return outputFolderPath;
	}

	public String getUserId() {
		return userId;
	}
    
    
    
}
