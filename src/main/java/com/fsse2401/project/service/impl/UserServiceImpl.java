package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.data.user.entity.UserEntity;
import com.fsse2401.project.repository.UserRepository;
import com.fsse2401.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserEntityByFirebaseUserData(FireBaseUserData data){
        /*Optional<UserEntity> userEntityOptional = userRepository.findByFirebaseUid(data.getFirebaseUid());
        if (userEntityOptional.isEmpty()){
            UserEntity userEntity = new UserEntity(data);
            return userRepository.save(userEntity);
        }else{
            return userEntityOptional.get();
        }*/
        return userRepository.findByFirebaseUid(data.getFirebaseUid()).orElseGet(() -> userRepository.save(new UserEntity(data)));
    }
}
