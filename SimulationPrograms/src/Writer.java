import java.io.*;

public class Writer {

    private File file;
    private BufferedWriter writer;

    public Writer(String path, Reader reader) throws IOException {
        this.file = new File(path);
        this.writer = new BufferedWriter(new FileWriter(this.file));
        double[] location = reader.getLocation();
        writer.write(location[0]+ ";"+ location[1]);
        writer.newLine();
        writer.flush();

        String date = reader.getDateString();
        writer.write(date);
        writer.newLine();
        writer.flush();

        String time = reader.getTimeString();
        writer.write(time);
        writer.newLine();
        writer.flush();
    }

    public void write(double holdingTime, double[][] deviation) throws IOException {
        for(int t = 0; t < deviation.length; t++){
            writer.write(holdingTime+";"+t*5+";"+deviation[0]+";"+deviation[1]);
            writer.newLine();
            writer.flush();
        }
    }
}
