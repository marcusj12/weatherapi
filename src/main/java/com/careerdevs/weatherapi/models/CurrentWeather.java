package com.careerdevs.weatherapi.models;
// Models - modeling API data; creating a structure
public class CurrentWeather {

    private String name;
    private int timezone;
    private int visibility;
    private Coords coord;
    private Main main;
    private Weather[] weather;


// Getters
    public String getName() {
        return name;
    }
    public int getTimezone() {
        return timezone;
    }
    public int getVisibility() {
        return visibility;
    }
    public Coords getCoord() {
        return coord;
    }
    public Main getMain() {
        return main;
    }
    public Weather[] getWeather() {
        return weather;
    }


    public static class Coords {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }
        public float getLat() {
            return lat;
        }
    }

    public static class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float emp_max;
        private float pressure;
        private float humidity;

        public float getTemp() {
            return temp;
        }
        public float getFeels_like() {
            return feels_like;
        }
        public float getTemp_min() {
            return temp_min;
        }
        public float getEmp_max() {
            return emp_max;
        }
        public float getPressure() {
            return pressure;
        }
        public float getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", emp_max=" + emp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }
        public String getMain() {
            return main;
        }
        public String getDescription() {
            return description;
        }
        public String getIcon() {
            return icon;
        }
    }
}


/*
{
  "coord": {
    "lon": -71.0598,
    "lat": 42.3584
  },
  "weather": [
    {
      "id": 701,
      "main": "Mist",
      "description": "mist",
      "icon": "50d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 55.63,
    "feels_like": 55.17,
    "temp_min": 51.94,
    "temp_max": 57.99,
    "pressure": 1006,
    "humidity": 91
  },
  "visibility": 9656,
  "wind": {
    "speed": 6.91,
    "deg": 130
  },
  "clouds": {
    "all": 100
  },
  "dt": 1653002670,
  "sys": {
    "type": 2,
    "id": 2013408,
    "country": "US",
    "sunrise": 1652951947,
    "sunset": 1653004955
  },
  "timezone": -14400,
  "id": 4930956,
  "name": "Boston",
  "cod": 200
}
 */