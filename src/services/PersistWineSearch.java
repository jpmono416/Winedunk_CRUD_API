package services;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import views.viewWines;

@Stateless
@LocalBean
public class PersistWineSearch {
	
	private String name;
	public void setName(String name) { this.name = name; }
	
	private String merchantId;
	public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
	
	private String countryId;
	public void setCountryId(String countryId) { this.countryId = countryId; }
	
	private String regionId;
	public void setRegionId(String regionId) { this.regionId = regionId; }

	private String wineryId;
	public void setWineryId(String wineryId) { this.wineryId = wineryId; }
	
	private String appellationId;
	public void setAppellationId(String appellationId) { this.appellationId = appellationId; }

	private String colourId;
	public void setColourId(String colourId) { this.colourId = colourId; }

	private String vintageMin;
	public void setVintageMin(String vintageMin) { this.vintageMin = vintageMin; }
	
	private String vintageMax;
	public void setVintageMax(String vintageMax) { this.vintageMax = vintageMax; }

	private String abvMin;
	public void setAbvMin(String abvMin) { this.abvMin = abvMin; }

	private String abvMax;
	public void setAbvMax(String abvMax) { this.abvMax = abvMax; }
	
	private String minPrice;
	public void setMinPrice(String minPrice) { this.minPrice = minPrice; }
	
	private String maxPrice;
	public void setMaxPrice(String maxPrice) { this.maxPrice = maxPrice; }

	private String closureId;
	public void setClosureId(String closureId) { this.closureId = closureId; }

	private String typeId;
	public void setTypeId(String typeId) { this.typeId = typeId; }

	private String grapeVarietyId;
	public void setGrapeVarietyId(String grapeVarietyId) { this.grapeVarietyId = grapeVarietyId; }

	private String jsonResult;
	public String getJsonResult() { return jsonResult; }
	public void setJsonResult(String jsonResult) { this.jsonResult = jsonResult; }
	
	private String errorMessage;
	public String getErrorMessage() { return errorMessage; }
	public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

	private String fullQuery;
	public String getFullQuery() { return fullQuery; }
	public void setFullQuery(String fullQuery) { this.fullQuery = fullQuery; }
	
	private Integer pageNumber;
	public void setPageNumber(Integer pageNumber) { this.pageNumber = pageNumber; }
	
	private String orderBy;
	public void setOrderBy(String orderBy) { this.orderBy = orderBy; }

	private String ratingValue;
	public void setRatingValue(String ratingValue) { this.ratingValue = ratingValue; }
	
	private Integer totalPages;
	public Integer getTotalPages() { return totalPages; }
	public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
	
	private String countingQuery = "SELECT count(wineId) FROM viewWines w ";
	public String getCountingQuery() { return countingQuery; }
	public void setCountingQuery(String countingQuery) { this.countingQuery = countingQuery; }
	
	private String querySelect = "";
	private String queryFrom = "";	
	private String queryWhere = "";	
	private String queryGroupBy = "";
	private String queryOrderBy = "";
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	public void resetParameters() {
		this.name = "";
		this.merchantId = "";
		this.countryId = "";
		this.regionId = "";
		this.wineryId = "";
		this.appellationId = "";
		this.closureId = "";
		this.colourId = "";
		this.vintageMin = "";
		this.vintageMax = "";
		this.abvMin = "";
		this.abvMax = "";
		this.minPrice = "";
		this.maxPrice = "";
		this.typeId = "";
		this.grapeVarietyId = "";
		this.errorMessage = "";
		this.pageNumber = 0;
		this.orderBy = "`winePercentageOff` DESC";
		this.ratingValue = "";
		this.resetQueryValues();
	}
	
