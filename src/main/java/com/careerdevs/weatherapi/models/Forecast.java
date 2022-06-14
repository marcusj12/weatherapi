package com.careerdevs.weatherapi.models;

import com.careerdevs.weatherapi.models.CurrentWeather.*; //* allows us to import all inner classes vs just one

public class Forecast {

    private City city;

    private ForecastWeather[] list;

    public static class City {
        private String name;
        private Coords coords;
        private String country;
        private int population;


        public String getName() {
            return name;
        }

        public Coords getCoords() {
            return coords;
        }

        public String getCountry() {
            return country;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static class ForecastWeather  extends  CurrentWeather{
        private String dt_txt;

        private float pop;
        private String name;

        public float getPop() {
            return pop;
        }

        public String getDt_txt() {
            return dt_txt;
        }
    }

    public City getCity() {
        return city;
    }

    public ForecastWeather[] getList() {
        return list;
    }
}




/*{
        "cod": "200",
        "message": 0,
        "cnt": 40,
        "list": [
        {
        "dt": 1647345600,
        "main": {
        "temp": 286.88,
        "feels_like": 285.93,
        "temp_min": 286.74,
        "temp_max": 286.88,
        "pressure": 1021,
        "sea_level": 1021,
        "grnd_level": 1018,
        "humidity": 62,
        "temp_kf": 0.14
        },
        "weather": [
        {
        "id": 804,
        "main": "Clouds",
        "description": "overcast clouds",
        "icon": "04d"
        }
        ],
        "clouds": {
        "all": 85
        },
        "wind": {
        "speed": 3.25,
        "deg": 134,
        "gust": 4.45
        },
        "visibility": 10000,
        "pop": 0,
        "sys": {
        "pod": "d"
        },
        "dt_txt": "2022-03-15 12:00:00"
        },

        ],
        "city": {
        "id": 2643743,
        "name": "London",
        "coord": {
        "lat": 51.5073,
        "lon": -0.1277
        },
        "country": "GB",
        "population": 1000000,
        "timezone": 0,
        "sunrise": 1647324903,
        "sunset": 1647367441
        }
        }
        */

