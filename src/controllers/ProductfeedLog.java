package controllers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.TblPFLogCategories;
import models.TblPFLogProcesses;
import models.TblPFLogTypes;
import models.tblPartners;
import services.PFLogCategoriesService;
import services.PFLogProcessesService;
import services.PFLogService;
import services.PFLogTypesService;
import services.PartnersService;

/**
 * Servlet implementation class PFProductsBlacklist
 */
@WebServlet("/ProductfeedLog")
public class ProductfeedLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ObjectMapper mapper = new ObjectMapper();
	private JsonNode jsonNode = null;
	
	@EJB
	PFLogService pfLogService;
	
	@EJB
	PFLogProcessesService pfLogProcessesService;
	
	@EJB
	PFLogTypesService pfLogTypesService;
	

	@EJB
	PFLogCategoriesService pfLogCategoriesService;
	
	@EJB
	PartnersService partnersService;
	
    public ProductfeedLog() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("action")) {
			
			try {
				
					switch (request.getParameter("action")) {
	
						case "getLogProcessProductFeedsPocessor": {
							
							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogProcesses pfLogProcess = pfLogProcessesService.getLogProcessProductFeedsPocessor();
							String pfLogProcessJson = "";
							pfLogProcessJson = mapper.writeValueAsString(pfLogProcess);
							response.getWriter().write(pfLogProcessJson);
							break;
						}
		
						case "getLogProcessProductsProcessor": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogProcesses pfLogProcess = pfLogProcessesService.getLogProcessProductsProcessor();
							String pfLogProcessJson = "";
							pfLogProcessJson = mapper.writeValueAsString(pfLogProcess);
							response.getWriter().write(pfLogProcessJson);
							break;
						}
		
						case "getLogTypeError": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogTypes pfLogTypes = pfLogTypesService.getLogTypeError();
							String pfLogTypesJson = "";
							pfLogTypesJson = mapper.writeValueAsString(pfLogTypes);
							response.getWriter().write(pfLogTypesJson);
							break;
						}
		
						case "getLogTypeWarning": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogTypes pfLogTypes = pfLogTypesService.getLogTypeWarning();
							String pfLogTypesJson = "";
							pfLogTypesJson = mapper.writeValueAsString(pfLogTypes);
							response.getWriter().write(pfLogTypesJson);
							break;
						}
		
						case "getLogTypeInformation": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogTypes pfLogTypes = pfLogTypesService.getLogTypeInformation();
							String pfLogTypesJson = "";
							pfLogTypesJson = mapper.writeValueAsString(pfLogTypes);
							response.getWriter().write(pfLogTypesJson);
							break;
						}
						
						case "getLogCategoryProcessStarted": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogCategories pfLogCategories = pfLogCategoriesService.getLogcategoryPROCESSSTARTED();
							String pfLogCategoriesJson = "";
							pfLogCategoriesJson = mapper.writeValueAsString(pfLogCategories);
							response.getWriter().write(pfLogCategoriesJson);
							break;
						}
						
						case "getLogCategoryProductProcessingStarted": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogCategories pfLogCategories = pfLogCategoriesService.getLogcategoryPRODUCTPROCESSINGSTARTED();
							String pfLogCategoriesJson = "";
							pfLogCategoriesJson = mapper.writeValueAsString(pfLogCategories);
							response.getWriter().write(pfLogCategoriesJson);
							break;
						}
						
						case "getLogCategoryProductProcessing": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogCategories pfLogCategories = pfLogCategoriesService.getLogcategoryPRODUCTPROCESSING();
							String pfLogCategoriesJson = "";
							pfLogCategoriesJson = mapper.writeValueAsString(pfLogCategories);
							response.getWriter().write(pfLogCategoriesJson);
							break;
						}
						
						case "getLogCategoryProductProcessingFinished": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogCategories pfLogCategories = pfLogCategoriesService.getLogcategoryPRODUCTPROCESSINGFINISHED();
							String pfLogCategoriesJson = "";
							pfLogCategoriesJson = mapper.writeValueAsString(pfLogCategories);
							response.getWriter().write(pfLogCategoriesJson);
							break;
						}
						
						case "getLogCategoryProcessFinished": {

							// Set pretty printing of json
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
							TblPFLogCategories pfLogCategories = pfLogCategoriesService.getLogcategoryPROCESSFINISHED();
							String pfLogCategoriesJson = "";
							pfLogCategoriesJson = mapper.writeValueAsString(pfLogCategories);
							response.getWriter().write(pfLogCategoriesJson);
							break;
						}
	
					}
					
					return;
				
			} catch (Exception e) {
				response.getWriter().write("Exception");
		    	e.printStackTrace();
		    	return;
		    }
		} else {
			response.getWriter().print("Parameter Action is missing");
			return;
		}			
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("action")) {
			
			try {

			    jsonNode = this.mapper.readTree(request.getInputStream());
				tblPartners partner = new tblPartners();
			    partner = this.mapper.treeToValue(jsonNode, tblPartners.class);

				switch (request.getParameter("action")) {
					
					case "productFeedsPocessorBegin" : {
						
						String pfName = "";
						try {
							pfName = request.getParameter("pfName");
							response.getWriter().print(pfLogService.ProductFeedsPocessorBegin(partner, pfName));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing pfName");
							break;
						}
					}
					
					case "productFeedsPocessorEnd" : {

						String pfName = "";
						try {
							pfName = request.getParameter("pfName");
							response.getWriter().print(pfLogService.ProductFeedsPocessorEnd(partner, pfName));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing pfName");
							break;
						}
					}
					
					case "productsProcessorBegin" : {
					    
						response.getWriter().print(pfLogService.ProductsProcessorBegin(partner));
						break;
					}
						
					
					case "productsProcessorEnd" : {
					    				    
						response.getWriter().print(pfLogService.ProductsProcessorEnd(partner));
						break;
					}
					
					case "productStandardisationBegin" : {
						
						String partnerProductId = "";
						try {
							partnerProductId = request.getParameter("partnerProductId");
							response.getWriter().print(pfLogService.ProductStandardisationBegin(partner, partnerProductId));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing partnerProductId");
							break;
						}
					}
					
					case "productStandardisationEnd" : {
						
						String partnerProductId = "";
						try {
							partnerProductId = request.getParameter("partnerProductId");
							response.getWriter().print(pfLogService.ProductStandardisationEnd(partner, partnerProductId));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing partnerProductId");
							break;
						}
					}
					
					case "productProcessingBegin" : {
						
						String partnerProductId = "";
						try {
							partnerProductId = request.getParameter("partnerProductId");
							response.getWriter().print(pfLogService.ProductProcessingBegin(partner, partnerProductId));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing partnerProductId");
							break;
						}
					}
					
					case "productProcessingEnd" : {
						
						String partnerProductId = "";
						try {
							partnerProductId = request.getParameter("partnerProductId");
							response.getWriter().print(pfLogService.ProductProcessingEnd(partner, partnerProductId));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing partnerProductId");
							break;
						}
					}
					
					case "productProcessing" : {
						
						String logtypeName = "";
						String partnerProductId = "";
						String entityName = "";
						Integer entityId = 0;
						String description = "";
						
						try {
							
							logtypeName = request.getParameter("logtypeName");
							partnerProductId = request.getParameter("partnerProductId");
							entityName = request.getParameter("entityName");
							entityId = Integer.parseInt(request.getParameter("entityId"));
							description = request.getParameter("description");

							response.getWriter().print(pfLogService.ProductProcessing(partner, logtypeName, partnerProductId, entityName, entityId, description));
							break;
							
						} catch (Exception e) {
							e.printStackTrace();
							break;
						}
						
					}
					
					case "storedprocedureCalled" : {
						
						String spName = "";
						try {
							spName = request.getParameter("spName");
							response.getWriter().print(pfLogService.StoredprocedureCalled(partner, spName));
							break;
						} catch (Exception e) {
							response.getWriter().print("Exception while parsing partnerProductId");
							break;
						}
					}
				}
				
				return;

		    } catch (Exception e) {
		    	
				response.getWriter().write("Exception");
		    	e.printStackTrace();
		    	return;
		    }

		} else {
			response.getWriter().print("Parameter Action is missing");
			return;
		}
			
	}
		

}
