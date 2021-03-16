package si.fri.health;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.codahale.metrics.health.HealthCheck;

public class BasicHealthCheck extends HealthCheck {

    public BasicHealthCheck() { }

    @Override
    protected Result check() throws Exception {
        URL url = new URL("http://localhost:8080/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return Result.healthy("API working!");
        }
        return Result.unhealthy("API Failed");
    }
}