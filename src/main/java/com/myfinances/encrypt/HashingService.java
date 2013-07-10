package com.myfinances.encrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/9/13 10:19 PM
 */
@Component
public class HashingService implements IHashingService {
    @Override
    public String hashIt(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt());
    }

    @Override
    public boolean areEqual(String candidate, String hash) {
        return BCrypt.checkpw(candidate, hash);
    }
}
