package it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteInsertMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String codiceParam = request.getParameter("codice");
		String descrizioneParam = request.getParameter("descrizione");
		String ubicazioneParam = request.getParameter("ubicazione");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Municipio municipioInstance = UtilityForm.createMunicipioFromParams(codiceParam, descrizioneParam,
				ubicazioneParam);

		// se la validazione non risulta ok
		if (!UtilityForm.validateRegistaBean(municipioInstance)) {
			request.setAttribute("insert_municipio_attr", municipioInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/municipio/insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			municipioService.inserisciNuovo(municipioInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListMunicipioServlet?operationResult=SUCCESS");
	}

}
