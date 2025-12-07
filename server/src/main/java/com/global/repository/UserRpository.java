package com.global.repository;

import com.global.entity.connection.UserSocketEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@NoArgsConstructor
public class UserRpository {

    //todo: user BufferedReader
    private final Map<String, UserSocketEntity> userMap = new ConcurrentHashMap<>();

    public void adduser(String id, InputStream in, OutputStream out){
        userMap.put(id, new UserSocketEntity(in, out));
    }

    public Set<String> getUserList(){
        return userMap.keySet();
    }

    public UserSocketEntity getSocket(String userId){
        return userMap.get(userId);
    }
}
