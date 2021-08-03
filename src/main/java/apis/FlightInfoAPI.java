package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;
//TODO: refactor query parameters
public class FlightInfoAPI {
    private String baseURL = "https://flifo.api.aero/flifo/flightinfo/v1/flights";
    private String apiKey;
    private static FlightInfoAPI instance;

    public static FlightInfoAPI getInstance() {
        if (instance == null){
            instance = new FlightInfoAPI();
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

    public JSONObject getFlight(String airlineCode, String flightNumber, String[] queryParameters) {
        String url = baseURL + "/airline/" + airlineCode + "/flightNumber/" + flightNumber;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getFlight(String airportCode, String airlineCode,
                                String flightNumber, String direction, String[] queryParameters) {
        String url = baseURL + "/airport/" + airportCode + "/airline/" + airlineCode +
                "/flightNumber/" + flightNumber + "/direction/" + direction;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getAllFlightsAtAirportAndAirline(String airportCode, String airlineCode,
                                                        String direction, String[] queryParameters) {
        String url = baseURL + "/airport/" + airportCode + "/airline/" + airlineCode + "/direction/" + direction;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getAllFlightsAtAirport(String airportCode, String direction, String[] queryParameters) {
        String url = baseURL + "/airport/" + airportCode + "/direction/" + direction;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getUpdatesForFlightsAtAirport(String airportCode, String[] queryParameters) {
        String url = baseURL + "/updates/airport/" + airportCode;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getUpdatesForFlightsAtAirportAndAirline(String airportCode, String airlineCode,
                                                              String[] queryParameters) {
        String url = baseURL + "/updates/airport/" + airportCode + "/airline/" + airlineCode;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getFlightUpdates(String airportCode, String airlineCode,
                                       String flightNumber, String[] queryParameters) {
        String url = baseURL + "/updates/airport/" + airportCode +
                "/airline/" + airlineCode + "/flightNumber/" + flightNumber;
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }

    public JSONObject getAllFlightsUpdates(String[] queryParameters) {
        String url = baseURL + "/updates";
        HttpRequest request = HttpRequest.get(url, true, (Object) queryParameters);
        return performRequest(request);
    }


}
