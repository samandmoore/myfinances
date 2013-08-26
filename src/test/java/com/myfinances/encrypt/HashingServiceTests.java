package com.myfinances.encrypt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 8/25/13 10:19 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class HashingServiceTests {

    private final IHashingService hashingService = new HashingService();

    @Test
    public void hashIt_actually_changes_the_input() {
        final String input = "sam is the best";

        final String output = hashingService.hashIt(input);

        Assert.assertNotEquals(input, output);
    }

    @Test
    public void areEqual_works() {
        final String input = "sam is the best";

        final String existingHashOfInput = hashingService.hashIt(input);

        Assert.assertTrue(hashingService.areEqual(input, existingHashOfInput));
    }
}
