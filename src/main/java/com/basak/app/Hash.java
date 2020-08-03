package com.basak.app;

public class Hash {
	public String hash(String plain) {
		SecurityUtil securityUtil = new SecurityUtil();
		return securityUtil.encryptSHA256(plain);
	}
}
