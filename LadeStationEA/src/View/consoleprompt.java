package View;

import controll.HaversinOperations;
import controll.LadestationGraph;
import controll.Sortierer;

import controll.Worngout;
import model.LadeStation;
import res.Strings;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class consoleprompt {
    public static List<LadeStation> ueberpruefen(int i) throws IOException {

        Scanner input = new Scanner(System.in); // Erstelle ein Scanner-Objekt, um Eingaben von der Tastatur zu lesen
        MYIO.ausgeben("Welche Ausgabe möchten Sie anzeigen?");
        MYIO.ausgeben("1. Teilaufgabe 1");
        MYIO.ausgeben("2. Teilaufgabe 2");
        MYIO.ausgeben("3. Teilaufgabe 3");
        MYIO.ausgeben("4. Teilaufgabe 4");
        int auswahl = input.nextInt(); // Lese die nächste Integer-Eingabe von der Tastatur

        switch (auswahl) {
            case 1:
                Worngout.ueberpruefen(1);
                break;
            case 2:
                Sortierer sortierer = new Sortierer();
                break;
            case 3:
               /* HaversinOperations stationenOps = new HaversinOperations();
                List<LadeStation> ladeStationen = stationenOps.ladeDaten(Strings.DATEI_PATH);
                // Sortieren und organisieren Sie die Liste, bevor der Vergleich durchgeführt wird
                // ...
                stationenOps.removeRedundantStations(25, 250);
                break;


                */


            case 4:
                int epsilon = 25;
                int maxDistance = 250;
               HaversinOperations stationenOps2 = new HaversinOperations();
                List<LadeStation> ladeStationen = stationenOps2.ladeDaten(Strings.DATEI_PATH);
                // Reduce the number of stations using epsilon
                stationenOps2.removeRedundantStations(epsilon, maxDistance);
                // Build the graph using the reduced list of stations
                LadestationGraph graph = new LadestationGraph(ladeStationen, maxDistance);
                // Access the graph using the getGraph() method
                Map<LadeStation, List<LadeStation>> graphMap = graph.getGraph();
                // Do something with the graph, for example, print the nodes and their neighbors
                for (LadeStation station : graphMap.keySet()) {
                    System.out.print(station + ": ");
                    for (LadeStation neighbor : graphMap.get(station)) {
                        System.out.print(neighbor + " ");
                    }
                    System.out.println();
                }
                break;

            default:
                MYIO.ausgeben("Ungültige Auswahl. Bitte wählen Sie eine Zahl von 1 bis 4.");
        }
        return null;
    }
}

