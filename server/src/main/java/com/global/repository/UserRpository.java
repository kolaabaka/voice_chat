package com.global.repository;

import com.global.entity.connection.UserSocketEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class UserRpository {

    private final Map<String, UserSocketEntity> userMap = new HashMap<>();

    public void adduser(String id, InputStream in, OutputStream out){
        userMap.put(id, new UserSocketEntity(in, out));
    }

    public UserSocketEntity getSocket(String userId){
        return userMap.get(userId);
    }
}
