package com.myfinances.accounts;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sam Moore
 * @since 8/7/13 9:44 PM
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class AccountRepo extends HibernateRepo<Account, Long> {
}
