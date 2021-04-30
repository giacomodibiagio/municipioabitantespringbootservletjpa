package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.RuoloService;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteModificaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RuoloService ruoloService;
	@Autowired
	private UtenteService utenteService;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParameter = request.getParameter("idUtente");
		String nomeParameter = request.getParameter("nome");
		String cognomeParameter = request.getParameter("cognome");
		String usernameParameter = request.getParameter("username");
		String passwordParameter = request.getParameter("password");
		String[] ruoliParam = request.getParameterValues("ruoli");
		Set<Ruolo> ruoli = new HashSet<>();

		for (String item : ruoliParam) { 
			ruoli.add(ruoloService.caricaSingoloElemento(Long.parseLong(item)));
		}
		
		Utente utenteInstance = UtilityForm.createUtenteFromParams(nomeParameter, cognomeParameter, usernameParameter, passwordParameter);
		utenteInstance.setRuoli(ruoli);
		utenteInstance.setId(Long.parseLong(idParameter));
		
		if (!UtilityForm.validateUtenteBean(utenteInstance)) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("utente_attribute", utenteInstance);
			request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
			return;
		}

		try {

			utenteService.aggiorna(utenteInstance);
			request.setAttribute("utenti_list_attribute",
					utenteService.listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
	}

}
