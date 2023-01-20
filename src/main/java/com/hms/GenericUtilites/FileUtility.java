package com.hms.GenericUtilites;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	/**
	 * 
	 * @param Key
	 * @return
	 * @throws Throwable 
	 */

	public String getPropertyKeyValue(String Key) throws Throwable {
		FileInputStream fis = new FileInputStream(IpathConstants.Filepath);
		Properties pObj = new Properties();
		pObj.load(fis);
		String value = pObj.getProperty(Key);
		return value;

	}
}
