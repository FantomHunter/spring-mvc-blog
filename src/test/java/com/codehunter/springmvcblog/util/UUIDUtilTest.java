package com.codehunter.springmvcblog.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class UUIDUtilTest {
  @Test
  public void isValidUUID_shouldFalse() {
    assertFalse(UUIDUtil.getInstance().isValidUUID(null));

    boolean actual = UUIDUtil.getInstance().isValidUUID("ssk");
    assertFalse(actual);
  }

  @Test
  public void is_valid_uuid_ok() {
    assertTrue(UUIDUtil.getInstance().isValidUUID("009692ee-f930-4a74-bbd0-63b8baa5a927"));
  }
}
