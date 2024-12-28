package com.example.ecommerceapp.config;

/**
 * Constants for JWT configuration.
 * Ensure that the secret key is securely managed.
 */
public class JwtConstant {

    // Secret key used for signing and verifying JWTs
    // Replace with a secure, base64-encoded, 256-bit or higher secret key
    public static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY") != null
            ? System.getenv("JWT_SECRET_KEY")
            : "skdfjvnweoiugwoffjvjwvfwiofakjdvjfvjvifvvjfvjvh";

    // Header field for passing the JWT
    public static final String JWT_HEADER = "Authorization";

}
