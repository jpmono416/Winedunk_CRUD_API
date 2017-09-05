package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Merchants
 */
@WebServlet("/Merchants")
public class Merchants extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Merchants() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	if(!request.getParameterMap().containsKey("action"))
    	{
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
    	}
    	
    	switch(request.getParameter("action"))
    	{
    		case "getAll":
    			break;
    		case "getByName":
    			break;
    	}
    }

}
