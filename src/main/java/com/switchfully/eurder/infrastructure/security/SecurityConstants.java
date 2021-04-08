package com.switchfully.eurder.infrastructure.security;

public final class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/authenticate";
    public static final String JWT_SECRET = "eShVkYp3s6v9y$B&E)H@McQfTjWnZq4t7w!z%C*F-JaNdRgUkXp2s5u8x/A?D(G+";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of SecurityConstants (it's a 'static utility' class");
    }
}
