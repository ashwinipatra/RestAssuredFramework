package com.api.enums;

public enum ConfigProperty {
	
	BASE_URL,
	RETRIES,
	OVERRIDE_REPORT,
	OVERRIDE_RESPONSE, 
	DOCUMENT_TITLE,
	REPORT_NAME ;
	
	
	public String getKeyName() {
		return this.toString().toLowerCase().replace("_", ".");
	}
}
