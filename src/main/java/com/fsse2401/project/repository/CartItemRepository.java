package com.fsse2401.project.repository;

import com.fsse2401.project.data.cart.entity.CartItemEntity;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    Optional<CartItemEntity> findByProductAndUser(ProductEntity product, UserEntity user);
    @Query(nativeQuery = true, value = "SELECT `cid`, `quantity`, `pid`, `uid` FROM `cart_item_entity` WHERE `pid` = ?1 AND `uid` = ?2")
    Optional<CartItemEntity> findByPidAndUid(Integer pid, Integer uid);


    @Query(nativeQuery = true, value = "SELECT `cid`, `quantity`, `pid`, `uid` FROM `cart_item_entity` WHERE `uid` = ?1")
    Iterable<CartItemEntity> findAllByUid(Integer uid);

    //@Query(nativeQuery = true, value = "DELETE * FROM `cart_item_entity` WHERE `uid` = ?1")
    void deleteAllByUser(UserEntity user);

    Integer countByUser(UserEntity user);
}
