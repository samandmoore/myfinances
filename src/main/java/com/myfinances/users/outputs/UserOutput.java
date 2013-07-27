package com.myfinances.users.outputs;

import com.myfinances.users.User;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/27/13 4:31 PM
 */
public class UserOutput {
    public Long id;

    public String username;

    public static UserOutput fromUser(User user) {
        UserOutput output = new UserOutput();
        output.id = user.getId();
        output.username = user.getUsername();

        return output;
    }
}
