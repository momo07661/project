package com.fsse2401.project.repository;

import com.fsse2401.project.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM `product`")
    Iterable<ProductEntity> getAll();

    @Query(nativeQuery = true, value = "SELECT * FROM `product` WHERE `pid` = ?1")
    Optional<ProductEntity> getByPid(Integer pid);

    @Query(nativeQuery = true, value = "SELECT * from product where `name` like %?1% escape '\\\\'")
    Optional<ProductEntity> getAllByName(String name);
}
