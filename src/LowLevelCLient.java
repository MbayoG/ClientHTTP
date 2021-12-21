import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LowLevelCLient {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 8080;
        Socket clientSocket;
        BufferedReader is;
        PrintWriter os;
        try {
            clientSocket = new Socket(hostname, port);

            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream());

            //Construction et envoi d'une requête GET
            StringBuilder request = new StringBuilder("GET /clock.php HTTP/1.0\r\n");
            request.append("Host: "+ hostname +"\r\n");
            request.append("Accept: text/plain, text/html, application/json\r\n");
            request.append("\r\n");

            os.println(request.toString());
            os.flush();

            //Reception de la réponse
            String response;
            while((response = is.readLine()) != null){
                System.out.println(response);

                //fait quelque-chose en fonction d'un paramètre
                if(response.split(":")[0].equals("Vary")){
                    System.out.println("YOdleHiHo!");
                }
                if(response.split(":").length > 1 && response.split(":")[0].charAt(0) == 'C'){
                    System.out.println("HEEEEEEEHO!");
                }
            }



            //fermetures des stream/socket
            os.close();
            is.close();
            clientSocket.close();

        } catch (IOException ex){
            //M'indique si il y a un problème dans le try
            System.out.println("gnagnagna");
        }
    }
}
