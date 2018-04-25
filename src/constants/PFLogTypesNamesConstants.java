package constants;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PFLogTypesNamesConstants {
	
	public PFLogTypesNamesConstants() {
		
		Gson gson = new Gson();
		try {
			
			File configClassFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
			String filePath = configClassFile.getParentFile().getAbsolutePath() + File.separator;
			JsonReader reader = new JsonReader(new FileReader(filePath + "pflogtypes.json"));
			
			PFLogTypesNamesConstants logTypesNamesConstants = gson.fromJson(reader, PFLogTypesNamesConstants.class);
			this.logtypeERRORname = logTypesNamesConstants.getLogtypeERRORname();
			this.logtypeWARNINGname = logTypesNamesConstants.getLogtypeWARNINGname();
			this.logtypeINFORMATIONname = logTypesNamesConstants.getLogtypeINFORMATIONname();

		}
		catch (Exception e) {
			this.logtypeERRORname = "Error";
			this.logtypeWARNINGname = "Warning";
			this.logtypeINFORMATIONname = "Information";	
		}
		
	}

	private String logtypeERRORname;
	public String getLogtypeERRORname() { return logtypeERRORname; }
	public void setLogtypeERRORname(String logtypeERRORname) { this.logtypeERRORname = logtypeERRORname; }
	
	private String logtypeWARNINGname;
	public String getLogtypeWARNINGname() { return logtypeWARNINGname; }
	public void setLogtypeWARNINGname(String logtypeWARNINGname) { this.logtypeWARNINGname = logtypeWARNINGname; }
	
	private String logtypeINFORMATIONname;
	public String getLogtypeINFORMATIONname() { return logtypeINFORMATIONname; }
	public void setLogtypeINFORMATIONname(String logtypeINFORMATIONname) { this.logtypeINFORMATIONname = logtypeINFORMATIONname; }

}
