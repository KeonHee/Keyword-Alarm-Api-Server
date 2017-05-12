package com.landvibe.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
/**
 * Created by user on 2017-05-06.
 */
public class RSAUtils {

    public static RSAUtils instance;

    public static KeyFactory keyFactory;

    public static PublicKey publicKey;

    public static PrivateKey privateKey;

    public static RSAPublicKeySpec publicKeySpec;
    public static RSAPrivateKeySpec privateKeySpec;

    public static Cipher cipher;

    private RSAUtils() throws NoSuchAlgorithmException {
    }

    public static RSAUtils getInstance(){
        if(instance==null){
            try {
                instance=new RSAUtils();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void init(){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            KeyPair keyPair = keyPairGenerator.genKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
            cipher = Cipher.getInstance("RSA");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }

    public void init(String modulus, String exponent){

        BigInteger RSAModulus = new BigInteger(modulus,16);
        BigInteger RSAExponent = BigInteger.valueOf(10001);

        publicKeySpec = new RSAPublicKeySpec(RSAModulus, RSAExponent);
        privateKeySpec = new RSAPrivateKeySpec(RSAModulus,RSAExponent.modInverse(RSAModulus));


        try {
            keyFactory=KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicKeySpec);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
            cipher = Cipher.getInstance("RSA");

        } catch (NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String msg) throws NullPointerException{

        byte[] enCryptoByte=null;
        String enCryptoMsg=null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            enCryptoByte = cipher.doFinal(msg.getBytes());
            System.out.println("byte :"+enCryptoByte);
            enCryptoMsg = Base64.encode(enCryptoByte);

        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return enCryptoMsg;
    }

    public String decrypt(String encMsg) throws NullPointerException{
        byte[] deCryptoByte;
        String deCryptoMsg=null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encryptedBytes = Base64.decode(encMsg);
            deCryptoByte = cipher.doFinal(encryptedBytes);
            deCryptoMsg = Base64.encode(deCryptoByte);

        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return deCryptoMsg;
    }

    public String encryptByKeyGen(String msg) throws NullPointerException {
        String result = null;

        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encByte = cipher.doFinal(msg.getBytes());

            result = Base64.encode(encByte);
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException |
                NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String decryptByKeyGen(String encMsg) throws NullPointerException {
        String result = null;
        try {
            byte[] encByte = Base64.decode(encMsg);

            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] decByte = cipher.doFinal(encByte);
            result=new String(decByte);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }


}
