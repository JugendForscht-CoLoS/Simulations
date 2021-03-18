import java.io.*;
import java.util.ArrayList;

public class Reader{

    private int[] location = new int[2];
    private int startTime = 0;
    private String date = "";
    private ArrayList<Double> azimut = new ArrayList<Double>();
    private ArrayList<Double> elevation = new ArrayList<Double>();

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
                this.azimut.add(Double.parseDouble(sunPosition[0]));
                this.elevation.add(Double.parseDouble(sunPosition[1]));
            }
            else if(count == 0) {
                String[] loc = line.split(",",1);
                this.location[0] = Integer.parseInt(loc[0];
                this.location[1] = Integer.parseInt(loc[1];
            }
            else if(count == 1) this.date = line;
            else {
                String[] time = line.split(":",2);
                this.startTime = 60*Integer.parseInt(time[0]) + Integer.parseInt(time[1]);
            }

            line = reader.readLine();
            count++;
        }

        reader.close();
        
    }

    public int[] getLocation(){
        return this.location;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public String getDate() {
        return this.date;
    }

    public Integer[] getElevation() {
        Integer[] arr = new Integer[this.elevation.size()];
        return this.elevation.toArray(arr);
    }

    public Integer[] getAzimut() {
        Integer[] arr = new Integer[this.azimut.size()];
        return this.azimut.toArray(arr);
    }
}