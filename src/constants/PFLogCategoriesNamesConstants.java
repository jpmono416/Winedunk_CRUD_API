package constants;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PFLogCategoriesNamesConstants {
	
	public PFLogCategoriesNamesConstants() {
		
		Gson gson = new Gson();
		try {
			
			File configClassFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
			String filePath = configClassFile.getParentFile().getAbsolutePath() + File.separator;
			JsonReader reader = new JsonReader(new FileReader(filePath + "pflogcategories.json"));
			
			PFLogCategoriesNamesConstants generalSettingsConstants = gson.fromJson(reader, PFLogCategoriesNamesConstants.class);
			this.logcategoryPROCESSSTARTEDname = generalSettingsConstants.getLogcategoryPROCESSSTARTEDname();
			this.logcategoryPRODUCTPROCESSINGSTARTEDname = generalSettingsConstants.getLogcategoryPRODUCTPROCESSINGSTARTEDname();
			this.logcategoryPRODUCTPROCESSINGname = generalSettingsConstants.getLogcategoryPRODUCTPROCESSINGname();
			this.logcategoryPRODUCTPROCESSINGFINISHEDname = generalSettingsConstants.getLogcategoryPRODUCTPROCESSINGFINISHEDname();
			this.logcategoryPROCESSFINISHEDname = generalSettingsConstants.getLogcategoryPROCESSFINISHEDname();
			this.logcategoryPRODUCTSTANDARDIZATIONSTARTEDname = generalSettingsConstants.getLogcategoryPRODUCTSTANDARDIZATIONSTARTEDname();
			this.logcategoryPRODUCTSTANDARDIZATIONFINISHEDname = generalSettingsConstants.getLogcategoryPRODUCTSTANDARDIZATIONFINISHEDname();
		}
		catch (Exception e) {
			this.logcategoryPROCESSSTARTEDname = "Process has started";
			this.logcategoryPRODUCTPROCESSINGSTARTEDname = "Product processing has started"; 
			this.logcategoryPRODUCTPROCESSINGname = "Product processing";
			this.logcategoryPRODUCTPROCESSINGFINISHEDname = "Product processing has finished";
			this.logcategoryPROCESSFINISHEDname = "Process has finished";
			this.logcategoryPRODUCTSTANDARDIZATIONSTARTEDname = "Product standardization has started";
			this.logcategoryPRODUCTSTANDARDIZATIONFINISHEDname = "Product standardization has finished";
		}
		
	}

	private String logcategoryPROCESSSTARTEDname;
	public String getLogcategoryPROCESSSTARTEDname() { return logcategoryPROCESSSTARTEDname; }
	public void setLogcategoryPROCESSSTARTEDname(String logcategoryPROCESSSTARTEDname) { this.logcategoryPROCESSSTARTEDname = logcategoryPROCESSSTARTEDname; }
		
	private String logcategoryPRODUCTSTANDARDIZATIONSTARTEDname;
	public String getLogcategoryPRODUCTSTANDARDIZATIONSTARTEDname() { return logcategoryPRODUCTSTANDARDIZATIONSTARTEDname; }
	public void setLogcategoryPRODUCTSTANDARDIZATIONSTARTEDname(String logcategoryPRODUCTSTANDARDIZATIONSTARTEDname) { this.logcategoryPRODUCTSTANDARDIZATIONSTARTEDname = logcategoryPRODUCTSTANDARDIZATIONSTARTEDname; }
	
	private String logcategoryPRODUCTSTANDARDIZATIONFINISHEDname;
	public String getLogcategoryPRODUCTSTANDARDIZATIONFINISHEDname() { return logcategoryPRODUCTSTANDARDIZATIONFINISHEDname; }
	public void setLogcategoryPRODUCTSTANDARDIZATIONFINISHEDname(String logcategoryPRODUCTSTANDARDIZATIONFINISHEDname) { this.logcategoryPRODUCTSTANDARDIZATIONFINISHEDname = logcategoryPRODUCTSTANDARDIZATIONFINISHEDname; }
	
	private String logcategoryPRODUCTPROCESSINGSTARTEDname;
	public String getLogcategoryPRODUCTPROCESSINGSTARTEDname() { return logcategoryPRODUCTPROCESSINGSTARTEDname; }
	public void setLogcategoryPRODUCTPROCESSINGSTARTEDname(String logcategoryPRODUCTPROCESSINGSTARTEDname) { this.logcategoryPRODUCTPROCESSINGSTARTEDname = logcategoryPRODUCTPROCESSINGSTARTEDname; }
	
	private String logcategoryPRODUCTPROCESSINGname;
	public String getLogcategoryPRODUCTPROCESSINGname() { return logcategoryPRODUCTPROCESSINGname; }
	public void setLogcategoryPRODUCTPROCESSINGname(String logcategoryPRODUCTPROCESSINGname) { this.logcategoryPRODUCTPROCESSINGname = logcategoryPRODUCTPROCESSINGname; }
	
	private String logcategoryPRODUCTPROCESSINGFINISHEDname;
	public String getLogcategoryPRODUCTPROCESSINGFINISHEDname() { return logcategoryPRODUCTPROCESSINGFINISHEDname; }
	public void setLogcategoryPRODUCTPROCESSINGFINISHEDname(String logcategoryPRODUCTPROCESSINGFINISHEDname) { this.logcategoryPRODUCTPROCESSINGFINISHEDname = logcategoryPRODUCTPROCESSINGFINISHEDname; }
	
	private String logcategoryPROCESSFINISHEDname;
	public String getLogcategoryPROCESSFINISHEDname() { return logcategoryPROCESSFINISHEDname; }
	public void setLogcategoryPROCESSFINISHEDname(String logcategoryPROCESSFINISHEDname) { this.logcategoryPROCESSFINISHEDname = logcategoryPROCESSFINISHEDname; }
	
}
