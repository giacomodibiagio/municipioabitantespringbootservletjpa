package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.StatoUtente;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	public ExecuteSearchUtenteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String dataCreazioneParam = request.getParameter("dataDiCreazione");
		String statoParam = request.getParameter("stato");

		StatoUtente statoInstance = null;
		statoInstance.valueOf(statoParam);

		Utente example = new Utente(usernameParam, nomeParam, cognomeParam,
				UtilityForm.parseDateFromString(dataCreazioneParam), statoInstance);

		try {
			request.setAttribute("utenti_list_attribute", utenteService.findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("utente/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("utente/list.jsp").forward(request, response);
	}

}
