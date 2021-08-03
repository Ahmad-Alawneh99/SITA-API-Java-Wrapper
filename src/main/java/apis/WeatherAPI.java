package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;

public class WeatherAPI {
    private String baseURL = "https://sitaopen.api.aero/weather/v1";
    private String apiKey;
    private static WeatherAPI instance;

    public static WeatherAPI getInstance() {
        if (instance == null){
            instance = new WeatherAPI();
        }
        return instance;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject performRequest(HttpRequest request) {
        String resultBody = request.header("X-apiKey", apiKey)
                .body();
        return new JSONObject(resultBody);
    }

    public JSONObject getCurrentWeather(String airportCode, String lengthUnit, String temperatureScale) {
        String url = baseURL + "/current/" + airportCode;
        HttpRequest request = HttpRequest.get(
                url,
                true,
                "lengthUnit",
                lengthUnit,
                "temperatureScale",
                temperatureScale);

        return performRequest(request);
    }

    public JSONObject getCurrentWeather(String airportCode){
        return getCurrentWeather(airportCode, "K", "C");
    }

    public JSONObject getCurrentWeather(String airportCode, String lengthUnit) {
        return getCurrentWeather(airportCode, lengthUnit, "C");
    }

    public JSONObject getCurrentWeather(String airportCode, char temperatureScale) {
        return getCurrentWeather(airportCode, "K", ""+temperatureScale);
    }


    public JSONObject getCurrentWeatherAndForecast(String airportCode, int forecastDuration,
                                                   String lengthUnit, String temperatureScale) {
        String url = baseURL + "/combined/" + airportCode;
        HttpRequest request = HttpRequest.get(
                url,
                true,
                "duration",
                forecastDuration,
                "lengthUnit",
                lengthUnit,
                "temperatureScale",
                temperatureScale);

        return performRequest(request);
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode) {
        return getCurrentWeatherAndForecast(airportCode, 5, "K", "C");
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, int forecastDuration) {
        return getCurrentWeatherAndForecast(airportCode, forecastDuration, "K", "C");
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, int forecastDuration, String lengthUnit) {
        return getCurrentWeatherAndForecast(airportCode, forecastDuration, lengthUnit, "C");
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, int forecastDuration, char temperatureScale) {
        return getCurrentWeatherAndForecast(airportCode, forecastDuration, "K", ""+temperatureScale);
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, String lengthUnit) {
        return getCurrentWeatherAndForecast(airportCode, 5, lengthUnit, "C");
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, char temperatureScale) {
        return getCurrentWeatherAndForecast(airportCode, 5, "K", ""+temperatureScale);
    }

    public JSONObject getCurrentWeatherAndForecast(String airportCode, String lengthUnit, String temperatureScale) {
        return getCurrentWeatherAndForecast(airportCode, 5, lengthUnit, temperatureScale);
    }


    public JSONObject getForecast(String airportCode, int forecastDuration, String temperatureScale) {
        String url = baseURL + "/forecast/" + airportCode;
        HttpRequest request = HttpRequest.get(
                url,
                true,
                "duration",
                forecastDuration,
                "temperatureScale",
                temperatureScale);

        return performRequest(request);
    }

    public JSONObject getForecast(String airportCode) {
        return getForecast(airportCode, 5, "C");
    }

    public JSONObject getForecast(String airportCode, int forecastDuration) {
        return getForecast(airportCode, forecastDuration, "C");
    }

    public JSONObject getForecast(String airportCode, String temperatureScale) {
        return getForecast(airportCode, 5, temperatureScale);
    }
}
