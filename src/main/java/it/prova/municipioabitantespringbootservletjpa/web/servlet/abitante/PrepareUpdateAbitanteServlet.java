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
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;

@Component
public class PrepareUpdateAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AbitanteService abitanteServiceInstance;
	@Autowired
	private MunicipioService municipioServiceInstance;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAbitanteParameter = request.getParameter("idAbitante");

		if (!NumberUtils.isCreatable(idAbitanteParameter)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {

			Abitante abitanteInstance = abitanteServiceInstance
					.caricaSingoloElementoEager(Long.parseLong(idAbitanteParameter));

			if (abitanteInstance == null) {
				request.setAttribute("errorMessage", "Errore caricamento elemento.");
				return;
			}

			request.setAttribute("municipi_list_attribute", municipioServiceInstance.listAllMunicipi());
			request.setAttribute("update_abitante_attr", abitanteInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("abitante/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/abitante/edit.jsp").forward(request, response);

	}

}
