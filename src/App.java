import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        String serverIp = "10.142.1.191";
        int port = 5550;
        String studentId = "22061001";

        try (
            Socket socket = new Socket(serverIp, port);
            PrintWriter out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
                true
            );
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            )
        ) {
            System.out.println("Sunucuya baglanildi. socket: " + socket);

            out.println("merhaba");

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Sunucudan: " + response);

                String cleaned = response.trim().toLowerCase();
                if (cleaned.contains("sana") && cleaned.contains("merhaba")) {
                    out.println("merhaba");
                    System.out.println("Merhaba mesajina cevap gonderildi.");
                } else if (cleaned.contains("derdin ne senin")) {
                    out.println(studentId);
                } else if (cleaned.contains("gorusuruz")) {
                    break;
                }
            }

            System.out.println("Baglanti kapatiliyor.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
