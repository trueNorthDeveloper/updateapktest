

import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:open_filex/open_filex.dart';
import 'package:package_info_plus/package_info_plus.dart';
import 'package:path_provider/path_provider.dart';

class UpdateManager {
  static const String versionUrl = "http://sscpl.org.in/app/version.json";

  static Future<void> checkForUpdate(BuildContext context) async {
    try {
      print("update mamager is working-------------------------------------");
      final response = await http.get(Uri.parse(versionUrl));
      if (response.statusCode != 200) return;

      final data = jsonDecode(response.body);

      int latestVersion = data["latest_version_code"];
      String apkUrl = data["download_url"];
      String changelog = data["changelog"];
      bool mandatory = data["mandatory"];

      // Get current app version
      PackageInfo info = await PackageInfo.fromPlatform();
      int currentVersion = int.parse(info.buildNumber);

      // Check if update available
      if (latestVersion > currentVersion) {
        showDialog(
          context: context,
          barrierDismissible: !mandatory,
          builder: (_) => AlertDialog(
            title: Text("Update Available"),
            content: Text(changelog),
            actions: [
              if (!mandatory)
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: Text("Later"),
                ),
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context);
                  downloadAndInstall(apkUrl, context);
                },
                child: Text("Update"),
              ),
            ],
          ),
        );
      }
    } catch (e) {
      print("Update check error: $e");
    }
  }

  static Future<void> downloadAndInstall(String url, BuildContext context) async {
    try {
      Dio dio = Dio();

      Directory dir = await getTemporaryDirectory();
      String filePath = "${dir.path}/update.apk";

      final dialog = showDialog(
        context: context,
        barrierDismissible: false,
        builder: (_) => AlertDialog(
          title: Text("Downloading Update..."),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              LinearProgressIndicator(),
              SizedBox(height: 16),
              Text("Please wait..."),
            ],
          ),
        ),
      );

      await dio.download(url, filePath);
      Navigator.pop(context);

      // Open APK installer
      await OpenFilex.open(filePath);

    } catch (e) {
      print("APK download error: $e");
    }
  }
}
