package com.myfinances.users;

import com.myfinances.persistence.HibernateRepo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sam Moore
 * @since 8/7/13 11:11 PM
 */
@Repository
public class UserRepo extends HibernateRepo<User, Long> {

    public User findByUsernameOrEmail(String usernameOrEmail) {
        Query query = this.currentSession()
                          .createQuery("from User u where u.username = :username or u.emailAddress = :email");
        query.setParameter("username", usernameOrEmail);
        query.setParameter("email", usernameOrEmail);

        @SuppressWarnings("unchecked")
        User result = (User)query.uniqueResult();

        return result;
    }

}
