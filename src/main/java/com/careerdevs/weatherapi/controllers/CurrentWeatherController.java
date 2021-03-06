package com.careerdevs.weatherapi.controllers;
// Controllers are used to take in API request
import com.careerdevs.weatherapi.models.CurrentWeather;
import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    //Inversion of control; taking control away from ourselves and give it to framework of springboot for ease of automation; autowired annotation.
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";// BaseURL used to reuse parts of URl that will remain the same


    @GetMapping("/city/{cityName}")
    // Want request from external api; so we use ResTemplate; add it as a parameter
    // ResponseEntity ; a way for a generic class; allows for fine level of control for the status, data, and headers of our responses
    public ResponseEntity<?> getCurrentWeatherByCityPV(RestTemplate restTemplate, @PathVariable String cityName) { // allows anyone to choose any given city
// The catch block; used for error handling; handle code in between try-catch; if error occurs; executes under catch; can add multiple *error executions*? under catch block to; creating *dynamic replies*?
        try {
            String units = "imperial";
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(cityName, units);

            //If validation fails in any way, return error  message(s)
            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);// return an array of all erros; hasmaps are best
            }

            String apiKey = env.getProperty("OW_API_KEY"); // Can access any properties within application.properties without sharing when uploading code
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=" + units;
            String openWeatherURL = BASE_URL + queryString;

            System.out.println();
            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class); // Client that allow calls to be made to weather api

            assert owRes != null;
//            System.out.println("City: " + openWeatherResponse.getName());
//            System.out.println("Temp: " + openWeatherResponse.getMain().getTemp());
//            System.out.println("Description: " + openWeatherResponse.getWeather()[0].getDescription());

            return ResponseEntity.ok(owRes.createReport(units));


            //We  run code that responds to an error exception by entering faulty input data; we use the error exception to make custom reply

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found: " + cityName);


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass()); // indicates what the exact exception was
            return ResponseEntity.internalServerError().body(e.getMessage());// we don't know the error response by default; so we create a generic exception to catch all general errors c
        }

    }



    /* Whenever want to grab more than "one" request from the client, it is best to move away from PathVariables and use RequestParameters (RequestParam)
    The help clean up our request without showing how the request is set up.

    @RequestParam is usually used for requesting more than one dynamic value in the url
     api/current/?city=boston
     */

// http:localhost:8000/api/current/city?name=boston&units=imperial
    @GetMapping("/city")
    public ResponseEntity<?> getCurrentWeatherByCityRequestParams(
            RestTemplate restTemplate,// client; initiates call
            @RequestParam(value = "name") String cityName,
            @RequestParam(defaultValue = "imperial") String units
    ) {
        try {
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(cityName, units);

            //If validation fails in any way, return error  message(s)
            if (validationErrors.size() !=0) {
                return ResponseEntity.badRequest().body(validationErrors);// return an array of all erros; hasmaps are best
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=" + units;
            String openWeatherURL = BASE_URL + queryString;

            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);
            assert owRes!=null;

            return ResponseEntity.ok(owRes.createReport(units));

        } catch (HttpClientErrorException.NotFound e) { //cityName validation
            return ResponseEntity.status(404).body("City Not Found: " + cityName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass()); // indicates what the exact exception was
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
// Methods are static when you don't create instances of them

