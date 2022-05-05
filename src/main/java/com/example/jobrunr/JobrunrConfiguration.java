package com.example.jobrunr;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.redis.LettuceRedisStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.jobrunr.utils.resilience.RateLimiter.Builder.rateLimit;

@Configuration
public class JobrunrConfiguration {
    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper){
        final LettuceRedisStorageProvider lettuceRedisStorageProvider=new LettuceRedisStorageProvider(
                getRedisClient(),
                rateLimit().withoutLimits());
        lettuceRedisStorageProvider.setJobMapper(jobMapper);
        return lettuceRedisStorageProvider;
    }

    private RedisClient getRedisClient(){
        return RedisClient.create(RedisURI.create("127.0.0.1",6379));
    }
}
