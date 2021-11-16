package com.project.pma.Repository;

import com.project.pma.Entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {

}
