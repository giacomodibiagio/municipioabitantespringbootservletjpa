package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.municipioabitantespringbootservletjpa.model.Utente;

@WebServlet("/utente/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("insert_utente_attr", new Utente());
		request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
	}

}
