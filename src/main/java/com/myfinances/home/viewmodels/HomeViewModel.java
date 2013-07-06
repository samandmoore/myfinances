package com.myfinances.home.viewmodels;

import com.myfinances.users.User;

public class HomeViewModel {
    public HomeViewModel() {
        userDetails = new UserDetailsViewModel();
    }

    public HomeViewModel(User user) {
        userDetails = new UserDetailsViewModel(user);
    }

    private UserDetailsViewModel userDetails;

    public UserDetailsViewModel getUserDetails() {
        return userDetails;
    }

    public static class UserDetailsViewModel {

        private UserDetailsViewModel() {
        }

        private UserDetailsViewModel(User user) {
            super();
            this.userName = user.getUsername();
        }

        private String userName;

        public String getUserName() {
            return userName;
        }
    }
}
