package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import views.viewWinePriceComparison;

@Stateless
@LocalBean
public class PersistComparisonSearch {
	
	private String id;
	public String getId() { return id; }
	
	private String wineId;
	public String getWineId() { return wineId; }
	
	private String shopId;
	public String getShopId() { return shopId; }
	
	private String shopName;
	public String getShopName() { return shopName; }
	
	private String partnerId;
	public String getPartnerId() { return partnerId; }
	
	private String partnerProductId;
	public String getPartnerProductId() { return partnerProductId; }
 	
	private String destinationURL;
	public String getDestinationURL() { return destinationURL; }
	
	private String productPrice;
	public String getProductPrice() { return productPrice; }
	
	private String deliveringCost;
	public String getDeliveringCost() { return deliveringCost; }
	
	private String stock;
	public String getStock() { return stock; }	
	
	private String jsonResult;
	public String getJsonResult() { return jsonResult; }

	private String fullQuery;
	private String querySelect = "SELECT `wpc`.`id`,"
	    + " `wpc`.`wineId`,"
	    + " `wpc`.`shopId`,"
	    + " `wpc`.`shopName`,"
	    + " `wpc`.`shopImageURL`, "
	    + " `wpc`.`partnerId`,"
	    + " `wpc`.`partnerProductId`,"
	    + " `wpc`.`destinationURL`,"
	    + " `wpc`.`productPrice`,"
	    + " `wpc`.`deliveringCost`,"
	    + " `wpc`.`stock`"
    + " FROM `viewWinePriceComparison` AS `wpc`";
	private String queryOrderBy = "ORDER BY `wpc`.`productPrice`";
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	public void getResults(String id)
	{
		try
		{
			if(id.equals(null) || id.equals("")) { return; }
			this.wineId = id;
			fullQuery = querySelect + " WHERE (`wpc`.`wineId` = " + wineId + ") " + queryOrderBy;
			@SuppressWarnings("unchecked")
			List<viewWinePriceComparison> resultsList = em.createNativeQuery(fullQuery, viewWinePriceComparison.class).getResultList();
			ObjectMapper mapper = new ObjectMapper();
	    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    	
	    	jsonResult = mapper.writeValueAsString(resultsList);
		} catch (Exception e) { e.printStackTrace(); }
	}
}