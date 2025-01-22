package com.fsse2401.project.repository;

import com.fsse2401.project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT `uid`, `email`, `firebase_uid` FROM `user` WHERE `firebase_uid` = ?1")
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
