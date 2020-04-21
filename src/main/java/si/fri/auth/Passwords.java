package si.fri.auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Passwords {

    private static final int KEY_LENGTH = 256;
    private static final int ITERATIONS = 65536;

    public static List<byte []> hashPassword(String password) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[200];
            secureRandom.nextBytes(salt);
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS,KEY_LENGTH);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            Base64.Encoder encoder = Base64.getEncoder();
            return Arrays.asList(salt,hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte [] hashPassword(String password, byte [] salt){
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS,KEY_LENGTH);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return secretKeyFactory.generateSecret(keySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
