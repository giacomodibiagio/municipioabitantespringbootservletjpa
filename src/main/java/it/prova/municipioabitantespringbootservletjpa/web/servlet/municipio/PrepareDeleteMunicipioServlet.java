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
public class PrepareDeleteMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParameter = request.getParameter("idMunicipio");
		Municipio municipioInstance = municipioService.caricaSingoloMunicipioConAbitanti(Long.parseLong(idParameter));
        Municipio result = null;

        if (!NumberUtils.isCreatable(idParameter)) {

            // qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
            return;
        }

        try {

            if (!municipioInstance.getAbitanti().isEmpty()) {
                request.setAttribute("errorMessageEager",
                        "Attenzione impossibile rimuovere municipio, sono presenti abitanti.");
                request.setAttribute("municipi_list_attribute",
                        municipioService.listAllMunicipi());
                request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
                return;
            }

            result = municipioInstance;

        } catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }

        request.setAttribute("municipio_delete", result);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/municipio/delete.jsp");
        rd.forward(request, response);	}

}
