package com.myfinances.users;


public interface IUserService {
    User findById(Long id);

    User findByUsernameAndPassword(String username, String password);
}
