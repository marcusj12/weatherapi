package com.careerdevs.weatherapi.models;

public class CurrentWeatherReport {
// create fields that store data and are accessible
    private String name;
    private String main;
    private String description;
    private String units;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private float lon;
    private float lat;


    //Allows us to pull in data; and store it.
    //a getter (method) that returns the information of an object

    public CurrentWeatherReport(
            String name,
            CurrentWeather.Coords coords,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather,
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

    public float getPressure() {return pressure;}

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

    public String getDescription() {return description;}

    public String getUnits() {return units;}


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"name\":\"").append(name).append('"');
        sb.append(", \"main\":\"").append(main).append('"');
        sb.append(", \"description\":\"").append(description).append('"');
        sb.append(", \"units\":\"").append(units).append(units.equals("imperial") ? "F" : "C").append("°\"");
        sb.append(", \"temp\":").append(temp).append(units.equals("imperial") ? "F" : "C").append("°\"");
        sb.append(", \"feelsLike\":").append(feelsLike).append(units.equals("imperial") ? "F" : "C").append("°\"");
        sb.append(", \"tempMin\":").append(tempMin).append(units.equals("imperial") ? "F" : "C").append("°\"");
        sb.append(", \"tempMax\":").append(tempMax).append(units.equals("imperial") ? "F" : "C").append("°\"");
        sb.append(", \"pressure\":").append(pressure);
        sb.append(", \"humidity\":").append(humidity);
        sb.append(", \"lon\":").append(lon);
        sb.append(", \"lat\":").append(lat);
        sb.append('}');
        return sb.toString();
    }
}
