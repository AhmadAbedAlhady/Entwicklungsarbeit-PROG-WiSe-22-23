package controll;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import View.MYIO;
import model.LadeStation;
import res.Strings;

public class Sortierer implements Strings {
    public Sortierer() throws IOException {
        sortiereLadestationen(DATEI_PATH);
    }

    public static void sortiereNachPostleitzahl(List<LadeStation> ladestationen) {
        Collections.sort(ladestationen, Comparator.comparingInt((LadeStation o) ->
                Integer.parseInt(o.getPostleitzahl())).thenComparingDouble(LadeStation::getAnschlussleistung));
    }

    public List<LadeStation> sortiereLadestationen(String dateiPfad) throws IOException {
        //Lade die Ladestationen
        List<LadeStation> ladestationen = Worngout.ueberpruefen(0);

        for (LadeStation ladeStation : ladestationen) {
            MYIO.ausgeben("Betreiber: " + ladeStation.getBetreiber() + "\tAdresse: " +
                    ladeStation.getStrasse() + " " + ladeStation.getHausnummer() + ", " +
                    ladeStation.getPostleitzahl() + "\t" + ladeStation.getOrt() + ",\t" + ladeStation.getBundesland()
                    + "\tBreitengrad: " + String.format("%.6f", ladeStation.getBreitengrad()) + "\tLängengrad: " +
                    String.format("%.6f", ladeStation.getLaengengrad()) + "\tAnschlussleistung: " + ladeStation.getAnschlussleistung());
        }
        MYIO.ausgeben(String.valueOf(ladestationen.size()));
// Sortiere die Ladestationen nach Postleitzahl
        sortiereNachPostleitzahl(ladestationen);

        return ladestationen;
    }
}
