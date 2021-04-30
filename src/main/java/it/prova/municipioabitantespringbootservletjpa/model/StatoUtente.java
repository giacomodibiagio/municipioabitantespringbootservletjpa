package it.prova.municipioabitantespringbootservletjpa.model;

public enum StatoUtente {
	ATTIVO,DISABILITATO,CREATO;

	public static StatoUtente traduciStatoUtente(String input) {
		StatoUtente[] stati = StatoUtente.values();
		for (StatoUtente statoItem : stati) {
			if (input.equalsIgnoreCase(statoItem.name())) {
				return statoItem;
			}
		}
		return null;
	}
}
