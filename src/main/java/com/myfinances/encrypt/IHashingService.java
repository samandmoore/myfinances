package com.myfinances.encrypt;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/9/13 10:19 PM
 */
public interface IHashingService {
    String hashIt(String input);
    boolean areEqual(String candidate, String hash);
}
