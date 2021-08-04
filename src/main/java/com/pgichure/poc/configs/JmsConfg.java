/**
 * 
 */
package com.pgichure.poc.configs;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author Paul
 *
 */
@Configuration
public class JmsConfg {
	
	@Value("${spring.jms.url}")
    private String jmsUrl;
	
    @Value("${spring.jms.username}")
    private String jmsUser;
    
    @Value("${spring.jms.password}")
    private String jmsPassword;
    
    @Value("${spring.jms.connectionFactoryName}")
    private String jmsConnFactoryName;
	
	@Bean
	public MessageConverter jmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("__type");
		return converter;
	}
	
	@Bean
	public JmsListenerContainerFactory<?> jmsMessageFactory(ConnectionFactory connectionFactory,
	                                                DefaultJmsListenerContainerFactoryConfigurer configurer){
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setSessionTransacted(true);
	    factory.setConcurrency("3-10");
	    configurer.configure(factory, connectionFactory);
	    return factory;
	}
	
    private Properties getJNDIProperties(){
        final Properties jndiProps = new Properties();
        jndiProps.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        jndiProps.setProperty(Context.PROVIDER_URL, jmsUrl);
        if (jmsUser != null && !jmsUser.isEmpty()) {
            jndiProps.setProperty(Context.SECURITY_PRINCIPAL, jmsUser);
        }
        if (jmsPassword != null && !jmsPassword.isEmpty()) {
            jndiProps.setProperty(Context.SECURITY_CREDENTIALS, jmsPassword);
        }
        return jndiProps;
    }

}
