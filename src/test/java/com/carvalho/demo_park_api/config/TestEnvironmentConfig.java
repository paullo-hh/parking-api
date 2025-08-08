package com.carvalho.demo_park_api.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestEnvironmentConfig {

  static {
    loadTestEnv();
  }

  private static void loadTestEnv() {
    Dotenv dotenv = Dotenv
        .configure()
        .filename("/.env.test")
        .load();
    dotenv.entries().forEach ( entry  -> {
      System.setProperty(entry.getKey(), entry.getValue());
    });
  }
}

