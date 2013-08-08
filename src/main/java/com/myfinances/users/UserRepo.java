package com.myfinances.users;

import com.myfinances.common.data.HibernateRepo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sam Moore
 * @since 8/7/13 11:11 PM
 */
@Repository
public class UserRepo extends HibernateRepo<User, Long> {

    public User findByUsername(String username) {
        Query query = this.currentSession().createQuery("from User u where u.username = :username");
        query.setParameter("username", username);

        @SuppressWarnings("unchecked")
        User result = (User)query.uniqueResult();

        return result;
    }

}
