package com.careerdevs.weatherapi.models;

public class CurrentWeatherReport {
// create fields that store data and are accessible to a metv  hod
    private String units;
    private String name;
    private float lon;
    private float lat;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private String main;
    private String description;

    //Allows us to pull in data; and store it.
    //a getter (method) that returns the information of an object

    public CurrentWeatherReport(
            String name,
            CurrentWeather.Coords coords,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather
            String units
    ){
        this.name = name;
        this.units = units;
        lon = coords.getLon();
        lat = coords.getLat();
        temp = main.getTemp();
        feelsLike = main.getFeels_like();
        tempMin = main.getTemp_min();
        tempMax= main.getEmp_max();
        pressure = main.getPressure();
        humidity = main.getHumidity();
        this.main = weather.getMain();
        description = weather.getDescription();
    }


    public String getName() {
        return name;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }


}
