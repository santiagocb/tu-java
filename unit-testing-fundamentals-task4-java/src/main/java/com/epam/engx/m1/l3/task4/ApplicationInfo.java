package com.epam.engx.m1.l3.task4;

import java.util.Objects;

class ApplicationInfo {

  private final ApplicationInfoRepository repository;

  ApplicationInfo(ApplicationInfoRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  void store() {
    repository.save(String.format("\n* Operating system: %s\n* Java version: %s\n* Working directory: %s",
      System.getProperty("os.name"),
      Runtime.version(),
      System.getProperty("user.dir")));
  }

}
