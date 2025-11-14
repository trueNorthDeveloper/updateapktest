import 'dart:io';

import 'package:flutter/foundation.dart';

enum PlatformType {
  web,
  android,
  ios,
  windows,
  macos,
  linux,
  unknown,
}

PlatformType getPlatformType() {
  if (kIsWeb) return PlatformType.web;
  if (Platform.isAndroid) return PlatformType.android;
  if (Platform.isIOS) return PlatformType.ios;
  if (Platform.isWindows) return PlatformType.windows;
  if (Platform.isMacOS) return PlatformType.macos;
  if (Platform.isLinux) return PlatformType.linux;

  return PlatformType.unknown;
}
