package net.luvina.insurance.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * ConvertPassWord ToMD5
 * @author nguyenthinhphu
 *
 */
public class ConvertPasswordToMD5 {

	/**
	 * encryptMD5
	 * @param pass
	 * @return String pass MD5
	 */
	public static String encryptMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
}
