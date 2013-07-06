package com.myfinances.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface IEncryptionService {
    String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException;

    String decrypt(String property) throws GeneralSecurityException, IOException;
}
