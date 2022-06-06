package com.xi.gamis.infrastructure.security;

import jdk.nashorn.internal.objects.NativeUint8Array;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.security.provider.MD5;

public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
