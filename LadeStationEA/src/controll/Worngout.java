package controll;

import View.MYIO;
import model.LadeStation;
import res.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Worngout implements Strings {
    public static List<LadeStation> validePositionen = new ArrayList<>();
    private static Instant startZeit;
    private static Instant endZeit;
    private static String zeile;
    private static List<String> zeilen;

    public static List<LadeStation> ueberpruefen(int i) throws IOException {

        startZeit = Instant.now();
        leseDatei(DATEI_PATH);
        MYIO.ausgeben("Lade Daten aus Datei: " + DATEI_PATH + " Eingelesene Zeilen: "
                + zeilen.size() + " (" + Duration.between(startZeit, endZeit).toMillis() +
                " Millisekunden)");
        //Startzeit für die Datenvalidierung
        //Validiere die Daten und speichere valide Daten in einer Collection

        for (String zeile : zeilen) {
            String[] werte = zeile.split(CSV_DELIMITER);
            double breite = Double.parseDouble(werte[6]);
            double laenge = Double.parseDouble(werte[7]);

            if (breite > -90 && breite < 90 && laenge > -180 && laenge < 180) {
                validePositionen.add(new LadeStation(werte[0], werte[1], werte[2],
                        werte[3], werte[4], werte[5], breite, laenge, Double.parseDouble(werte[8])));
            }
            if (i == 1) {
                MYIO.ausgeben("Trage Ladestation nicht ein. Breiten- oder Längengrad " +
                        "nicht korrekt: GeoPosition{breitenGrad=" + breite + ", laengenGrad=" +
                        laenge + "}   ");
            }
        }

        MYIO.ausgeben(Duration.between(startZeit, endZeit).toMillis() +
                "  Millisekunden)");
        MYIO.ausgeben("validierte Stationen" + " (" + validePositionen.size() + ")");
        //Endzeit für die Datenvalidierung
        endZeit = Instant.now();
        return validePositionen;
    }

    public static void leseDatei(String dateiPfad) throws IOException {
        //Startzeit für das Lesen der Datei
        startZeit = Instant.now();
        //Lese die Datei und speichere die Daten in einer Liste
        File datei = new File(dateiPfad);
        BufferedReader br = new BufferedReader(new FileReader(datei));
        zeilen = new ArrayList<>();
        while ((zeile = br.readLine()) != null) {
            zeilen.add(zeile);
        }
        br.close();
        //Endzeit für das Lesen der Datei
        endZeit = Instant.now();
    }

}

