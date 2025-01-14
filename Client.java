import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket sock = new Socket("localhost", 10);
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
