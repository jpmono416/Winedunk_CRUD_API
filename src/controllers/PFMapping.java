package controllers;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.MappingService;
/**
 * 
 * 
 * 
 * Servlet implementation class PFMapping
 * 
 * 
 * 
 */
@WebServlet("/PFMapping")
public class PFMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	MappingService mappingService;

	public PFMapping() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter action missing");
			return;
		}
		
		switch (request.getParameter("action"))
		{
			case "getByPFId":
			{
				if (!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter id missing");
					return;
				}
				response.getWriter().write(this.mapper
						.writeValueAsString(this.mappingService.findByPFId(Integer.valueOf(request.getParameter("id")))));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter action missing");
			return;
		}
	}
}