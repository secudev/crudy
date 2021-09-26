package net.secudev.crudy;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
public class CrudyApplication {

	// Ici car besoin avant security config
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Sinon pas de trace http dans SBA
	@Bean
	public HttpTraceRepository htttpTraceRepository()
	{
	  return new InMemoryHttpTraceRepository();
	}
	
	 @Bean
	    public CacheManager cacheManager() {
	        SimpleCacheManager cacheManager = new SimpleCacheManager();
	        cacheManager.setCaches(Arrays.asList(
	          new ConcurrentMapCache("produits"), 
	          new ConcurrentMapCache("activites")
	          ));
	        return cacheManager;
	    }

	public static void main(String[] args) {
		SpringApplication.run(CrudyApplication.class, args);
	}

}
