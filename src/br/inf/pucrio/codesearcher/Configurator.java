package br.inf.pucrio.codesearcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Configurator extends AbstractCodeSearcherServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void performAction(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		Feedback[] feedbackArray = Feedback.values();
		for (Feedback feedback : feedbackArray)
		{
			String feedbackName = feedback.name();

			String feedbackValueStr = request.getParameter( feedbackName );

			if (feedbackValueStr != null)
			{
				Float feedbackValue = Float.parseFloat( feedbackValueStr );

				feedback.setBoostValue( feedbackValue );
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher( "config.jsp" );

		dispatcher.forward( request, response );
	}

}
