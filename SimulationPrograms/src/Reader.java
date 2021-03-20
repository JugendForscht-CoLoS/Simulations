import java.io.*;
import java.util.ArrayList;

public class Reader{

    private double[] location = new double[2];
    private int startTime = 0;                  //in Minuten
    private int date = 0;
    private double[] azimut, elevation;

    private File file = new File("C:/Users/Jonas/IdeaProjects/Simulations/SimulationPrograms/datafiles/data0.sundata");
    private BufferedReader reader = new BufferedReader(new FileReader(file));

    public Reader() throws FileNotFoundException {
    }

    private int lengthOfFile() throws IOException {
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
            else if(count == 1) this.date = line;
            else {
                String[] time = line.split(":",3);
                this.startTime = 60*Integer.parseInt(time[0]) + Integer.parseInt(time[1]);
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

    public String getDate() {
        return this.date;
    }

    public double[] getElevation() {
        return this.elevation;
    }

    public double[] getAzimut() {
        return this.azimut;
    }
}