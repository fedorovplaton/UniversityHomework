import diseases.Huntington;
import fileService.FileReader;
import fileService.FileSaver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        FileReader fileFileReader =new FileReader("C:\\Files\\Genomes");

        Huntington huntington=new Huntington(fileFileReader);

        huntington.find();

        FileSaver saver=new FileSaver("C:\\Files\\Genomes","Statistics1",huntington.getStatistics());
        saver.save();

        /*Huntington h=new Huntington("C:\\Files","C:\\Projects\\HomeWorks\\GenDiSearch\\chromedriver.exe");

        h.find();

        FileSaver s=new FileSaver("C:\\Files","Stat",h.getStatistics());
        s.save();*/
    }
}
