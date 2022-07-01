package com.careerdevs.weatherapi.validation;

import java.util.HashMap;

public class WeatherValidation {

    //returns a hashmap with a key
    public static HashMap<String, String>  validateQuery (String city, String units){

        HashMap<String, String> validationErrors = new HashMap<>();
        // Distinguish between the different type of errors(city names or units)

        if (city.trim().equals("")) {
            validationErrors.put("city", "City name required");

        } else if (
                !city.replaceAll("[^a-zA-Z -]", "").equals(city)
        ) {
            //System.out.println(cityName);
            // System.out.println(cityName.replaceAll("[^a-zA-z]","*").equals(cityName));
            // name should not include special chars/numbers
            validationErrors.put("city","Invalid City Name");
        }
        //validation - units
        // is it metric or imperial
        if (!units.equals("metric") && !units.equals(("imperial")))  {
            validationErrors.put("units","Units must be metric OR imperial  ");
        }
        return  validationErrors;
    }
}
