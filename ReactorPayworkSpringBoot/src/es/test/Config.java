package es.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.Environment;
import reactor.bus.EventBus;
import reactor.core.config.DispatcherType;

@Configuration
public class Config {
	
	private static final int REACTOR_THREAD_COUNT = 10;

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        //return EventBus.create(env, Environment.THREAD_POOL);
    	
    	return EventBus.create(
    			  env, 
    			  Environment.newDispatcher(REACTOR_THREAD_COUNT,REACTOR_THREAD_COUNT,DispatcherType.THREAD_POOL_EXECUTOR));
    	
    	
    }
    
}