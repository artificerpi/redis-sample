package com.example.demo.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class GsonRedisSerializer implements RedisSerializer {

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
