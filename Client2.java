import java.io.*;
import java.net.*;

public class Client2 {

    public static void main(String[] args) {
        try {
            String serverIP = "192.168.1.9"; 
            int serverPort = 10; 

            System.out.println("Connecting to server at " + serverIP + " on port " + serverPort + "...");
            
            Socket sock = new Socket(serverIP, serverPort);
            System.out.println("Connected to server.");

            DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter a message to send to the server: ");
            String message = br.readLine();

            dout.writeUTF(message);
            dout.flush();
            System.out.println("Message sent: " + message);

            dout.close();
            sock.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
