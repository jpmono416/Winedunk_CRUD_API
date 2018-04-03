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
	
	private String sortingMethod;
	public void setSortingMethod(String sortingMethod) { this.sortingMethod = sortingMethod; }

	private String ratingValue;
	public void setRatingValue(String ratingValue) { this.ratingValue = ratingValue; }
	
	private Integer totalPages;
	public Integer getTotalPages() { return totalPages; }
	public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
	
	private String countingQuery = "SELECT count(wineId) FROM viewWines w ";
	public String getCountingQuery() { return countingQuery; }
	public void setCountingQuery(String countingQuery) { this.countingQuery = countingQuery; }

	private String queryWhere = "  WHERE (`w`.`wineDeleted` = 0) AND (`wineMinimumPrice` IS NOT NULL) AND (`wineMinimumPrice` > 0) ";
	private String queryGroupBy = " GROUP BY "
			+ " `w`.`wineId`, "
			+ " `w`.`wineImageURL`, "
			+ " `w`.`wineName`, "
			+ " `w`.`wineDefaultDescription`, "
			+ " `w`.`wineCountryName`, "
			+ " `w`.`wineRegionName`, "
			+ " `w`.`wineWineryName`, "
			+ " `w`.`wineAppellationName`, "
			+ " `w`.`wineColourName`, "
			+ " `w`.`wineVintage`, "
			+ " `w`.`wineBottleSize`, "
			+ " `w`.`wineAbv`, "
			+ " `w`.`wineClosureName`, "
			+ " `w`.`wineMinimumPrice`, "
			+ " `w`.`avgRating` ";
	
	private String querySelect = "SELECT DISTINCT `w`.`wineId` AS `wineId`, "
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
			+ " `w`.`avgRating` as `avgRating`, "
			+ " GROUP_CONCAT(DISTINCT `t`.`name`) AS `wineTypeName`,"
			+ " GROUP_CONCAT(DISTINCT `v`.`name`) AS `grapeVarietyName`"
			+ " FROM "; 
			
			
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	public void getResults()
	{
		buildQuerySelect();
		createQueryWhere();
						 
		fullQuery = querySelect + " " + queryWhere + queryGroupBy;
			
		if(sortingMethod != null && !sortingMethod.equals("")) { fullQuery += " ORDER BY wineMinimumPrice " + sortingMethod ; }  
		fullQuery += " LIMIT " + (pageNumber * 20 - 20) + ",20";
			
		try { launchQuery(); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void getOneResult(String wineId)
	{
		try
		{
			if(wineId.equals(null) || wineId.equals("")) { return; }
			//Construct and launch query
			buildQuerySelect();
			queryWhere += " AND (`w`.`wineId`= " + wineId + ") ";
			fullQuery = querySelect + " " + queryWhere;			
			viewWines wine = (viewWines) em.createNativeQuery(fullQuery, viewWines.class).getSingleResult();
			
			ObjectMapper mapper = new ObjectMapper();
	    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonResult = mapper.writeValueAsString(wine);
			
		} catch (Exception e) { e.printStackTrace(); }
	}

	private void createQueryWhere()
	{
		queryWhere = "  WHERE (`w`.`wineDeleted` = 0) AND (`wineMinimumPrice` IS NOT NULL) AND (`wineMinimumPrice` > 0) ";
		try
		{
			if(!name.equals("")) 						{ queryWhere += " AND ((`w`.`wineName` ='" + name + "') OR (`w`.`wineName` LIKE '%" + name + "%') OR (`w`.`wineDefaultDescription` LIKE '%" + name + "%'))"; }  
			if(!merchantId.equals("")) 					{ queryWhere += " AND (`w`.`merchantId` = " + merchantId + ")"; }
			if(!countryId.equals("")) 					{ queryWhere += " AND (`w`.`wineCountryId` = " + countryId + ") "; }
			if(!regionId.equals("")) 					{ queryWhere += " AND (`w`.`wineRegionId` = " + regionId +  ") "; }
			if(!wineryId.equals("")) 					{ queryWhere += " AND (`w`.`wineWineryId` = " + wineryId +  ") "; }
			if(!appellationId.equals("")) 				{ queryWhere += " AND (`w`.`wineAppellationId` = " + appellationId + ") "; }
			if(!colourId.equals("")) 					{ queryWhere += " AND (`w`.`wineColourId` = " + colourId + ") "; }
			if(!abvMin.equals("")) 						{ queryWhere += " AND (`w`.`wineAbv` BETWEEN " + abvMin + " AND " + abvMax + ") "; }
			if(!minPrice.equals("")) 					{ queryWhere += " AND (`w`.`wineMinimumPrice` BETWEEN " + minPrice + " AND " + maxPrice +") ";  }
			if(!closureId.equals("")) 					{ queryWhere += " AND (`w`.`wineClosureId` = " + closureId + ") "; }
			if(!vintageMin.equals("")) 					{ queryWhere += " AND (`w`.`wineVintage` BETWEEN " + vintageMin + " AND " + vintageMax + ") "; }
			if(!ratingValue.equals(""))					{ queryWhere += " AND (`w`.`avgRating` >= " + ratingValue + ") "; }
			
			/* ---- MODIFICADO ---- */
			//if(!typeId.equals("")) 					{ queryWhere += " AND (`t`.`id` = " + typeId + ") "; }
			// si se selecciona un solo tipo:
			if(!typeId.isEmpty())						{ queryWhere += " AND (`w`.`wineId` in (SELECT DISTINCT `wineId` FROM `tblWinesWineTypes` WHERE `typeId` = " + typeId + "))"; }  
			// si se selecciona más de un tipo, se crea un string con todos los tipos separados por coma (e.g.: "1, 5, 10"): 
			//if(!typesIdCommaSeparated.equals(""))		{ queryWhere += " AND (`w`.`id` in (SELECT DISTINCT `wineId` FROM `tblWinesWineTypes` WHERE `typeId` IN (" + typesIdCommaSeparated + ")))"; }  
			
			
			//if(!grapeVarietyId.equals("")) 			{ queryWhere += " AND (`v`.`id` = " + grapeVarietyId + ") "; }
			// si se selecciona una sola variedad:
			if(!grapeVarietyId.equals(""))				{ queryWhere += " AND (`w`.`wineId` in (SELECT DISTINCT `wineId` FROM `tblWinesGrapeVarieties` WHERE `grapeVarietyId` = " + grapeVarietyId + "))"; }  
			// si se selecciona más de una variedad, se crea un string con todos IDs de variedad separados por coma (e.g.: "1, 5, 10"): 
			//if(!grapeVarietyIdCommaSeparated.equals(""))	{ queryWhere += " AND (`w`.`id` in (SELECT DISTINCT `wineId` FROM `tblWinesGrapeVarieties` WHERE `grapeVarietyId` IN (" + grapeVarietyIdCommaSeparated + ")))"; }  
			
			/* ---- MODIFICADO ---- */
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void getCountForQuery()
	{
		buildQuerySelect();
		createQueryWhere();
		if(!name.equals("")) 
		{
			fullQuery = countingQuery + queryWhere + " AND ( (`w`.`wineName` ='" + name + "') OR (`w`.`wineName` LIKE '%" + name + "%') "
					+ " OR (`w`.`wineDefaultDescription` LIKE '%" + name + "%') )";
		} else { fullQuery = countingQuery + queryWhere; } 
		
		Integer amountOfResults = Integer.valueOf(((Long) em.createNativeQuery(fullQuery).getSingleResult()).intValue());
		totalPages = amountOfResults / 21;
		if(amountOfResults % 21 > 0) { totalPages++; }
	}
	
	private void launchQuery() throws IOException
	{		
		System.out.println("Query: " + fullQuery); //TODO DELETE
		@SuppressWarnings("unchecked")
		List<viewWines> resultsList = em.createNativeQuery(fullQuery, viewWines.class).getResultList();
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
    	jsonResult = mapper.writeValueAsString(resultsList);
	}
	
	private void buildQuerySelect()
	{
		if(!merchantId.equals("")) 	{ querySelect += "`viewWinesbyMerchants` AS `w`"; }
		else 						{ querySelect += "`viewWines` AS `w`"; } 
		
		querySelect += " LEFT JOIN `tblWinesWineTypes` AS `wt` ON `wt`.`wineId` = `w`.`wineId`"
		+ " LEFT JOIN `tblWineTypes` AS `t` ON `t`.`id`=`wt`.`typeId`"
		+ " LEFT JOIN `tblWinesGrapeVarieties` AS `wv` ON `wv`.`wineId` = `w`.`wineId`"
		+ " LEFT JOIN `tblGrapeVarieties` AS `v` ON `v`.`id`=`wv`.`grapeVarietyId`";
	}
	
	public void resetParameters()
	{
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
		this.fullQuery = "";
		this.queryWhere = " WHERE (`w`.`wineDeleted` = 0) AND (`wineMinimumPrice` IS NOT NULL) AND (`wineMinimumPrice` > 0) ";
		this.pageNumber = 0;
		this.sortingMethod = "";
		this.ratingValue = "";
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
				+ " `w`.`avgRating` as `avgRating`, "
				+ " GROUP_CONCAT(DISTINCT `t`.`name`) AS `wineTypeName`,"
				+ " GROUP_CONCAT(DISTINCT `v`.`name`) AS `grapeVarietyName`"
				+ " FROM "; 
	}
}