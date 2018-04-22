import java.awt.image.BufferedImage;
import java.math.BigInteger;

public class PublicCryptographer {
    private PublicKey publicKey;
    private BigInteger integer;
    private String string;
    private BufferedImage image;

    public PublicCryptographer(BigInteger integer, PublicKey publicKey) {
        this.integer = integer;
        this.publicKey = publicKey;
    }

    public PublicCryptographer(String string, PublicKey publicKey) {
        this.string = string;
        this.publicKey = publicKey;
    }

    public PublicCryptographer(BufferedImage image, PublicKey publicKey) {
        this.image = image;
        this.publicKey = publicKey;
    }

    public BigInteger getInteger() {
        return integer;
    }

    public String getString() {
        return string;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setInteger(BigInteger integer) {
        this.integer = integer;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void encryptInt() {
        if ((integer != null)&&(publicKey!=null)) {
            integer = integer.modPow(publicKey.e, publicKey.n);
        } else {
            System.out.println("No integer value or publicKey");
        }
    }

    public BigInteger[] encryptString(){
        if ((string!=null)&&(publicKey!=null)){
            BigInteger[] charArray=new BigInteger[string.length()];
            for (int i=0;i<string.length();i++){
                BigInteger c=BigInteger.valueOf(string.charAt(i));
                charArray[i]=c.modPow(publicKey.e, publicKey.n);
            }
            return charArray;
        } else {
            System.out.println("No string or publicKey");
            return null;
        }
    }

    public BigInteger[][] encryptImage(){
        if ((image!=null)&&(publicKey!=null)){
            BigInteger[][] pixelMatrix=new BigInteger[image.getWidth()][image.getHeight()];
            for (int i=0;i<image.getWidth();i++){
                for (int j=0;j<image.getHeight();j++){
                    BigInteger pixel=BigInteger.valueOf(image.getRGB(i,j));
                    pixelMatrix[i][j]=pixel.modPow(publicKey.e, publicKey.n);
                }
            }
            return pixelMatrix;
        }else{
            System.out.println("No image or publicKey");
            return null;
        }
    }
}
