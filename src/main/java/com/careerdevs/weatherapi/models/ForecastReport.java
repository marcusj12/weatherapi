package com.careerdevs.weatherapi.models;

//import nested class
import com.careerdevs.weatherapi.models.Forecast.ForecastWeatherData;

public class ForecastReport {

    private String cityName;
    private String country;
    private int population;
    private  ForecastReportEntry[] reports;

// forecast is the argument; this is the constructor; Why did we create this constructor?
    public ForecastReport(Forecast forecast){
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population = forecast.getCity().getPopulation();

        reports = new ForecastReportEntry[forecast.getList().length];// What is this doing?

        for (int i = 0; i < forecast.getList().length; i++) {
            reports[i] = new ForecastReportEntry(forecast.getList()[i]);

        }
    }

    // Why did we duplicate the values; is this generating a new report?
    public static class ForecastReportEntry {
// not being modified; can be final
        private String dateTime;
        private String description;
        private String temp;
        private String percentageOfPrecipitation;

        public ForecastReportEntry(ForecastWeatherData wd) {
            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
            temp = wd.getMain().getTemp() + "°F";
            percentageOfPrecipitation = wd.getPop() * 100 + "%";
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

    public int getPopulation() {
        return population;
    }

    public ForecastReportEntry[] getReports() {
        return reports;
    }

    // Why did we delete the getters?
}


// Array list are less efficient code; when adding a new element it will create a brand-new array