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
        double[] location = reader.getLocation();

        System.out.println(location[0]);
        System.out.println(location[1]);
        System.out.println();
        System.out.println(reader.getStartTime());
        System.out.println();

        double[] elevation = reader.getElevation();
        double[] azimut = reader.getAzimut();

        for(int i=0; i<elevation.length; i++){
            System.out.println(azimut[i]+" "+elevation[i]);
        }
    }
}
