package com.fsse2401.project.repository;

import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM `transaction_product_entity` WHERE tid = ?1")
    Optional<TransactionProductEntity> findById(Integer tid);
}
