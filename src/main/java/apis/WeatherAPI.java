package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;

//TODO: overload methods to allow for optional parameters
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
}
