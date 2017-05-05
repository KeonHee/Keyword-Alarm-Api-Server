package com.landvibe.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by user on 2017-05-06.
 */
public class RSAUtils {

    public static RSAUtils instance;

    public static KeyFactory keyFactory;

    public static PublicKey publicKey;

    public static RSAPublicKeySpec publicKeySpec;

    public Cipher cipher;

    private RSAUtils() throws NoSuchAlgorithmException {
    }

    public RSAUtils getInstance(){
        if(instance==null){
            try {
                instance=new RSAUtils();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void init(String modulus, String exponent){

        BigInteger RSAModulus = new BigInteger(modulus,16);
        BigInteger RSAExponent = new BigInteger(exponent,16);

        publicKeySpec = new RSAPublicKeySpec(RSAModulus, RSAExponent);

        try {
            keyFactory=KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicKeySpec);

            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (NoSuchAlgorithmException e ) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String msg) throws NullPointerException{
        byte[] enCryptoByte;
        String enCryptoMsg=null;
        try {
            enCryptoByte = cipher.doFinal(msg.getBytes());
            enCryptoMsg = new String(enCryptoByte,"utf-8");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return enCryptoMsg;
    }


}
