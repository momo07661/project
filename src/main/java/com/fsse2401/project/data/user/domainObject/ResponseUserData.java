package com.fsse2401.project.data.user.domainObject;

import com.fsse2401.project.data.user.entity.UserEntity;
import jakarta.persistence.Column;

public class ResponseUserData {
    private Integer uid;

    private String firebaseUid;
    private String email;

    public ResponseUserData(UserEntity entity) {
        this.uid = entity.getUid();
        this.firebaseUid = entity.getFirebaseUid();
        this.email = entity.getEmail();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
