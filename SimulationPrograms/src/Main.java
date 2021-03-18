import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static Reader reader;

    static {
        try {
            reader = new Reader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        reader.read();
        System.out.println("Hallo");
    }
}
