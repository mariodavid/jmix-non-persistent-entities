package io.jmix.npe.app;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CacheConfig {
//    @Bean
//    public ConcurrentMapCacheManager cacheManager() {
//        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("orders,jmix-locks-cache");
//        cacheManager.setCacheNames(List.of("orders,jmix-locks-cache"));
//        return cacheManager;
//    }
}
