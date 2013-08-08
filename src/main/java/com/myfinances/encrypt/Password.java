package com.myfinances.encrypt;

/**
 * @author Sam Moore
 * @since 8/8/13 12:03 AM
 */
public class Password {
    private String result;
    private String salt;

    public Password(String result, String salt) {
        this.result = result;
        this.salt = salt;
    }

    public String getResult() {
        return result;
    }

    public String getSalt() {
        return salt;
    }
}
