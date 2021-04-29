package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.municipioabitantespringbootservletjpa.dao.UtenteDAO;
import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

@Component
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDAO utenteDAO;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Utente> listAll() {
		return utenteDAO.list();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloElemento(Long id) {
		return utenteDAO.findOne(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		utenteDAO.update(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteDAO.insert(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) throws Exception {
		if (utenteDAO.list().size()==0 && utenteInstance.isAdmin()) {
			throw new Exception("Utente non eliminabile.");
		}else {
			utenteDAO.delete(utenteInstance);
		}
	}

	@Transactional
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) {
		// 'attacco' alla sessione di hibernate i due oggetti
		// così jpa capisce che se è già presente quel ruolo non deve essere inserito
		utenteEsistente = entityManager.merge(utenteEsistente);
		ruoloInstance = entityManager.merge(ruoloInstance);

		utenteEsistente.getRuoli().add(ruoloInstance);
		// l'update non viene richiamato a mano in quanto
		// risulta automatico, infatti il contesto di persistenza
		// rileva che utenteEsistente ora è dirty vale a dire che una sua
		// proprieta ha subito una modifica (vale anche per i Set ovviamente)

	}

	@Transactional(readOnly = true)
	public Utente findByUsernameAndPassword(String username, String password) {
		Optional<Utente> result = utenteDAO.findByUsernameAndPassword(username, password);
		return result.isPresent() ? result.get() : null;
	}

	@Transactional(readOnly = true)
	public Utente accedi(String username, String password) {
		Optional<Utente> result = utenteDAO.login(username, password);
		return result.isPresent() ? result.get() : null;
	}
	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente example) {
		return utenteDAO.findByExample(example);
	}
	@Transactional
	public Utente caricaSingoloUtenteConRuoli(Long id) {
		return utenteDAO.findEager(id);	
	}

}
