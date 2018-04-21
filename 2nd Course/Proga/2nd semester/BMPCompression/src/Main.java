import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        MyImage Lenna = new MyImage(System.getProperty("user.dir")+"\\src\\"+"lenna.bmp");
        Lenna.print();
        Lenna.compress();
        Lenna.print();
        Lenna.save("littleLenna","BMP",System.getProperty("user.dir")+"\\src");

    }

}
