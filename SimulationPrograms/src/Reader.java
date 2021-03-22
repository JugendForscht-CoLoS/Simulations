import java.io.*;
import java.util.ArrayList;

public class Reader{

    private double[] location = new double[2];
    private int startTime = 0; //in Sekunden
    private int date = 0; //in Sekunden
    private double[] azimut, elevation;
    public int length;

    private int[] monate1 = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    private int[] monate2 = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};

    private File file;
    private BufferedReader reader = new BufferedReader(new FileReader(file));

    public Reader(String path) throws FileNotFoundException {
        
        this.file = new File(path);
        length = lengthOfFile() - 3;
    }


    public int lengthOfFile() throws IOException {
        FileReader fr = new FileReader(this.file);
        int count = 0;
        int character;

        while((character = fr.read()) != -1){
            if(character == 10){
                count++;
            }
        }
        return count;
    }

    public void read() throws IOException {

        int length = this.lengthOfFile();
        this.elevation = new double[length-3];
        this.azimut = new double[length-3];

        int count = 0;
        String line = this.reader.readLine();

        //alle Zeilen der Datei auslesen und den Variablen zuordnen
        //1. Standort
        //2. Datum
        //3. Uhrzeit
        //4. bis n. Sonnenposition
        while(line != null){
            if (count > 2){
                String[] sunPosition = line.split(";",2);
                this.azimut[count-3] = Double.parseDouble(sunPosition[0]);
                this.elevation[count-3] = Double.parseDouble(sunPosition[1]);
            }
            else if(count == 0) {
                String[] loc = line.split(",",2);
                this.location[0] = Double.parseDouble(loc[0]);
                this.location[1] = Double.parseDouble(loc[1]);
            }
            else if(count == 1){
                String[] date = line.split("/", 3);
                this.date = (Integer.parseInt(date[0]) - 1) * 86400;
                if ((Integer.parseInt(date[2]) - 2004) % 4 == 0) {
                    this.date += 86400 * monate2[Integer.parseInt(date[1]) - 1];
                }
                else {
                    this.date += 86400 * monate1[Integer.parseInt(date[1]) - 1];
                }
            }
            else {
                String[] time = line.split(":",3);
                this.startTime = 3600 * Integer.parseInt(time[0]) + 60 * Integer.parseInt(time[1]);
                this.date += this.startTime;
            }

            line = this.reader.readLine();
            count++;
        }

        this.reader.close();
        
    }

    public double[] getLocation(){
        return this.location;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public int getDate() {
        return this.date;
    }

    public double[] getElevation() {
        return this.elevation;
    }

    public double[] getAzimut() {
        return this.azimut;
    }
}
