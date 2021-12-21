import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CLientHTTP {
    public static void main(String[] args) {
        Map<String, String> parameters = new HashMap<>();
        StringBuilder hostname = new StringBuilder("http://google.com");
        //Récupère les paramètres HTTP et les envoie

        parameters.put("hl", "fr");
        //parameters.put("q", "java");

        try {
            //Créer la requête
            hostname.append(ParameterStringBuilder.getParamsString(parameters));
            URL url = new URL(hostname.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            //Ajoute des headers à la requête
            con.setRequestProperty("Content-Type", "application/json");
            //Lit le header de la connexion
            //String contentType = con.getHeaderField("Content-Type");

            //Met un timeout de 5 secondes
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            //lit la réponse
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);


            con.disconnect();
        } catch(Exception e)
        {

        }
    }
}

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder("?");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}