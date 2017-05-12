package com.landvibe.utils;

import com.landvibe.util.RSAUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by user on 2017-05-08.
 */
public class RSAUtilsTest {

    private RSAUtils rsaUtils;

    @Before
    public void setUp() throws Exception {
        rsaUtils = RSAUtils.getInstance();
    }

    @Test
    public void cryptByKeyGen(){
        String msg = "12121442";

        rsaUtils.init();
        String encMsg =  rsaUtils.encryptByKeyGen(msg);
        System.out.println("encMsg : " + encMsg);
        String decMsg = rsaUtils.decryptByKeyGen(encMsg);
        System.out.println("decMsg : " + decMsg);
        Assert.assertThat(msg, CoreMatchers.equalTo(decMsg));
    }

    @Ignore
    @Test
    public void cryptByME(){
        String msg = "12121442";
        String m = "9fd37435f7f731b4b870918e2ad60486af47a522ad937866ae0679397de2592ee3561281eac639a77a21df67ec33e3599f6f69c57753c950209cb0103dc7ef77983fb748a0fd34002348523c1b464e12286625c0be542fa9172cb97fd5817c9b845f279d8ca29723b5ce3462fe88e87c033d2fe47b5420c16884309dc1adb27b";
        String e = "10001";
        rsaUtils.init(m,e);
        String encMsg = rsaUtils.encrypt(msg);
        System.out.println("encMsg : " +encMsg);

        String decMsg = rsaUtils.decrypt(encMsg);
        System.out.println("decMsg : " +decMsg);

        Assert.assertThat(msg, CoreMatchers.equalTo(decMsg));
    }

}
