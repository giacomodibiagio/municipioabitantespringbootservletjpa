package it.prova.municipioabitantespringbootservletjpa.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.municipioabitantespringbootservletjpa.web.filter.AuthenticationFilter;

@WebServlet(urlPatterns = {"","/home"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operationResult = request.getParameter("operationResult");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_ALLOWED"))
			request.setAttribute("errorMessage", "Attenzione! Non si è autorizzati alla navigazione richiesta");
		
		getRequestDispatcherIfUserLoggedInOrNot(request).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getRequestDispatcherIfUserLoggedInOrNot(request).forward(request, response);
	}

	private RequestDispatcher getRequestDispatcherIfUserLoggedInOrNot(HttpServletRequest request) {
		if (AuthenticationFilter.isUserLoggedIn(request))
			return request.getRequestDispatcher("index.jsp");

		return request.getRequestDispatcher("login.jsp");
	}

}
