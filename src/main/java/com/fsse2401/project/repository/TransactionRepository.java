package com.fsse2401.project.repository;


import com.fsse2401.project.data.transaction.entity.TransactionEntity;
import com.fsse2401.project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    //Optional<TransactionEntity> findByTidAndUser(Integer tid, UserEntity user);

    @Query(nativeQuery = true, value = "SELECT `tid`, `date_time`, `status`, `total`, `buyer_uid`, `stripe_session_id` FROM `transaction_entity` WHERE `tid` = ?1 AND `buyer_uid` = ?2")
    Optional<TransactionEntity> findByTidAndUid(Integer tid, Integer uid);

}
