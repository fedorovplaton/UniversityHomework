package main;

import privateCryptographer.Cryptographer;
import publicCryptographer.PublicCryptographer;
import keys.PublicKey;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        PublicKey key;
        Cryptographer crypt = new Cryptographer();
        crypt.generateKeys();
        PublicCryptographer publicCrypt = new PublicCryptographer(BigInteger.valueOf(111111), crypt.getPublicKey());
        publicCrypt.setString("Hello world!");
        publicCrypt.encryptInt();
        BigInteger[] c=publicCrypt.encryptString();
        crypt.decryptString(c);
        System.out.println(crypt.getString());
        System.out.println(publicCrypt.getInteger());
        crypt.setInteger(publicCrypt.getInteger());
        crypt.decryptInt();
        System.out.println(crypt.getInteger());
    }
}
