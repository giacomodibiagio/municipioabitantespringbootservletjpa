package it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio;

import java.io.IOException;
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
public class ExecuteDeleteMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idMunicipioParam = request.getParameter("idDeleteInput");

		if (!NumberUtils.isCreatable(idMunicipioParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("municipio/search.jsp").forward(request, response);
			return;
		}

		try {

			Municipio municipioInstance = municipioService.caricaSingoloMunicipio(Long.parseLong(idMunicipioParam));
			municipioService.rimuovi(municipioInstance);
			request.setAttribute("listaMunicipiAttribute", municipioService.listAllMunicipi());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("municipio/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("ExecuteListMunicipioServlet").forward(request, response);	}

}
