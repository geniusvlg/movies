package com.fact.nash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.time.Duration;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class NashApplication {

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	OAuth2ClientProperties oAuth2ClientProperties() {
		return new OAuth2ClientProperties();
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(60))
				.disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
						new GenericJackson2JsonRedisSerializer()));
	}


	public static void main(String[] args) {
		SpringApplication.run(NashApplication.class, args);
	}

}