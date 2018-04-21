import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImage {
    private BufferedImage image;
    private int width;
    private int height;

    //Constructs MyImage object using filePath
    public MyImage(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
    }

    //Constructs MyImage object using existing BufferedImage
    public MyImage(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    //Prints MyImage object
    public void print() {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);

        ImageComponent component = new ImageComponent(image);

        frame.add(component);
    }

    //Compresses existing image and returns smaller one
    public MyImage compress(MyImage lenna) {
        int w = lenna.width / 2;
        int h = lenna.height / 2;

        int[][] pixelsArray = new int[w][h];
        for (int i = 0; i < lenna.width; i += 2) {
            for (int j = 0; j < lenna.height; j += 2) {
                pixelsArray[i / 2][j / 2] = lenna.image.getRGB(i, j);
            }
        }

        MyImage littleLenna = new MyImage(new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB));

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                littleLenna.image.setRGB(i, j, pixelsArray[i][j]);
            }
        }

        return littleLenna;
    }

    //Compresses current image
    public void compress() {
        int w = width / 2;
        int h = height / 2;

        int[][] pixelsArray = new int[w][h];
        for (int i = 0; i < width; i += 2) {
            for (int j = 0; j < height; j += 2) {
                pixelsArray[i / 2][j / 2] = image.getRGB(i, j);
            }
        }

        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                image.setRGB(i, j, pixelsArray[i][j]);
            }
        }
    }

    //Saves image in directory filePath with name imageName in format format(JPEG,BMP,GIF,PNG)
    public void save(String imageName,String format,String filePath) {
        File f = new File(filePath+File.separator + imageName+"."+format.toLowerCase());
        try {
            ImageIO.write(image, format.toUpperCase(), f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
