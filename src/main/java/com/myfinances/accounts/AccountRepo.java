package com.myfinances.accounts;

import com.myfinances.persistence.HibernateRepo;
import org.springframework.stereotype.Repository;

/**
 * @author Sam Moore
 * @since 8/7/13 9:44 PM
 */
@Repository
public class AccountRepo extends HibernateRepo<Account, Long> {
}
