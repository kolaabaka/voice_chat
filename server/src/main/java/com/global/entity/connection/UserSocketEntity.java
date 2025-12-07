package com.global.entity.connection;

import lombok.AllArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;

@AllArgsConstructor
public class UserSocketEntity {
    private InputStream in;
    private OutputStream out;
}
