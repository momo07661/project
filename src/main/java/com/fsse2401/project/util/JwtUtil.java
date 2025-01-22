package com.fsse2401.project.util;

import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FireBaseUserData getFirebaseUserData(JwtAuthenticationToken jwtToken){
        return new FireBaseUserData(jwtToken);
    }
}
