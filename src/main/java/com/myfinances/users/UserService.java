package com.myfinances.users;

import com.myfinances.encrypt.HashingService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService implements IUserService {
    private final HashingService hashingService;
    private final UserRepo userRepo;

    @Autowired
    public UserService(final HashingService hashingService, final UserRepo userRepo) {
        this.hashingService = hashingService;
        this.userRepo = userRepo;
    }

    @Override
    public User findById(Long id) {
        return userRepo.find(id);
    }

    @Override
    public User findByUsernameAndPassword(final String username, final String password) {
        User user = userRepo.findByUsernameOrEmail(username);

        if (user == null) {
            return null;
        }

        if (this.hashingService.areEqual(password, user.getHashedPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public User create(String username, String email, String password) {
        User user = userRepo.findByUsernameOrEmail(username);
        user = user == null ? new User() : user;

        user.setEmailAddress(email);
        user.setUsername(username);
        user.setCreatedAt(new DateTime(DateTimeZone.UTC));
        user.setHashedPassword(hashingService.hashIt(password));

        userRepo.update(user);

        return user;
    }

    @Override
    public void recordLogin(User user) {
        user.setLastLoggedInAt(new DateTime(DateTimeZone.UTC));

        userRepo.update(user);
    }
}
