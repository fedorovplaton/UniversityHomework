import diseases.Huntington;
import fileService.FileSaver;
import fileService.MyReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        MyReader fileMyReader =new MyReader("C:\\Files\\Genomes");

        Huntington huntington=new Huntington(fileMyReader);

        huntington.find();

        FileSaver saver=new FileSaver("C:\\Files\\Genomes","Statistics",huntington.getStatistics());
        saver.save();
    }
}
