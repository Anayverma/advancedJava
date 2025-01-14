import javax.ejb.Remote;

@Remote
public interface AdderImplRemote {
    int add(int a, int b);
    int subtract(int a, int b); // New method for subtraction
    int multiply(int a, int b); // New method for multiplication
}
