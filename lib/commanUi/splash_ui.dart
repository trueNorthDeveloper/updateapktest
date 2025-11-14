import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:truenorthdemoproject/commanUi/select_screen.dart';
import 'package:truenorthdemoproject/user_screens/main_screen.dart';
import 'package:truenorthdemoproject/usersProvider/user_dashboard_provider.dart';
import 'package:truenorthdemoproject/utils/app_image.dart';
import 'package:truenorthdemoproject/utils/size_config.dart';


class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    _checkAutoLogin();
  }
Future<void> _checkAutoLogin() async {
  try {
    final prefs = await SharedPreferences.getInstance();
    final id = prefs.getString('empId');
    final empName = prefs.getString('empName');

    if (id != null && empName != null) {
      WidgetsBinding.instance.addPostFrameCallback((_) {
        if (!mounted) return;
        Provider.of<UserDashboardProvider>(context, listen: false)
            .changePostion(0);
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (_) => ListOfUiScreen()), 
        );
      });
    } else {
      Future.delayed(const Duration(seconds: 3), () {
        if (!mounted) return;
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (_) => const SelectScreenForService()),
        );
      });
    }
  } catch (e) {
    
    debugPrint("Auto-login error: $e");
  }
}


  @override
  Widget build(BuildContext context) {
    SizeConFig.init(context);
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: SizedBox(
            height: SizeConFig.screenHeight * 50 / 100,
            width: SizeConFig.screenWidth * 80 / 100,
            //  height: 50,
            child: Image.asset(Appimage.splash, fit: BoxFit.fill),
            // child:Text("splash screen"),
          ),
        ),
      ),
    );
  }
}
