import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader{

    private int[] location = new int[2];
    private String startTime = "";
    private int[] azimut, elevation;

    private File file = new File("../../Workspace/data0.sundata");
    private FileReader reader = new FileReader(file);

    public Reader() throws FileNotFoundException {
    }

    public void read(){

    }
}