package com.bidding.app.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to encrypt password
 * @author ARUN P P
 */
public class EncryptData {

	private static final Logger LOGGER = Logger.getLogger(EncryptData.class);

	/**
	 * Function to encrypt password
	 *
	 * @param password the password which is to be encrypted
	 * @return encrpted password
	 * @throws BidException if the specified algorithm does not exists
	 */
	public static String encryptPassword( String password ) throws BidException
	{
		String encryptedPassword;

		try
		{
			String dataToEncrypt = password + password.substring(0, 4);
			MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
			msgDigest.update(dataToEncrypt.getBytes());

			encryptedPassword = new String(Base64.encodeBase64(msgDigest.digest()));
		}
		catch( NoSuchAlgorithmException e )
		{
			LOGGER.error("Encryption of password failed");
			throw new BidException("Encryption of password failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return encryptedPassword;
	}
}
