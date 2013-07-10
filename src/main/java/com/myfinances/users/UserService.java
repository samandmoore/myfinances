package com.myfinances.users;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.myfinances.encrypt.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserService implements IUserService {
    private final Map<Long, User> users = new HashMap<>();

    private final HashingService hashingService;

    @Autowired
    public UserService(final HashingService hashingService) {
        this.hashingService = hashingService;

        User sam = new User(1L, "sam", this.hashingService.hashIt("asdfasdf"));
        users.put(sam.getId(), sam);
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
                        && hashingService.areEqual(password, u.getHashedPassword());
            }
        });

        return items.isEmpty() ? null : items.iterator().next().getValue();
    }
}
