import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class ArithmeticServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10);
            System.out.println("Server started. Waiting for client...");

            Socket sock = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(sock.getInputStream());
            DataOutputStream dout = new DataOutputStream(sock.getOutputStream());

            String equation = din.readUTF();
            System.out.println("Received equation: " + equation);

            // Process the equation
            StringTokenizer st = new StringTokenizer(equation);
            int operand1 = Integer.parseInt(st.nextToken());
            String operator = st.nextToken();
            int operand2 = Integer.parseInt(st.nextToken());

            int result;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }

            dout.writeUTF(String.valueOf(result));
            dout.flush();

            din.close();
            dout.close();
            sock.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
