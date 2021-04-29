package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
 
@Component
 public class ExecuteDeleteAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AbitanteService abitanteService;

//	@Autowired
//	private MunicipioService municipioService;

	public ExecuteDeleteAbitanteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAbitanteParameter = request.getParameter("idDeleteInput");
 
		Abitante abitanteInstance = new Abitante();
		if (!NumberUtils.isCreatable(idAbitanteParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/abitante/delete.jsp").forward(request, response);
			return;
		}

		try {

			abitanteInstance = abitanteService.caricaSingoloElementoEager(Long.parseLong(idAbitanteParameter));
			abitanteService.rimuovi(abitanteInstance);
			request.setAttribute("abitante_list_attribute", abitanteService.listAllAbitanti());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;

		}

		RequestDispatcher rd = request.getRequestDispatcher("/abitante/list.jsp");
		rd.forward(request, response);
	}

}
