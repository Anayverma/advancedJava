import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUsingBufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Anay Verma 22BCE10120");
        System.out.print("Enter your name: ");
        String name = reader.readLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
