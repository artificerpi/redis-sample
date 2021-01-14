/**
 * Copyright (C) 2021 artificerpi
 *
 * Licensed under MIT License
 */
package com.example.demo;

import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author artificerpi
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

  private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(Kryo::new);

  private Class<T> clazz;

  public KryoRedisSerializer(Class<T> clazz) {
    super();
    this.clazz = clazz;
  }

  @Override
  public byte[] serialize(T t) throws SerializationException {
    if (t == null) {
      return EMPTY_BYTE_ARRAY;
    }

    Kryo kryo = kryos.get();
    kryo.setReferences(false);
    kryo.register(clazz);

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); Output output = new Output(baos)) {
      kryo.writeClassAndObject(output, t);
      output.flush();
      return baos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return EMPTY_BYTE_ARRAY;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T deserialize(byte[] bytes) throws SerializationException {
    if (bytes == null || bytes.length <= 0) {
      return null;
    }

    Kryo kryo = kryos.get();
    kryo.setReferences(false);
    kryo.register(clazz);

    try (Input input = new Input(bytes)) {
      return (T) kryo.readClassAndObject(input);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
