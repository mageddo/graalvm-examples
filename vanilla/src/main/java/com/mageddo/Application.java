package com.mageddo;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    LOG.info("Hello World!!!");
  }

  private static void fileReadingPerformanceCheck() throws IOException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Runtime.getRuntime().exec("truncate --size 4G /tmp/file");
    try (final InputStream in = Files.newInputStream(Paths.get("/tmp/file"));) {
      final byte[] buff = new byte[8124];
      int i = 0;
      while (in.read(buff) != -1) {
        i++;
        int x = 5 * buff[0];
        if (i % 100000 == 0) {
          LOG.info("i={}", i);
        }
      }
    }
    LOG.info("done time={}", stopWatch.getTime());
  }
}
