package it.prova.municipioabitantespringbootservletjpa.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.StatoUtente;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

public class UtilityForm {

	public static Municipio createMunicipioFromParams(String codiceParam, String descrizioneParam,
			String ubicazioneParam) {

		Municipio result = new Municipio(descrizioneParam, codiceParam, ubicazioneParam);
		return result;
	}

	public static boolean validateRegistaBean(Municipio municipioToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(municipioToBeValidated.getCodice())
				|| StringUtils.isBlank(municipioToBeValidated.getDescrizione())
				|| StringUtils.isBlank(municipioToBeValidated.getUbicazione())) {
			return false;
		}
		return true;
	}

	public static Abitante createAbitanteFromParams(String nomeInputParam, String cognomeInputParam,
			String residenzaInputParam, String dataDiNascitaStringParam, String municipioIdStringParam) {

		Abitante result = new Abitante(nomeInputParam, cognomeInputParam, residenzaInputParam);
		result.setDataDiNascita(parseDateFromString(dataDiNascitaStringParam));
		if (NumberUtils.isCreatable(municipioIdStringParam)) {
			result.setMunicipio(new Municipio(Long.parseLong(municipioIdStringParam)));
		}
		return result;
	}

	public static boolean validateAbitanteBean(Abitante abitanteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(abitanteToBeValidated.getNome())
				|| StringUtils.isBlank(abitanteToBeValidated.getCognome())
				|| StringUtils.isBlank(abitanteToBeValidated.getResidenza())
				|| abitanteToBeValidated.getDataDiNascita() == null || abitanteToBeValidated.getMunicipio() == null
				|| abitanteToBeValidated.getMunicipio().getId() == null
				|| abitanteToBeValidated.getMunicipio().getId() < 1) {
			return false;
		}
		return true;
	}

	public static Date parseDateFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Utente createUtenteFromParams(String usernameInput, String passwordInput, String nomeInputParam, String cognomeInputParam) {

		Utente result = new Utente(usernameInput, passwordInput, nomeInputParam, cognomeInputParam, new Date());
		
		return result;
	}

	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
			return false;
		}
		return true;
	}
	public static Utente prepareSearchUtenteFromParams(String usernameInput, String nomeInput, String cognomeInput,
			String statoInput, String[] idRuoloArrayInput, Date dateInput) {

		Utente result = new Utente(usernameInput, nomeInput, cognomeInput, dateInput);

		// setto lo stato utente fisso a creato appena viene creato
		StatoUtente statoParsed = StringUtils.isNotBlank(statoInput) ? StatoUtente.traduciStatoUtente(statoInput) : null;

		result.setStato(statoParsed);

		Set<Ruolo> insiemeDiRuoli = null;
		if (idRuoloArrayInput != null) {

			insiemeDiRuoli = new HashSet<>();

			for (String idRuoloItem : idRuoloArrayInput) {

				Ruolo ruoloBean = new Ruolo();
				ruoloBean.setId(Long.parseLong(idRuoloItem));
				insiemeDiRuoli.add(ruoloBean);

			}

		}

		result.setRuoli(insiemeDiRuoli);

		return result;
	}
	
	
//	public static boolean validateUtenteBean(Utente utenteToBeValidated, String passwordRepeat) {
//		// prima controlliamo che non siano vuoti i parametri
//		if (StringUtils.isBlank(utenteToBeValidated.getNome()) || StringUtils.isBlank(utenteToBeValidated.getCognome())
//				|| ! passwordRepeat.equals(utenteToBeValidated.getPassword())
//				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
//				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
//			return false;
//		}
//		return true;
//	}

}
