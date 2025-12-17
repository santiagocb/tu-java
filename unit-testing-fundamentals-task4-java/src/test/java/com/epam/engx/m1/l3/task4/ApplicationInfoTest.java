package com.epam.engx.m1.l3.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationInfoTest {

  private ApplicationInfoRepository repositoryMock;
  private ApplicationInfo applicationInfo;

  @BeforeEach
  void setUp() {
    repositoryMock = Mockito.mock(ApplicationInfoRepository.class);
    applicationInfo = new ApplicationInfo(repositoryMock);
  }

  @Test
  void store_shouldSaveFormattedApplicationInfoToRepository() {
    // Arrange: Prepare expected values for verification
    String expectedOsName = System.getProperty("os.name");
    String expectedJavaVersion = Runtime.version().toString();
    String expectedWorkingDir = System.getProperty("user.dir");

    String expectedInfo = String.format(
      "\n* Operating system: %s\n* Java version: %s\n* Working directory: %s",
      expectedOsName,
      expectedJavaVersion,
      expectedWorkingDir
    );

    // Act: Call store()
    applicationInfo.store();

    // Assert: Verify repository.save() is called with the correct formatted string
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(repositoryMock).save(captor.capture());

    String actualInfo = captor.getValue();
    assertEquals(expectedInfo, actualInfo, "Stored application info does not match the expected format.");
  }

  @Test
  void constructor_shouldThrowExceptionWhenRepositoryIsNull() {
    // Act & Assert: Ensure the constructor throws an exception when the repository is null
    assertThrows(NullPointerException.class, () -> {
      new ApplicationInfo(null);
    });

  }
}
