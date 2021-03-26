import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static String repoPath = "/Users/timjaeger/Desktop/JuFo/Programme/Simulations/";

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        Reader[] data = new Reader[1];
        
        data[0] = new Reader(repoPath + "SimulationPrograms/datafiles/sunData/data0.sundata");
        
        for (int i = 0; i < data.length; i++) {

            data[i].read();
            Writer w = new Writer(repoPath + "SimulationPrograms/datafiles/deviations/deviation" + i + ".deviationdata", data[i]);
          
            for (double holdingTime = 5 * 60; holdingTime < 2 * 3600; holdingTime += 5 * 60) {

                Simulator s = new Simulator(5.7, 1.01, holdingTime);
                w.write(holdingTime, s.getDeviationOfLocation(data[i]));
            }
        }
    }
}
