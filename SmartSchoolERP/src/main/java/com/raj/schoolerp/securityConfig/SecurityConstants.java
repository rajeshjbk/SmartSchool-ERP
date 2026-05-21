package com.raj.schoolerp.securityConfig;

public class SecurityConstants {

	public static final String JWT_KEY = System.getenv("JWT_SECRET") != null ? System.getenv("JWT_SECRET")
			: "secretRAJESHITJRTPSCHOOLERPJwtImplementation";

	public static final String JWT_HEADER = "Authorization";
}