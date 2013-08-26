package com.myfinances.encrypt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 8/25/13 10:23 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class EncryptionServiceTests {

    private final String secret = "keep it secret, keep it safe";

    private final IEncryptionService encryptionService = new EncryptionService(secret);

    final String input = "sam is the best";
    private String output = null;
    private String inputAgain = null;

    @Before
    public void setup() {
        output = null;
        inputAgain = null;
    }

    @Test
    public void encrypt_and_decrypt_with_same_key_works() {
        try {
            legOne();
            legTwo();
        } catch (Throwable t) {
            Assert.fail("this should not throw!");
        }

        Assert.assertNotEquals(input, output);
        Assert.assertEquals(input, inputAgain);
    }

    @Test(expected = GeneralSecurityException.class)
    public void encrypt_and_decrypt_with_diffeent_key_fails() throws GeneralSecurityException {
        try {
            legOne();
        } catch (Throwable t) {
            Assert.fail("this should not throw!");
        }

        setSecret("not the original secret");

        try {
            legTwo();
        } catch (IOException e) {
            Assert.fail("should not throw IO exception");
        }
    }

    private void setSecret(String secret) {
        ((EncryptionService)encryptionService).setSecret(secret);
    }

    private void legOne() throws GeneralSecurityException, UnsupportedEncodingException {
        output = encryptionService.encrypt(input);
    }

    private void legTwo() throws GeneralSecurityException, IOException {
        inputAgain = encryptionService.decrypt(output);
    }
}
