/**
 * Copyright (C) 2021 artificerpi
 *
 * Licensed under MIT License
 */
package com.example.demo.service.impl;

import com.example.demo.service.IFooService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author artificerpi
 */
@Service
public class FooServiceImpl implements IFooService {

  @Cacheable(value = "cache_test")
  @Override
  public String getResult(int i) {
    try {
      long time = 2000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }

    return "success" + i;
  }

}
