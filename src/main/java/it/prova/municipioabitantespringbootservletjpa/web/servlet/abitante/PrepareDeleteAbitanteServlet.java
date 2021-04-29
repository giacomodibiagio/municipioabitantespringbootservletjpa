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
 public class PrepareDeleteAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AbitanteService abitanteService;

//	@Autowired
//	private MunicipioService municipioService;

	public PrepareDeleteAbitanteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String abitanteId = request.getParameter("idAbitante");

 		Abitante result = null;

		if (!NumberUtils.isCreatable(abitanteId)) {

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/abitante/list.jsp").forward(request, response);
			return;
		}

		RequestDispatcher rd = null;

		try {
			
			result = abitanteService.caricaSingoloElementoEager(Long.parseLong(abitanteId));
			request.setAttribute("abitante_delete", result);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/abitante/delete.jsp");
		rd.forward(request, response);
	}

}
