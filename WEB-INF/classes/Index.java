import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import beans.GameState;

public class Index extends HttpServlet
{
	
	public Index(){}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		
		if (request.getParameter("new") != null) 
		{
			if (request.getParameter("new").equals("false"))
			{
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/LoadSave.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/Index.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	
}