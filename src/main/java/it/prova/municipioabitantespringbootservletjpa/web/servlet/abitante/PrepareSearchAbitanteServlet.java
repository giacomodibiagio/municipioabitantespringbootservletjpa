package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;

@Component
public class PrepareSearchAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("insert_abitante_attr", new Abitante());
			request.setAttribute("municipi_list_attribute",municipioService.listAllMunicipiOrderByDescrizione());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/abitante/search.jsp").forward(request, response);
	}
}
