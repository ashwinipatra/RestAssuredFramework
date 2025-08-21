package com.api.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@Config.LoadPolicy(LoadType.MERGE)
@Sources({
	"system:properties",
	"file:src/test/resources/config/config.properties",
	"file:src/test/resources/config/config_qa.properties",
	"file:src/test/resources/config/config_dev.properties",
	})
public interface IConfig extends Config {

	String env();
	
	@Key("${env}.base.url")
	String baseUrl();
	
	
}
