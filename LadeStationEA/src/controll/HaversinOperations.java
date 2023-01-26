package controll;

import View.MYIO;
import model.LadeStation;
import res.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class HaversinOperations implements Strings {
    private Instant startZeit;
    private Instant endZeit;
    List<LadeStation> ladeStationen = new ArrayList<>(); // Default to empty list.
    public List<LadeStation> ladeDaten(String dateiPfad) throws IOException {
        // Startzeit für das Lesen der Datei
        startZeit = Instant.now();

        // Lese die Datei und speichere die Daten in einer Liste
        try (BufferedReader csvFileReader = new BufferedReader(new FileReader(dateiPfad))) {
            String line = "";

            // Lese die Datei Zeile für Zeile
            while ((line = csvFileReader.readLine()) != null) {
                // add the new station to the list
                ladeStationen.add(new LadeStation(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Endzeit für das Lesen der Datei
        endZeit = Instant.now();
        MYIO.ausgeben("Lade Daten aus Datei: " + dateiPfad + "\nEingelesene LadeStationen: " + ladeStationen.size() +
                " (Millisekunden: " + Duration.between(startZeit, endZeit).toMillis() + ")");

        return ladeStationen;
    }

    public void removeRedundantStations(int epsilon, int maxDistance) {
        // Prüfe, ob die Argumente korrekt sind
        if (epsilon < 0 || maxDistance < 0) {
            MYIO.ausgeben("Ungültige Argumente");
            return;
        }

        // Create a copy of the original list to compare against
        List<LadeStation> nonRedundantStations = new ArrayList<>(ladeStationen);

        // Iterate over the original list
        for (int i = 0; i < ladeStationen.size(); ++i) {
            MYIO.ausgeben("-------------------------------------------------------------------------------------");
            MYIO.ausgeben("Station: " + i);

            // pick the reference station
            LadeStation referenceStation = ladeStationen.get(i);

            // remove the reference station from the comparison
            nonRedundantStations.remove(i);

            // Create an instance of the Haversin class
            Haversin haversin = new Haversin();

            // Iterate over the copy of the list
            for (int j = 0; j < nonRedundantStations.size(); ++j) {
                // Hier ist der Aufruf der calcDistance Methode
                double Entfernung = haversin.calcDistance(referenceStation, nonRedundantStations.get(j));
// Überprüfe, ob die Entfernung kleiner als ((Epsilon)) ist
                if (Entfernung <= epsilon || Entfernung > maxDistance) {
                    nonRedundantStations.remove(j);
                }
            } MYIO.ausgeben("Anzahl Ladestationen nach Löschung redundanter Stationen: "
                    + nonRedundantStations.size() + "");

            // Update the original list with the non-redundant stations
            ladeStationen = nonRedundantStations;
        }
    }
}