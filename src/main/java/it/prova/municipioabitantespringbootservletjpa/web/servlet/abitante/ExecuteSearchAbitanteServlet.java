package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteSearchAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AbitanteService abitanteService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String residenzaParam = request.getParameter("residenza");
		String dataNascitaParam = request.getParameter("dataDiNascita");
		String municipioIdParam = request.getParameter("municipio.id");

		Abitante example = new Abitante(nomeParam, cognomeParam, UtilityForm.parseDateFromString(dataNascitaParam),
				residenzaParam);
		if (NumberUtils.isCreatable(municipioIdParam))
			example.setMunicipio(new Municipio(Long.parseLong(municipioIdParam)));

		try {
			request.setAttribute("abitanti_list_attribute", abitanteService.findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("abitante/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("abitante/list.jsp").forward(request, response);
	}

}
