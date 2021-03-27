import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        Reader[] data = new Reader[12];
        
        File directory = new File("../datafiles/sunData");
        File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++) {
            data[i] = new Reader(files[i].getPath());
        }
        
        for (int i = 0; i < data.length; i++) {

            data[i].read();
            Writer w = new Writer("../datafiles/deviations/deviation" + i + ".deviationdata", data[i]);
          
            for (double holdingTime = 5 * 60; holdingTime < 3 * 3600; holdingTime += 5 * 60) {

                Simulator s = new Simulator(0.468, 0.117, holdingTime);
                w.write(holdingTime, s.getDeviationOfLocation(data[i]));
            }
        }
    }
}
