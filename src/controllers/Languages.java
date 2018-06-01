package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblLanguages;
import services.LanguagesService;

/**
 * Servlet implementation class Languages
 */
@WebServlet("/languages")
public class Languages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	LanguagesService languageService = new LanguagesService();
    public Languages() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) {
			case "getLanguages" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblLanguages> languages = languageService.getLanguages();
					String arrayToJson = objectMapper.writeValueAsString(languages);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getLanguage" : {
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblLanguages language = languageService.getLanguageById(id);
					String arrayToJson = objectMapper.writeValueAsString(language);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
        
	    while ((line = reader.readLine()) != null) 
        { sb.append('\n').append(line); }
	    reader.close();

	    String content = sb.toString().replaceFirst("\n", "");
	    
		String action = request.getParameter("action");
		switch (action) {
			case "addLanguage" : {
				try
				{
					tblLanguages language = new tblLanguages();
					ObjectMapper mapper = new ObjectMapper();
					language = mapper.readValue(content, tblLanguages.class);
					
					if(languageService.addLanguage(language)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateLanguage" : {
				try
				{
					tblLanguages language = new tblLanguages();
					ObjectMapper mapper = new ObjectMapper();
					language = mapper.readValue(content, tblLanguages.class);
					
					if(languageService.updateLanguage(language)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteLanguage" : {
				try
				{
					Integer id = Integer.parseInt(content);
					if(languageService.deleteLanguage(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}