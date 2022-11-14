package br.com.canaaapi.utils;



import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class SegurancaUtils {

	private static final String ALGORITHM = "AES"; // https://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#Cipher
	private static final Key KEY = new SecretKeySpec(Base64.getEncoder().encodeToString("fluxogama@86".getBytes()).getBytes(), ALGORITHM);

	private SegurancaUtils() {
	}

	public static String encrypt(String data) throws Exception {
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, KEY);
		byte[] encVal = c.doFinal(data.getBytes());
		return Base64.getUrlEncoder().encodeToString(encVal);
	}

	public static String decrypt(String encryptedData) throws Exception {
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, KEY);
		byte[] decordedValue = Base64.getUrlDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);
	}
}