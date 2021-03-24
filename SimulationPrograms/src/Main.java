import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        Reader[] data = new Reader[1];
        
        data[0] = new Reader("C:/Users/Jonas/IdeaProjects/Simulations/SimulationPrograms/datafiles/data0.sundata");
        
        for (Reader reader: data) {

            reader.read();
            Writer w = new Writer("C:/Users/Jonas/IdeaProjects/Simulations/SimulationPrograms/datafiles/deviations/deviation0.sundata", reader);
          
            for (double holdingTime = 5 * 60; holdingTime < 2 * 3600; holdingTime += 5 * 60) {

                Simulator s = new Simulator(5.7, 1.01, holdingTime);
                w.write(holdingTime, s.getDeviationOfLocation(reader));
            }
        }
    }
}
