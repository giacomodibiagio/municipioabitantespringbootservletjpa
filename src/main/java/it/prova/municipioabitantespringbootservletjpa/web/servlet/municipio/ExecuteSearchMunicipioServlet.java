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

@Component
public class ExecuteSearchMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codiceParam = request.getParameter("codice");
		String descrizioneParam = request.getParameter("descrizione");
		String ubicazioneParam = request.getParameter("ubicazione");

		Municipio example = new Municipio(descrizioneParam, codiceParam, ubicazioneParam);

		try {
			request.setAttribute("municipi_list_attribute", municipioService.findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("municipio/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("municipio/list.jsp").forward(request, response);
	}

}
