package it.prova.municipioabitantespringbootservletjpa.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Utente;

//affidiamo i filtri a spring e non specificando altro = urlPatterns = { "/*" }
@Component
@Order(1)
public class AuthenticationFilter implements Filter {

	public static final String HOME_PATH = "";
	public static final String[] EXCLUDED_URLS = { "/login.jsp", "/LoginServlet", "/LogoutServlet", "/assets/" };

	private static final String USER_LOGGED_IN_ATTRIBUTE_NAME = "userInfo";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Nel filtro di check user in session");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// prendo il path della request che sta passando in questo momento es.
		// /LoginServlet
		String pathAttuale = httpRequest.getServletPath();
		System.out.println("Invocazione di: " + pathAttuale);

		// vediamo se il path risulta tra quelli 'liberi di passare'
		// se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isPathInWhiteList(pathAttuale) && !isUserLoggedIn(httpRequest)) {
			httpResponse.sendRedirect(httpRequest.getContextPath());
			return;
		}

		chain.doFilter(request, response);
	}

	public static boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}

	public static boolean isUserLoggedIn(HttpServletRequest httpRequest) {
		return httpRequest.getSession().getAttribute(USER_LOGGED_IN_ATTRIBUTE_NAME) != null;
	}

	public static Utente getUserLoggedIn(HttpServletRequest httpRequest) {
		return (Utente) httpRequest.getSession().getAttribute(USER_LOGGED_IN_ATTRIBUTE_NAME);
	}

	public static void putUserLoggedInSession(HttpServletRequest httpRequest, Utente utenteInstance) {
		httpRequest.getSession().setAttribute(AuthenticationFilter.USER_LOGGED_IN_ATTRIBUTE_NAME, utenteInstance);
	}
}
