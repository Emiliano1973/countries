package com.example.counties.configuration;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory connectionFactory(){
        RedisProperties properties=properties();
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
        configuration.setHostName(properties.getHost());
        configuration.setPort(properties.getPort());
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, ?> redisTemplate(){
        RedisTemplate<String, ?> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    @Primary
    public RedisProperties properties(){
    return  new RedisProperties();
    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }

}