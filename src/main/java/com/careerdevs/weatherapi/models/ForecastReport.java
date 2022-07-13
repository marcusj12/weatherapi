package com.careerdevs.weatherapi.models;

//import nested class
import com.careerdevs.weatherapi.models.Forecast.ForecastWeatherData;

import java.util.ArrayList;

public class ForecastReport {

    private final String cityName;
    private final String country;
    private final int population;
    private final CurrentWeather.Coords coords;
    private final int reportsCount;
    private final ArrayList<ForecastReportEntry> reports;

// forecast is the argument; this is the constructor; Why did we create this constructor?
    public ForecastReport(Forecast forecast, String units){
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();
        this.coords = forecast.getCity().getCoord();
        reportsCount = forecast.getList().length;

        //reports = new ForecastReportEntry[forecast.getList().length];// What is this doing?
        reports = new ArrayList<>();

        for (int i = 0; i < forecast.getList().length; i++) {
            reports.add(new ForecastReportEntry(forecast.getList()[i], units));

        }
    }

    // Why did we duplicate the values; is this generating a new report?
    public static class ForecastReportEntry {
// not being modified; can be final
        private final String dateTime;
        private final String description;
        private final String temp;
        private final String percentageOfPrecipitation;

        public ForecastReportEntry(ForecastWeatherData wd, String units) {
            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
//// Ternary operator; if units equal imperial; then use the string that contains f otherwise C            
            temp = wd.getMain().getTemp() + "°" + (units.equals("imperial") ? "F" : "C"); 
            percentageOfPrecipitation = (wd.getPop() * 100) + "%";
        }

        public String getDescription() {
            return description;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getTemp() {
            return temp;
        }

        public String getPercentageOfPrecipitation() {
            return percentageOfPrecipitation;
        }

    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public int getReportsCount () {return reportsCount; }

    public int getPopulation() {
        return population;
    }

    public CurrentWeather.Coords getCoords() {return coords;}

    public ArrayList<ForecastReportEntry> getReports() {
        return reports;
    }

    // Why did we delete the getters?
}


// Array list are less efficient code; when adding a new element it will create a brand-new array