	private void resetQueryValues() {
		this.querySelect = "SELECT DISTINCT `w`.`wineId` AS `wineId`, "
				  + " `w`.`wineImageURL` AS `wineImageURL`, " 
				  + " `w`.`wineName` AS `wineName`, "
				  + " `w`.`wineShortDescription` AS `wineShortDescription`, "
				  + " `w`.`wineDefaultDescription` AS `wineDefaultDescription`, "
				  + " `w`.`wineCountryName` AS `wineCountryName`, "
				  + " `w`.`wineRegionName` AS `wineRegionName`, "
				  + " `w`.`wineWineryName` AS `wineWineryName`, "
				  + " `w`.`wineAppellationName` AS `wineAppellationName`, "
				  + " `w`.`wineColourName` AS `wineColourName`, " 
				  + " `w`.`wineVintage` AS `wineVintage`, "
				  + " `w`.`wineBottleSize` AS `wineBottleSize`, "
				  + " `w`.`wineAbv` AS `wineAbv`, "
				  + " `w`.`wineClosureName` AS `wineClosureName`, "
				  + " `w`.`wineMinimumPrice` AS `wineMinimumPrice`, "
				  + " `w`.`winePreviousMaxPrice` AS `winePreviousMaxPrice`, "
				  + " `w`.`wineMoneySaving` AS `wineMoneySaving`, "
				  + " `w`.`winePercentageOff` AS `winePercentageOff`, "
				  + " `w`.`wineMinimumPriceClicktag` AS `wineMinimumPriceClicktag`, "
				  + " `w`.`avgRating` as `avgRating`, "
				  + " GROUP_CONCAT(DISTINCT `t`.`name`) AS `wineTypeName`, "
				  + " GROUP_CONCAT(DISTINCT `v`.`name`) AS `grapeVarietyName` ";

		this.queryFrom = "FROM `viewWines` AS `w` "
  					   + "LEFT JOIN `tblWinesWineTypes` AS `wt` ON `wt`.`wineId` = `w`.`wineId` "
					   + "LEFT JOIN `tblWineTypes` AS `t` ON `t`.`id`=`wt`.`typeId` "
					   + "LEFT JOIN `tblWinesGrapeVarieties` AS `wv` ON `wv`.`wineId` = `w`.`wineId` "
					   + "LEFT JOIN `tblGrapeVarieties` AS `v` ON `v`.`id`=`wv`.`grapeVarietyId` ";

		this. queryWhere = "WHERE (`w`.`wineDeleted` = 0) AND "
							   + "(`w`.`wineMinimumPrice` IS NOT NULL) AND "
							   + "(`w`.`wineMinimumPrice` > 0) ";

		this.queryGroupBy = "GROUP BY "
							  		+ "`w`.`wineId`, "
							  		+ "`w`.`wineImageURL`, "
									+ "`w`.`wineName`, "
									+ "`w`.`wineDefaultDescription`, "
									+ "`w`.`wineCountryName`, "
									+ "`w`.`wineRegionName`, "
									+ "`w`.`wineWineryName`, "
									+ "`w`.`wineAppellationName`, "
									+ "`w`.`wineColourName`, "
									+ "`w`.`wineVintage`, "
									+ "`w`.`wineBottleSize`, "
									+ "`w`.`wineAbv`, "
									+ "`w`.`wineClosureName`, "
									+ "`w`.`wineMinimumPrice`, "
									+ "`w`.`winePreviousMaxPrice`, "
									+ "`w`.`wineMoneySaving`, "
									+ "`w`.`winePercentageOff`, "
									+ "`w`.`wineMinimumPriceClicktag`, "
									+ "`w`.`avgRating` ";
		
		this.queryOrderBy = "ORDER BY " + orderBy;

		this.fullQuery = "";

	}
	
