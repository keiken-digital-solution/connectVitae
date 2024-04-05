package io.connectvitae.core.config;

import feign.Logger;
import feign.Request;

public class CustomFeignLogger extends Logger {

  @Override
  protected void logRequest(String configKey, Level logLevel, Request request) {
    if (logLevel.ordinal() >= Level.BASIC.ordinal()) {
      super.logRequest(configKey, logLevel, request);
    }
  }

  @Override
  protected void log(String configKey, String format, Object... args) {
    System.out.printf(methodTag(configKey) + format + "%n", args);
  }
}
