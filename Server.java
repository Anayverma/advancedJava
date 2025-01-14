import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10);
            System.out.println("Server started. Waiting for a client to connect...");

            Socket sock = serverSocket.accept();
            System.out.println("Client connected: " + sock);

            DataInputStream din = new DataInputStream(sock.getInputStream());
            String message = din.readUTF();

            System.out.println("Message from client: " + message);

            din.close();
            sock.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
