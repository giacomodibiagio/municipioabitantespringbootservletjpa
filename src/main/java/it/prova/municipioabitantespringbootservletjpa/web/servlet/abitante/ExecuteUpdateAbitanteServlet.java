package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteUpdateAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private AbitanteService abitanteServiceInstance;

	@Autowired
	private MunicipioService municipioServiceInstance;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idParam = request.getParameter("idToUpdate");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String residenzaParam = request.getParameter("residenza");
		String dataNascitaParam = request.getParameter("dataDiNascita");
		String municipioIdParam = request.getParameter("municipio.id");

		Abitante abitanteInstance = UtilityForm.createAbitanteFromParams(nomeParam, cognomeParam, residenzaParam,
				dataNascitaParam, municipioIdParam);

		try {
			
			if (!UtilityForm.validateAbitanteBean(abitanteInstance)) {
				request.setAttribute("insert_abitante_attr", abitanteInstance);
				request.setAttribute("municipi_list_attribute", municipioServiceInstance.listAllMunicipiOrderByDescrizione());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/abitante/insert.jsp").forward(request, response);
				return;
			}
			abitanteInstance.setId(Long.parseLong(idParam));
			abitanteServiceInstance.aggiorna(abitanteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/abitante/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListAbitanteServlet?operationResult=SUCCESS");
		}

}
