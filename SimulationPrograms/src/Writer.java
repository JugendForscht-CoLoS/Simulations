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

        String date = reader.getDateString();
        writer.write(date);
        writer.newLine();

        String time = reader.getTimeString();
        writer.write(time);
        writer.newLine();
    }
}
