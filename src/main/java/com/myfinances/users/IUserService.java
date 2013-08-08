package com.myfinances.users;


public interface IUserService {
    User findById(Long id);

    User findByUsernameAndPassword(String username, String password);

    User create(String username, String email, String password);

    void recordLogin(User user);
}
