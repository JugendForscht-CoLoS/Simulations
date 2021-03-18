import java.io.*;

public class Reader{

    private int[] location = new int[2];
    private String startTime = "";
    private String date = "";
    private int[] azimut, elevation;

    private File file = new File("../../Workspace/data0.sundata");
    private BufferedReader reader = new BufferedReader(new FileReader(file));

    public Reader() throws FileNotFoundException {
    }

    public void read() throws IOException {
        int count = 0;
        String line = reader.readLine();

        while(line != null){
            if (count > 2){

            }
            else if(count == 0) {
            }
            else if(count == 1) this.date = line;
            else this.startTime = line;

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
        return this.elevation;
    }

    public int[] getAzimut() {
        return this.azimut;
    }
}