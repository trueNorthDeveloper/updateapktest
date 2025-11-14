import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:open_filex/open_filex.dart';
import 'package:package_info_plus/package_info_plus.dart';
import 'package:provider/provider.dart';
import 'package:truenorthdemoproject/adminProviders/admin_dashBoard_provider.dart';
import 'package:truenorthdemoproject/adminUi/admin_splash_screen.dart';
import 'package:truenorthdemoproject/apiEndpoint/api_const.dart';
import 'package:truenorthdemoproject/commanUi/splash_ui.dart';
import 'package:truenorthdemoproject/controller/login_controller_provider.dart';
import 'package:truenorthdemoproject/user_screens/UpdateManager.dart';
import 'package:truenorthdemoproject/usersProvider/user_dashboard_provider.dart';
import 'package:truenorthdemoproject/usersProvider/user_project_provider.dart';
import 'package:truenorthdemoproject/utils/platform_type.dart';
import 'package:truenorthdemoproject/utils/themdata.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  // Platform platform = getPlatform();
  // Platform.isAndroid

  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => LoginProvider()),
        ChangeNotifierProvider(create: (_) => UserDashboardProvider()),
        ChangeNotifierProvider(create: (_) => AdminDashboardProvider()),
        ChangeNotifierProvider(create: (_) => UserProjectProvider()),

        // ChangeNotifierProvider(create: (_) => bookingProvider()),
        // ChangeNotifierProvider(create: (_) => profileProvider()),
        // ChangeNotifierProvider(create: (_) => notificationProvider()),
      ],
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    checkForUpdate();
    //apiUrlFun();
    // Future.delayed(Duration(seconds: 1), () {
    // UpdateManager.checkForUpdate(context);
    //});
  }

  Future<void> checkForUpdate() async {
    // Get current app version
    final packageInfo = await PackageInfo.fromPlatform();
    String currentVersion = packageInfo.version;
    print("-------------------current version----------------");

    // Fetch updategd.json from Google Drive
    final response = await http.get(Uri.parse(
        "https://drive.google.com/uc?export=download&id=1nJJHx-SKM3pMLFLuBf1kqyLHDbwYaWWh"));

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);
      String latestVersion = data['latest_version'];
      print("-----------------Latest version-----------------");
      print(latestVersion);

      String apkUrl = data['apk_url'];
      print(apkUrl);
      // String fileID = data['fileID'];

      // Compare versions
      if (latestVersion != currentVersion) {
        print("Update available");
        await downloadAndInstall(apkUrl);
      }
    } else {
      print("App is up to date");
    }
  }

  Future<bool> downloadFile(String url, String savePath) async {
  try {
    final dio = Dio();

    print("Downloading APK from Google Drive...");
    
    final response = await dio.get(
      url,
      options: Options(
        responseType: ResponseType.bytes,
        followRedirects: true,
        validateStatus: (status) => status != null && status < 500,
      ),
    );

    final file = File(savePath);
    await file.writeAsBytes(response.data);

    print("APK downloaded successfully to $savePath");
    print(await File(savePath).length());
    return true;
  } catch (e) {
    print("Download error: $e");
    return false;
  }
}

Future<void> downloadAndInstall(String url) async {
  final directory = await Directory.systemTemp.createTemp();
  final savePath = "${directory.path}/update.apk";

  print("Saving APK to: $savePath");

  bool success = await downloadFile(url, savePath);

  if (success) {
    print("Opening APK installer...");
    await OpenFilex.open(savePath);
  } else {
    print("Failed to download APK");
  }
}


  // Future<void> apiUrlFun() async {
  //   final url =
  //       'https://gist.github.com/trueNorthDeveloper/3f3c234a6720360f7ddf84771a953faa/raw/update-truno.json';

  //   final response = await http.get(Uri.parse(url));

  //   if (response.statusCode == 200) {
  //     final json = jsonDecode(response.body);
  //     final dowloadUrl = json['download_url'];
  //     print(
  //         "---------------------------------------------------------------------");
  //     print(dowloadUrl);
  //   } else {
  //     print("Failed to fetch api url");
  //   }
  // }

  @override
  Widget build(BuildContext context) {
    final platform = getPlatformType();

    return MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: MyAppTheme.lightTheme,
        darkTheme: MyAppTheme.darkTheme,
        home: platform == PlatformType.web
            ? AdminSplashScreen()
            : SplashScreen());
  }
}
