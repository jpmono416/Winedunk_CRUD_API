package constants;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class PFLogGeneralSettingsConstants {
	
	public PFLogGeneralSettingsConstants() {
		
		Gson gson = new Gson();
		try {
			
			File configClassFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
			String filePath = configClassFile.getParentFile().getAbsolutePath() + File.separator;
			JsonReader reader = new JsonReader(new FileReader(filePath + "pfloggeneralsettings.json"));
			
			PFLogGeneralSettingsConstants generalSettingsConstants = gson.fromJson(reader, PFLogGeneralSettingsConstants.class);
			this.rotateLogsDays = generalSettingsConstants.getRotateLogsDays();
			this.productFeedsPocessorBeginDescription = generalSettingsConstants.getProductFeedsPocessorBeginDescription();
			this.productFeedsPocessorEndDescription = generalSettingsConstants.getProductFeedsPocessorEndDescription();
			this.productsProcessorBeginDescription = generalSettingsConstants.getProductsProcessorBeginDescription();
			this.productsProcessorEndDescription = generalSettingsConstants.getProductsProcessorEndDescription();
			this.processingProductBeginDescription = generalSettingsConstants.getProcessingProductBeginDescription();
			this.processingProductEndDescription = generalSettingsConstants.getProcessingProductEndDescription();
			this.standardizingProductBeginDescription = generalSettingsConstants.getStandardizingProductBeginDescription();
			this.standardizingProductEndDescription = generalSettingsConstants.getStandardizingProductEndDescription();
			
		}
		catch (Exception e) {
			this.rotateLogsDays = 30; // in case JSON fails we will be deleting files older than 30 days
			this.productFeedsPocessorBeginDescription = "ProductFeedsPocessor has started"; 
			this.productFeedsPocessorEndDescription = "ProductFeedsPocessor has finished"; 
			this.productsProcessorBeginDescription = "ProductsProcessor has started";
			this.productsProcessorEndDescription = "ProductsProcessor has finished";
			this.processingProductBeginDescription = "Processing product has started";
			this.processingProductEndDescription = "Processing product has finished";
			this.standardizingProductBeginDescription = "Product standardization has started";
			this.standardizingProductEndDescription = "Product standardization has finished";
		}
		
	}

	private Integer rotateLogsDays;
	public Integer getRotateLogsDays() { return rotateLogsDays; }
	public void setRotateLogsDays(Integer rotateLogsDays) { this.rotateLogsDays = rotateLogsDays; }

	private String productFeedsPocessorBeginDescription;
	public String getProductFeedsPocessorBeginDescription() { return productFeedsPocessorBeginDescription; }
	public void setProductFeedsPocessorBeginDescription(String productFeedsPocessorBeginDescription) { this.productFeedsPocessorBeginDescription = productFeedsPocessorBeginDescription; }

	private String productFeedsPocessorEndDescription;
	public String getProductFeedsPocessorEndDescription() { return productFeedsPocessorEndDescription; }
	public void setProductFeedsPocessorEndDescription(String productFeedsPocessorEndDescription) { this.productFeedsPocessorEndDescription = productFeedsPocessorEndDescription; }
	
	private String productsProcessorBeginDescription;
	public String getProductsProcessorBeginDescription() { return productsProcessorBeginDescription; }
	public void setProductsProcessorBeginDescription(String productsProcessorBeginDescription) { this.productsProcessorBeginDescription = productsProcessorBeginDescription; }
	
	private String productsProcessorEndDescription;
	public String getProductsProcessorEndDescription() { return productsProcessorEndDescription; }
	public void setProductsProcessorEndDescription(String productsProcessorEndDescription) { this.productsProcessorEndDescription = productsProcessorEndDescription; }

	private String processingProductBeginDescription;
	public String getProcessingProductBeginDescription() { return processingProductBeginDescription; }
	public void setProcessingProductBeginDescription(String processingProductBeginDescription) { this.processingProductBeginDescription = processingProductBeginDescription; }
	
	private String processingProductEndDescription;
	public String getProcessingProductEndDescription() { return processingProductEndDescription; }
	public void setProcessingProductEndDescription(String processingProductEndDescription) { this.processingProductEndDescription = processingProductEndDescription; }
	

	private String standardizingProductBeginDescription;
	public String getStandardizingProductBeginDescription() { return standardizingProductBeginDescription; }
	public void setStandardizingProductBeginDescription(String standardizingProductBeginDescription) { this.standardizingProductBeginDescription = standardizingProductBeginDescription; }
	
	private String standardizingProductEndDescription;
	public String getStandardizingProductEndDescription() { return standardizingProductEndDescription; }
	public void setStandardizingProductEndDescription(String standardizingProductEndDescription) { this.standardizingProductEndDescription = standardizingProductEndDescription; }
	

}
