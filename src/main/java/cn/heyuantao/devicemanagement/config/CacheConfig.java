package cn.heyuantao.devicemanagement.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Arrays;

/**
 * @author he_yu
 * 配置缓存相关的内容，主要使用了Redis作为缓存
 */

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    /**
     * 设置缓存的超时时间,默认的超时时间为60秒
     */
    @Value("${custom.cache-expire-time:60}")
    private int cacheExpireTime;

    /**
     * 配置该方法后，在序列号的时候不会在Redis中出现乱码
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(objectJackson2JsonRedisSerializer))
                .entryTtl(Duration.ofSeconds(cacheExpireTime))
                .disableCachingNullValues();

        System.out.println("Cache expire time is :"+cacheExpireTime+" seconds !");
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }

    /**
     * 设置在使用redis缓存数据的时候，数据后缀的内容，例如Key的样式为X::content，该配置的内容显示在content的部分.
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target,method,params)->{
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            sb.append("-");
            //sb.append(StringUtils.arrayToDelimitedString(params, "_"));
            sb.append(Arrays.deepHashCode(params));
            return sb.toString();
        };
    }

}
