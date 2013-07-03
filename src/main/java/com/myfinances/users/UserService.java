package com.myfinances.users;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserService implements IUserService {
    static Map<Long, User> users = new HashMap<>();

    static {
        users.put(1L, new User("sam", "asdfasdf"));
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }

    @Override
    public User findByUsernameAndPassword(final String username, final String password) {
        Set<Map.Entry<Long, User>> items = Sets.filter(users.entrySet(), new Predicate<Map.Entry<Long, User>>() {
            @Override
            public boolean apply(@Nullable Map.Entry<Long, User> longUserEntry) {
                User u = longUserEntry.getValue();
                return u.getUsername().compareToIgnoreCase(username) == 0
                        && u.getHashedPassword().equals(password);
            }
        });

        return items.isEmpty() ? null : items.iterator().next().getValue();
    }
}
