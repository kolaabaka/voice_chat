package com.global.entity.connection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;

@AllArgsConstructor
@Data
public class UserSocketEntity {
    private InputStream in;
    private OutputStream out;
}
