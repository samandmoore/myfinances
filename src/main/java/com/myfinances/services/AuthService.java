package com.myfinances.services;

import com.myfinances.models.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AuthService implements UserDetailsService {

    static Map<String, User> users = new HashMap<String, User>();

    static {
        users.put("test1", new User("sam", "adsfasdf"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFromDB = findUserFromDatabase(username);

        return new org.springframework.security.core.userdetails.User(userFromDB.getUsername(), userFromDB.getHashedPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
    }

    private User findUserFromDatabase(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }

        return null;
    }
}