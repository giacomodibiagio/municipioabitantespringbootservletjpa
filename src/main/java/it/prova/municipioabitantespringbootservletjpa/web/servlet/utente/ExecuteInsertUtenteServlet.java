package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		Date dateParam = new Date();

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Utente utenteInstance = UtilityForm.createUtenteFromParams(usernameParam, passwordParam, nomeParam, cognomeParam);
		utenteInstance.setDateCreated(dateParam);

		// se la validazione non risulta ok
		if (!UtilityForm.validateUtenteBean(utenteInstance)) {
			request.setAttribute("insert_utente_attr", utenteInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			utenteService.inserisciNuovo(utenteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
