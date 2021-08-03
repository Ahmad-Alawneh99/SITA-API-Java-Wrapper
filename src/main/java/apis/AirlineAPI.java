package apis;

import com.github.kevinsawicki.http.HttpRequest;
import org.json.JSONObject;

public class AirlineAPI {
    private String baseURL = "https://sitaopen.api.aero/data/v1/airlines";
    private String apiKey;
    private static AirlineAPI instance;

    public static AirlineAPI getInstance() {
        if (instance == null){
            instance = new AirlineAPI();
        }
        return instance;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject performRequest(String url, boolean showCargoAirlines) {
        String resultBody = HttpRequest.get(url, true, "showCargoAirlines", String.valueOf(showCargoAirlines))
                .header("X-apiKey", apiKey)
                .body();
        return new JSONObject(resultBody);
    }

    public JSONObject getAllAirlines(boolean showCargoAirlines) {
        return performRequest(baseURL, showCargoAirlines);
    }

    public JSONObject getAirlineByCode(String airlineCode, boolean showCargoAirlines) {
        String url = baseURL + "/" + airlineCode;
        return performRequest(url, showCargoAirlines);
    }

    public JSONObject matchAirlines(String partialAirlineName, boolean showCargoAirlines) {
        String url = baseURL + "/match/" + partialAirlineName;
        return performRequest(url, showCargoAirlines);
    }
}
