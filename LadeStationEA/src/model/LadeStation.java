package model;

import res.Strings;

public class LadeStation {
    private final String betreiber;
    private final String strasse;
    private final String hausnummer;
    private final String postleitzahl;
    private final String ort;
    private final String bundesland;
    private final double breitengrad;
    private final double laengengrad;
    private final double anschlussleistung;

    public LadeStation(String betreiber, String strasse, String hausnummer,
                       String postleitzahl, String ort, String bundesland,
                       double breitengrad, double laengengrad, double anschlussleistung) {
        this.betreiber = betreiber;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.bundesland = bundesland;
        this.breitengrad = breitengrad;
        this.laengengrad = laengengrad;
        this.anschlussleistung = anschlussleistung;
    }

    public LadeStation(String csvRecord) {
        // Get all tokens available in line
        String[] tokens = csvRecord.split(Strings.CSV_DELIMITER);

        this.betreiber = tokens[0];
        this.strasse = tokens[1];
        this.hausnummer = tokens[2];
        this.postleitzahl = tokens[3];
        this.ort = tokens[4];
        this.bundesland = tokens[5];
        this.breitengrad = Double.parseDouble(tokens[6]);
        this.laengengrad = Double.parseDouble(tokens[7]);
        this.anschlussleistung = Double.parseDouble(tokens[8]);
    }

    public String getBetreiber() {
        return betreiber;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getBundesland() {
        return bundesland;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }

    public double getAnschlussleistung() {
        return anschlussleistung;
    }
}