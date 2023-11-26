package login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * The PasswordManager class provides functionalities related to password encryption and verification.
 * It uses SHA-256 hashing algorithm to secure passwords.
 */
public class PasswordManager {
    /**
     * Encrypts a plaintext password using SHA-256 hashing algorithm.
     *
     * @param password The plaintext password to be encrypted.
     * @return A string representing the hexadecimal format of the hashed password.
     * @throws RuntimeException If the SHA-256 hashing algorithm is not available.
     */
    public static String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Compares an encrypted password with a plaintext password after hashing it.
     * This method is typically used to verify user login attempts.
     *
     * @param encryptedPassword The already encrypted password to compare against.
     * @param password The plaintext password to check.
     * @return true if the plaintext password matches the encrypted password, false otherwise.
     */
    public static boolean checkPassword(String encryptedPassword, String password) {
        String hashedPassword =  encrypt(password);
        return hashedPassword.equals(encryptedPassword);
    }

    /**
     * Converts a byte array from the hashed password into a hexadecimal string.
     *
     * @param hash The byte array output from the hashing algorithm.
     * @return A string representing the hexadecimal values of the hash.
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
