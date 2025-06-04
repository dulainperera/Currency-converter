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
           String apiKey = "fca_live_Y9QRaz2Ty9lGsoq56LqLSFGCJwJgLdsj01tT1fYV";
           String apiURL = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_Y9QRaz2Ty9lGsoq56LqLSFGCJwJgLdsj01tT1fYV" + "&access_key=" + apiKey;

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

           if (json.has("data") && !json.isNull("data")) {
               JSONObject data = json.getJSONObject("data");

               if (data.has(to) && data.has(from)) {
                   double toRate = data.getDouble(to);
                   double fromRate = data.getDouble(from);

                   //calculate exchange rate
                   return toRate/fromRate;

               } else {
                   System.out.println("Currency not found in API response: " + json.toString());
                   return -1;
               }
           } else {
               System.out.println("API response missing 'data': " + json.toString());
               return -1;
           }


       } catch(Exception e) {
           System.out.println("Error fetching exchange rate:"+ e.getMessage());
           return -1;
       }
    }
}
