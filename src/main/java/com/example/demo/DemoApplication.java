package com.example.demo;

import java.time.Duration;
import java.time.Instant;

import com.example.demo.service.IFooService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private IFooService fooService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// LOG.info("EXECUTING : command line runner");

		// redisTemplate.opsForValue().set("greeting", "hello");
		// String val = Objects.toString(redisTemplate.opsForValue().get("greeting"));

		// LOG.info("Read value #{} from cache", val);

		for (int i = 0; i < 5; i++) {
			Instant start = Instant.now();
			String result = fooService.getResult(i);
			LOG.info("Takes {} to get result {}", Duration.between(start, Instant.now()), result);
		}
	}

}
