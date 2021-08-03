package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;

public class AirlineAPI {
    private final String baseURL = "https://sitaopen.api.aero/data/v1/airlines";
    private String apiKey;
    private static AirlineAPI instance;

    public static AirlineAPI getInstance() {
        if (instance == null){
            instance = new AirlineAPI();
        }
        return instance;
    }

    private AirlineAPI() {

    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject performRequest(HttpRequest request) {
        String resultBody = request.header("X-apiKey", apiKey)
                .body();
        return new JSONObject(resultBody);
    }

    public JSONObject getAllAirlines(String sortBy, boolean showCargoAirlines) {
        HttpRequest request = HttpRequest.get(baseURL, true, "sortBy",
                sortBy, "showCargoAirlines", showCargoAirlines);
        return performRequest(request);
    }

    public JSONObject getAllAirlines() {
        return getAllAirlines("name",false);
    }

    public JSONObject getAllAirlines(String sortBy) {
        return getAllAirlines(sortBy, false);
    }

    public JSONObject getAllAirlines(boolean showCargoAirlines) {
        return getAllAirlines("name", showCargoAirlines);
    }


    public JSONObject getAirlineByCode(String airlineCode, boolean showCargoAirlines) {
        String url = baseURL + "/" + airlineCode;
        HttpRequest request = HttpRequest.get(url, true, "showCargoAirlines", showCargoAirlines);
        return performRequest(request);
    }

    public JSONObject getAirlineByCode(String airlineCode) {
        return getAirlineByCode(airlineCode, false);
    }


    public JSONObject matchAirlines(String partialAirlineName, boolean showCargoAirlines) {
        String url = baseURL + "/match/" + partialAirlineName;
        HttpRequest request = HttpRequest.get(url, true, "showCargoAirlines", showCargoAirlines);
        return performRequest(request);
    }

    public JSONObject matchAirlines(String partialAirlineName) {
        return matchAirlines(partialAirlineName, false);
    }
}
