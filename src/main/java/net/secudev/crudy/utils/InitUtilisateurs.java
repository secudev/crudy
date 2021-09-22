package net.secudev.crudy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitUtilisateurs implements ApplicationRunner{
	
	@Autowired
	private Populator populator;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	
		populator.initUsers();
	}
}
