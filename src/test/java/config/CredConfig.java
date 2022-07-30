package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:demowebshop/credential.properties")
public interface CredConfig extends Config {
    String password();
}


