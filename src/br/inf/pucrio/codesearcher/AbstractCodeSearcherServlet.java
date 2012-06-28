package br.inf.pucrio.codesearcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCodeSearcherServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			doPost( request, response );
		}
		catch (Exception e)
		{
			request.setAttribute( "exception", e );

			RequestDispatcher dispatcher = request.getRequestDispatcher( "error.jsp" );

			dispatcher.forward( request, response );
		}
	}
}
