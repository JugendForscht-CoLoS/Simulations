import java.io.*;
import java.util.ArrayList;

public class Reader{

    private int[] location = new int[2];
    private String startTime = "";
    private String date = "";
    private ArrayList<Integer> azimut = new ArrayList<Integer>();
    private ArrayList<Integer> elevation = new ArrayList<Integer>();

    private File file = new File("../../Workspace/data0.sundata");
    private BufferedReader reader = new BufferedReader(new FileReader(file));

    public Reader() throws FileNotFoundException {
    }

    public void read() throws IOException {

        int count = 0;
        String line = reader.readLine();

        while(line != null){
            if (count > 2){
                String[] sunPosition = line.split(";",1);
                this.azimut.add(Integer.parseInt(sunPosition[0]));
                this.elevation.add(Integer.parseInt(sunPosition[1]));
            }
            else if(count == 0) {
                String[] loc = line.split(",",1);
                this.location[0] = Integer.parseInt(loc[0];
                this.location[1] = Integer.parseInt(loc[1];
            }
            else if(count == 1) this.date = line;
            else {

            }

            line = reader.readLine();
            count++;
        }

        reader.close();
        
    }

    public int[] getLocation(){
        return this.location;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getDate() {
        return this.date;
    }

    public int[] getElevation() {
        Integer[] arr = new Integer[this.elevation.size()]
        return this.elevation.toArray(arr);
    }

    public int[] getAzimut() {
        return this.azimut;
    }
}