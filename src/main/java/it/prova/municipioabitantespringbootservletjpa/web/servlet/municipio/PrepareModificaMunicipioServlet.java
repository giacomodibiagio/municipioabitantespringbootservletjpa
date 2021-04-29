package it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;


@Component
public class PrepareModificaMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametroMunicipioDaModificare = request.getParameter("idMunicipio");

		Municipio result = null;

		if (!NumberUtils.isCreatable(parametroMunicipioDaModificare)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
			return;

		}
		RequestDispatcher rd = null;

		try {

			result = municipioService.caricaSingoloMunicipio(Long.parseLong(parametroMunicipioDaModificare));
			request.setAttribute("municipio_attribute", result);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/municipio/edit.jsp");
		rd.forward(request, response);	}


}
