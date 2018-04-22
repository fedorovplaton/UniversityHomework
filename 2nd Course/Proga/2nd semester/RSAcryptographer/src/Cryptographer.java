import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.Random;

public class Cryptographer {
    private BigInteger integer;
    private String string;
    private BufferedImage image;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Cryptographer() {
    }

    public Cryptographer(Integer integer) {
        this.integer = BigInteger.valueOf(integer);
    }

    public Cryptographer(String string) {
        this.string = string;
    }

    public Cryptographer(BufferedImage image) {
        this.image = image;
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

    public PublicKey getPublicKey() {
        return publicKey;
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

    public TripleBig extGCD(BigInteger a, BigInteger b) {
        TripleBig temp = new TripleBig(a, BigInteger.ONE, BigInteger.ZERO);
        TripleBig temp2 = new TripleBig();

        if (b == BigInteger.ZERO) {
            return temp;
        } else {

            temp2 = extGCD(b, a.mod(b));
            temp = new TripleBig();

            temp.d = temp2.d;
            temp.x = temp2.y;
            temp.y = temp2.x.subtract(a.divide(b).multiply(temp2.y));

            return temp;
        }
    }

    public void generateKeys() {
        BigInteger p = new BigInteger(1024, 1000, new Random());
        BigInteger q = new BigInteger(1024, 1000, new Random());

        BigInteger n = p.multiply(q);
        BigInteger fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger(512, 1000, new Random());

        BigInteger d = extGCD(e, fi).x;

        if (fi.subtract(d).signum() == -1) {
            d = d.mod(fi);
        } else if (d.signum() == -1) {
            d = (d.mod(fi).add(fi)).mod(fi);
        }

        privateKey = new PrivateKey(d, n);
        publicKey = new PublicKey(e, n);
    }

    public void encryptInt() {
        if (integer != null) {
            integer = integer.modPow(publicKey.e, publicKey.n);
        } else {
            System.out.println("No integer value");
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

    public void decryptInt() {
        if ((integer != null)&&(privateKey!=null)) {
            integer = integer.modPow(privateKey.getD(), privateKey.getN());
        } else {
            System.out.println("No integer value or privateKey");
        }
    }

    public void decryptString(BigInteger[] charArray){
        if (privateKey==null){
            System.out.println("No privateKey");
            return;
        } else if (string!=null){
            System.out.println("String will be deleted");
            string="";
        }
        char[] c=new char[charArray.length];
        for (int i=0;i<charArray.length;i++){
            c[i]=(char)charArray[i].modPow(privateKey.getD(), privateKey.getN()).intValue();
        }
        string= String.valueOf(c);
    }

    public void decryptImage(BigInteger[][] pixelMatrix){
        if (privateKey==null){
            System.out.println("No privateKey");
            return;
        } else if (image!=null){
            System.out.println("Image will be deleted");
            image=null;
        }

        image=new BufferedImage(pixelMatrix[0].length,pixelMatrix.length,BufferedImage.TYPE_INT_RGB);
        for (int i=0;i<image.getWidth();i++){
            for (int j=0;j<image.getHeight();j++){
                image.setRGB(i,j,pixelMatrix[i][j].modPow(privateKey.getD(), privateKey.getN()).intValue());
            }
        }
    }
}
