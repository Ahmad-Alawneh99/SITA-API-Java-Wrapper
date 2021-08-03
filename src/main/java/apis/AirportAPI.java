package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;

public class AirportAPI {
    private String baseURL = "https://sitaopen.api.aero/data/v3/airports";
    private String apiKey;
    private static AirportAPI instance;

    public static AirportAPI getInstance() {
        if (instance == null) {
            instance = new AirportAPI();
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

    public JSONObject getAllAirports(String sortBy) {
        HttpRequest request = HttpRequest.get(baseURL, true, "sortBy", sortBy);
        return performRequest(request);
    }

    public JSONObject getAllAirports() {
        return getAllAirports("name");
    }


    public JSONObject getAirportByCode(String airportCode) {
        String url = baseURL + "/" + airportCode;
        HttpRequest request = HttpRequest.get(url, true);
        return performRequest(request);
    }


    public JSONObject getDistanceBetweenTwoAirports(String startAirportCode, String endAirportCode, String unit) {
        String url = baseURL + "/distance/" + startAirportCode + "/" + endAirportCode;
        HttpRequest request = HttpRequest.get(url, true, "units", unit);
        return performRequest(request);
    }

    public JSONObject getDistanceBetweenTwoAirports(String startAirportCode, String endAirportCode) {
        return getDistanceBetweenTwoAirports(startAirportCode, endAirportCode, "km");
    }


    public JSONObject getNearestAirport(double latitude, double longitude, int maxAirports) {
        String url = baseURL + "/nearest/" + latitude + "/" + longitude;
        HttpRequest request = HttpRequest.get(url, true, "maxAirports", maxAirports);
        return performRequest(request);
    }

    public JSONObject getNearestAirport(double latitude, double longitude) {
        return getNearestAirport(latitude, longitude, 1);
    }


    public JSONObject matchAirports(String partialAirportName) {
        String url = baseURL + "/match/" + partialAirportName;
        HttpRequest request = HttpRequest.get(url, true);
        return performRequest(request);
    }

    public JSONObject isInSameCountry(String firstAirport, String secondAirport) {
        String url = baseURL + "/samecountry/" + firstAirport + "/" + secondAirport;
        HttpRequest request = HttpRequest.get(url, true);
        return performRequest(request);
    }

}
