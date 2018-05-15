import diseases.Huntington;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        /*FileReader fileReader=new FileReader("C:\\Files\\sequence (5).fasta");
        System.out.println(fileReader.getSequence().get("Z69711.1"));*/

        Huntington huntington=new Huntington("C:\\Files","C:\\Projects\\HomeWorks\\GenDiSearch\\chromedriver.exe");

        huntington.find();
    }
}
