package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ExchangeRateServices {

    public static double getExchangeRate(String from, String to) throws Exception{
       try{

           //free version of api is used
           //100 uses only available
           String apiURL = "http://api.exchangerate.host/convert?from=" + from + "&to=" + to;

           //open connection
           URL url = new URL(apiURL);
           HttpURLConnection connection = (HttpURLConnection)url.openConnection();
           connection.setRequestMethod("GET");

           //read response
           BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

           StringBuilder response = new StringBuilder();
           String line;

           while ((line = reader.readLine()) != null) {
                response.append(line);
           }

           //close bufferedReader
           reader.close();

           //parse JSON and return the result
           JSONObject json = new JSONObject(response.toString());

           if (json.has("result") && !json.isNull("result")) {
               return json.getDouble("result");
           } else {
               System.out.println("API response missing 'result': " + json.toString());
               return -1;
           }


       } catch(Exception e) {
           System.out.println("Error fetching exchange rate:"+ e.getMessage());
           return -1;
       }
    }
}
