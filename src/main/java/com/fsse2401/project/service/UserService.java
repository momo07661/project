package com.fsse2401.project.service;

import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByFirebaseUserData(FireBaseUserData data);
}