	public void getResults()
	{
		
		buildQueryWhere();
		buildQueryOrderBy();
						 
		fullQuery = querySelect + " " + queryFrom + " " + queryWhere +  " " + queryGroupBy +  " " + queryOrderBy;
		fullQuery = fullQuery.replaceAll("  ", " ");	
		fullQuery += " LIMIT " + (pageNumber * 20 - 20) + ",20";

		try { launchQuery(); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private void buildQueryOrderBy() {
		try {
			if ( (orderBy != null) && (!orderBy.equals("")) && (!orderBy.equals("null")) ) { 
				queryOrderBy = "ORDER BY " + orderBy;
			} else {
				queryOrderBy = "ORDER BY `winePercentageOff` DESC";
			}		
		} catch (Exception e) {
			queryOrderBy = "ORDER BY `winePercentageOff` DESC";
			e.printStackTrace();
		}	
		
	}
	
	public void getOneResult(String wineId)
	{
		resetQueryValues();
		try
		{
			if(wineId.equals(null) || wineId.equals("")) { return; }
			//Construct and launch query
			queryWhere += " AND (`w`.`wineId`= " + wineId + ") ";
			fullQuery = querySelect + " " + queryWhere;			
			viewWines wine = (viewWines) em.createNativeQuery(fullQuery, viewWines.class).getSingleResult();
			
			ObjectMapper mapper = new ObjectMapper();
	    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonResult = mapper.writeValueAsString(wine);
			
		} catch (Exception e) { e.printStackTrace(); }
	}

	private void buildQueryWhere()
	{
		resetQueryValues();
		try
		{
			if ( (name != null) && (!name.equals("")) )							{ queryWhere += " AND ((`w`.`wineName` ='" + name + "') OR (`w`.`wineName` LIKE '%" + name + "%') OR (`w`.`wineDefaultDescription` LIKE '%" + name + "%'))"; }  
			if ( (colourId != null) && (!colourId.equals("")) ) 				{ queryWhere += " AND (`w`.`wineColourId` = " + colourId + ") "; }
			if ( (typeId != null) && (!typeId.isEmpty()) )						{ queryWhere += " AND (`w`.`wineId` in (SELECT DISTINCT `wineId` FROM `tblWinesWineTypes` WHERE `typeId` = " + typeId + "))"; }  
			if ( (minPrice != null) && (!minPrice.equals("")) ) 				{ queryWhere += " AND (`w`.`wineMinimumPrice` BETWEEN " + minPrice + " AND " + maxPrice +") ";  }
			if ( (grapeVarietyId != null) && (!grapeVarietyId.equals("")) )		{ queryWhere += " AND (`w`.`wineId` in (SELECT DISTINCT `wineId` FROM `tblWinesGrapeVarieties` WHERE `grapeVarietyId` = " + grapeVarietyId + "))"; }  
			if ( (countryId != null) && (!countryId.equals("")) ) 				{ queryWhere += " AND (`w`.`wineCountryId` = " + countryId + ") "; }
			if ( (regionId != null) && (!regionId.equals("")) ) 				{ queryWhere += " AND (`w`.`wineRegionId` = " + regionId +  ") "; }
			if ( (appellationId != null) && (!appellationId.equals("")) ) 		{ queryWhere += " AND (`w`.`wineAppellationId` = " + appellationId + ") "; }
			if ( (merchantId != null) && (!merchantId.isEmpty()) )				{ queryWhere += " AND (`w`.`wineId` in (SELECT DISTINCT `wineId` FROM `tblPartnersProducts` WHERE `shopId` = " + merchantId + "))"; }  
			if ( (wineryId != null) && (!wineryId.equals("")) ) 				{ queryWhere += " AND (`w`.`wineWineryId` = " + wineryId +  ") "; }
			if ( (abvMin != null) && (!abvMin.equals("")) ) 					{ queryWhere += " AND (`w`.`wineAbv` BETWEEN " + abvMin + " AND " + abvMax + ") "; }
			if ( (vintageMin != null) && (!vintageMin.equals("")) ) 			{ queryWhere += " AND (`w`.`wineVintage` BETWEEN " + vintageMin + " AND " + vintageMax + ") "; }
			if ( (closureId != null) && (!closureId.equals("")) ) 				{ queryWhere += " AND (`w`.`wineClosureId` = " + closureId + ") "; }
			if ( (ratingValue != null) && (!ratingValue.equals("")) )			{ queryWhere += " AND (`w`.`avgRating` >= " + ratingValue + ") "; }
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void getCountForQuery()
	{
		buildQueryWhere();
		fullQuery = countingQuery + queryWhere;
		fullQuery = fullQuery.replaceAll("  ", " ");	
		try {
			
			Integer amountOfResults = Integer.valueOf(((Long) em.createNativeQuery(fullQuery).getSingleResult()).intValue());
			totalPages = amountOfResults / 20;
			if(amountOfResults % 20 > 0) { totalPages++; }
			
		} catch (Exception e) {
			totalPages = 1;
			e.printStackTrace();
		}
		
	}
	
	private void launchQuery() throws IOException
	{		
		@SuppressWarnings("unchecked")
		List<viewWines> resultsList = em.createNativeQuery(fullQuery, viewWines.class).getResultList();
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
    	jsonResult = mapper.writeValueAsString(resultsList);
	}

}