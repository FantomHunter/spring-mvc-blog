package com.codehunter.springmvcblog.util;

import java.util.regex.Pattern;

public final class UUIDUtil {
  private static final UUIDUtil instance = new UUIDUtil();
  private static final Pattern UUID_REGEX_PATTERN =
      Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

  private UUIDUtil() {}

  public static UUIDUtil getInstance() {
    return instance;
  }

  public boolean isValidUUID(String str) {
    if (str == null) {
      return false;
    }
    return UUID_REGEX_PATTERN.matcher(str).matches();
  }
}
