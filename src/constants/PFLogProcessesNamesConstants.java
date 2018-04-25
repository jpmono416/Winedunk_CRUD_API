package constants;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PFLogProcessesNamesConstants {
	
	public PFLogProcessesNamesConstants() {

		Gson gson = new Gson();
		try {
			try {
			
				File configClassFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
				String filePath = configClassFile.getParentFile().getAbsolutePath() + File.separator;
				JsonReader reader = new JsonReader(new FileReader(filePath + "pflogprocesses.json"));
				
				PFLogProcessesNamesConstants logProcessesNamesJSON = gson.fromJson(reader, PFLogProcessesNamesConstants.class);
				this.logProductFeedProcessName = logProcessesNamesJSON.getLogProductFeedProcessName();
				this.logProductsProcessName = logProcessesNamesJSON.getLogProductsProcessName();
				
			} catch (Exception e) {
				this.logProductFeedProcessName = "ProductFeedsPocessor";
				this.logProductsProcessName = "ProductsProcessor";				
			}
			
		}
		catch (Exception e) {
			
		}
		
	}

	private String logProductFeedProcessName;
	public String getLogProductFeedProcessName() { return logProductFeedProcessName; }
	public void setLogProductFeedProcessName(String logProductFeedProcessName) { this.logProductFeedProcessName = logProductFeedProcessName; }
	
	private String logProductsProcessName;
	public String getLogProductsProcessName() { return logProductsProcessName; }
	public void setLogProductsProcessName(String logProductsProcessName) { this.logProductsProcessName = logProductsProcessName; }
	
}

