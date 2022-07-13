package com.careerdevs.weatherapi.controllers;


import com.careerdevs.weatherapi.models.Forecast;
import com.careerdevs.weatherapi.models.ForecastReport;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getForecastByCityPathVar(RestTemplate restTemplate, @PathVariable String city) {
        try {
            String units = "imperial";
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(city, units);

            //If validation fails in any way, return error  message(s)
            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);// return an array of all errors; hashmaps are best
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            //Create URL
            String url = BASE_URL + queryString;

            // The request from rest template; going to return a forecast for us; using get for object and
            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            //generate a report
            //System.out.println("City:" + owRes.getCity().getName() + ", " + owRes.getCity().getCountry() + " -  Population: " + owRes.getCity().getPopulation());
            // System.out.println("Temp in 3 hours:" + owRes.getList()[0].getMain().getTemp());
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));

            //catching "exception for worst case/unexpected scenarios; otherwise this allows us to identify where errors occur and what type of error it is
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City not found: " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @GetMapping("/city")
    public ResponseEntity<?> getForecastByCityRequestParams(
            RestTemplate restTemplate,
            @RequestParam(value = "name") String city,
            @RequestParam(defaultValue = "imperial") String units,
            @RequestParam(defaultValue = "40") String count
    ) {
        try {
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(city, units);

            //validate count is a number
            if (!count.replaceAll("[^0-9]", "").equals(count)) { // regular expression that matches texts in the string; if matches it replaced with nothing
                validationErrors.put("count", "Count must be a number"); // checking error before air message
            } else if (Integer.parseInt(count) < 1 || Integer.parseInt(count) > 40) {
                validationErrors.put("count", "Count must be 1 and 40");
            }
            //If validation fails in any way, return error  message(s)
            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);// return an array of all errors; hashmaps are best
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey + "&cnt=" + count;
            //Create URL
            String url = BASE_URL + queryString;
            // The request from rest template; going to return a forecast for us; using get for object and
            Forecast owRes = restTemplate.getForObject(url, Forecast.class);

            //generate a report
            //System.out.println("City:" + owRes.getCity().getName() + ", " + owRes.getCity().getCountry() + " -  Population: " + owRes.getCity().getPopulation());
            // System.out.println("Temp in 3 hours:" + owRes.getList()[0].getMain().getTemp());
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));

            //catching "exception for worst case/unexpected scenarios; otherwise this allows us to identify where errors occur and what type of error it is
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City not found: " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}


