package com.caldroidsample.application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppContext {

    private static AppContext instance = null;
    public static Map mapYml = null;
    private static boolean compareDefaultDefault = true;
    private static boolean compareRoundedPrices = false;
    private static boolean compareTypeAirlines = false;
    private Date diaIdaAlerta = null;
    private Date diaVoltaAlerta = null;
    public static HashMap<String, Boolean> otas = new HashMap<>();
    public static HashMap<String, Boolean> resultsCompatible = new HashMap<>();

    public static void setCompareDefault(boolean c) {
        compareDefaultDefault = c;
        compareRoundedPrices = false;
        compareTypeAirlines = false;
    }

    public static boolean isCompareDefaultDefault() {
        return compareDefaultDefault;
    }

    public static boolean isCompareRoundedPrices() {
        return compareRoundedPrices;
    }

    public static boolean isCompareTypeAirlines() {
        return compareTypeAirlines;
    }

    public static void setCompareRoundedPrices(boolean c) {
        compareRoundedPrices = c;
        compareTypeAirlines = false;
        compareDefaultDefault = false;
    }

    public static void setCompareTypeAirlines(boolean c) {
        compareTypeAirlines = c;
        compareRoundedPrices = false;
        compareDefaultDefault = false;
    }

    private AppContext() {

    }

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public static Map getMapYml() {
        return mapYml;
    }

    public static void setMapYml(Map mapYml) {
        AppContext.mapYml = mapYml;
    }

    public Date getDiaIdaAlerta() {
        return diaIdaAlerta;
    }

    public void setDiaIdaAlerta(Date diaIdaAlerta) {
        this.diaIdaAlerta = diaIdaAlerta;
    }

    public Date getDiaVoltaAlerta() {
        return diaVoltaAlerta;
    }

    public void setDiaVoltaAlerta(Date diaVoltaAlerta) {
        this.diaVoltaAlerta = diaVoltaAlerta;
    }

    public static HashMap<String, Boolean> getOtas() {
        return otas;
    }

    public static void setOtas(HashMap<String, Boolean> otas) {
        AppContext.otas = otas;
    }

    public static HashMap<String, Boolean> getResultsCompatible() {
        return resultsCompatible;
    }

    public static void setResultsCompatible(HashMap<String, Boolean> resultsCompatible) {
        AppContext.resultsCompatible = resultsCompatible;
    }
}


