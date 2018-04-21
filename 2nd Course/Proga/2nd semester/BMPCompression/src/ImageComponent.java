import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends JComponent {
    private BufferedImage image;
    public ImageComponent(BufferedImage image){
        this.image=image;
    }
    public void paintComponent(Graphics g){
        g.drawImage(image,0,0,null);
    }
}
