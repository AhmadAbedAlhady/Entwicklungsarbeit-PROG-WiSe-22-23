/*
//------------------------------------------------------------------------------------------------------------



//------------------------------------------------------------------------------------------------------------
 */


package controll;

import model.LadeStation;

public class Haversin {
    public double calcDistance(LadeStation referenceStation, LadeStation comparisonStation) {
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
}
