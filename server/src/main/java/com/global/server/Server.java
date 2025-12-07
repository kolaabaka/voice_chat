package com.global.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.entity.json.InitMessageEntity;
import com.global.repository.UserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
public class Server extends Thread {

    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper mapper;

    @Autowired
    @Qualifier("userRpository")
    private UserRpository userRpository;

    private static final byte[] MESSAGE_OK = new String("OK").getBytes();

    @Override
    public void run() {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(8080)){
                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                byte[] initMessageArray = new byte[256];
                inputStream.read(initMessageArray);

                InitMessageEntity initMessage= parseInitMessageJson(initMessageArray);

                System.out.println(initMessage);
                outputStream.write(MESSAGE_OK);
                outputStream.flush();

                userRpository.adduser(initMessage.getUserId(), inputStream, outputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private InitMessageEntity parseInitMessageJson(byte[] mes){
        String initMessageString = new String(mes, StandardCharsets.UTF_8);

        InitMessageEntity initMessage;

        try {
            initMessage = mapper.readValue(initMessageString, InitMessageEntity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return  initMessage;
    }
}
