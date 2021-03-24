import java.io.*;

public class Writer {

    private File file;
    private BufferedWriter writer;

    public Writer(String path, Reader reader) throws IOException {
        this.file = new File(path);
        this.writer = new BufferedWriter(new FileWriter(this.file));
        double[] location = reader.getLocation();
        writer.write(location[0]+ ","+ location[1]);
        writer.newLine();

        String date = reader.getDateString();
        writer.write(date);
        writer.newLine();

        String time = reader.getTimeString();
        writer.write(time);
        writer.newLine();
    }

    public void write(double holdingTime, double[][] deviation) throws IOException {
        for(int t = 0; t < deviation.length; t++){
            writer.write(holdingTime+";"+t*5+";"+deviation[t][0]+";"+deviation[t][1]);
            writer.newLine();
        }
        writer.flush();
    }
}
