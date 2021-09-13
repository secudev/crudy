package net.secudev.crudy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("secudev")
@Data
public class AppGlobalProperties {
	private AppMode mode;
	private String motd;
	
	public enum AppMode{reset,demo}
}
