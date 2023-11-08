package org.biblioteka.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class PasswordEncoder {

    private static final int SALT_SIZE = 16;

    public boolean matches(String rawPassword, String encodedPassword) {
        byte[] encodedPasswordBytes = Base64.getDecoder().decode(encodedPassword);
        byte[] salt = Arrays.copyOfRange(encodedPasswordBytes, 0, SALT_SIZE);
        return Arrays.equals(encodedPasswordBytes, encode(rawPassword, salt));
    }

    public String encode(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(encode(password, salt));
    }

    private byte[] encode(String rawPassword, byte[] salt) {
        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] encodedPassword = factory.generateSecret(spec).getEncoded();
            byte[] saltAndHash = Arrays.copyOf(salt, salt.length + encodedPassword.length);
            System.arraycopy(encodedPassword, 0, saltAndHash, salt.length, encodedPassword.length);
            return saltAndHash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
