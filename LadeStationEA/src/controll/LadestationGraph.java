package controll;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.LadeStation;

public class LadestationGraph {

    private Map<LadeStation, List<LadeStation>> graph = new HashMap<>();
    private double maxDistance;

    public LadestationGraph(List<LadeStation> stations, double maxDistance) {
        this.maxDistance = maxDistance;
        Instant startZeit = Instant.now();
        for (LadeStation station : stations) {
            graph.put(station, new ArrayList<>());
        }

        for (LadeStation station : graph.keySet()) {
            for (LadeStation comparison : graph.keySet()) {
                if (!comparison.equals(station)) {
                    double distance = calcDistance(station, comparison);
                    if (distance <= maxDistance) {
                        graph.get(station).add(comparison);
                    }
                }
            }
        }
        Instant endZeit = Instant.now();
        System.out.println("Konstruiere Graph mit maximaler Entfernung von " + maxDistance);
        System.out.println("Anzahl Ladestationen: " + graph.size() + " (Graph in " + Duration.between(startZeit, endZeit).toMillis() + " Millisekunden konstruiert)");
    }

    private double calcDistance(LadeStation referenceStation, LadeStation comparisonStation) {
        double latDistance = Math.toRadians(comparisonStation.getBreitengrad() - referenceStation.getBreitengrad());
        double lonDistance = Math.toRadians(comparisonStation.getLaengengrad() - referenceStation.getLaengengrad());

        double a = Math.pow(Math.sin(latDistance / 2), 2) +
                Math.pow(Math.sin(lonDistance / 2), 2) *
                        Math.cos(referenceStation.getBreitengrad()) *
                        Math.cos(comparisonStation.getBreitengrad());

        // die Kugel ist die Erde mit mittlerem Radius r = 6371 m
        // wie in der Formel benötigt, 2 * r = 2 * 6371 = 12742
        return 12742 * Math.asin(Math.sqrt(a));
    }

    public Map<LadeStation, List<LadeStation>> getGraph() {
        return graph;
    }
}
