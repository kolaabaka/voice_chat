package com.global.server.message;

import com.global.entity.connection.UserSocketEntity;
import com.global.repository.UserRpository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static com.global.server.Server.*;

@Component
public class MessageHandler extends Thread{

    @Autowired
    @Qualifier("userRpository")
    private UserRpository userRpository;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            Set<String> users = userRpository.getUserList();
            for (String userId : users) {
                UserSocketEntity userSocket = userRpository.getSocket(userId);
                if (userSocket.getIn().available() > 0) {
                    byte[] messageArray = new byte[TOTAL_MESSAGE_BYTE_COUNT];
                    int bytesRead = userSocket.getIn().read(messageArray);

                    System.out.println(parseUserId(messageArray, bytesRead));
                    System.out.println(parseMessage(messageArray, bytesRead));
                }
            }
        }
    }

    private int parseUserId(byte[] messageByteArray, int countBytes){
        byte[] userId = new byte[ID_BYTE_COUNT];
        System.arraycopy(messageByteArray, 0, userId, 0, ID_BYTE_COUNT);
        ByteBuffer buffer = ByteBuffer.wrap(userId);
        return buffer.getInt();
    }

    private String parseMessage(byte[] messageByteArray, int countBytes){
        byte[] messageByte = new byte[countBytes];
        System.arraycopy(messageByteArray, ID_BYTE_COUNT, messageByte, 0, countBytes - ID_BYTE_COUNT);
        return new String(messageByte, StandardCharsets.UTF_8);
    }
}
