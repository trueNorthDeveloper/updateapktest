import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:truenorthdemoproject/user_screens/user_home_page.dart';
import 'package:truenorthdemoproject/user_screens/user_profile.dart';
import 'package:truenorthdemoproject/user_screens/user_work_history.dart';
import 'package:truenorthdemoproject/usersProvider/user_dashboard_provider.dart';



class ListOfUiScreen extends StatefulWidget {
  ListOfUiScreen({super.key});

  @override
  State<ListOfUiScreen> createState() => _ListOfUiScreenState();
}

class _ListOfUiScreenState extends State<ListOfUiScreen> {
  static const List<Widget> _screens = [
    UserHomePage(),
    UserWorkHistory(),
    UserProfileUI()
  ];
  @override
  Widget build(BuildContext context) {
    return

        // ignore: deprecated_member_use
        WillPopScope(
            onWillPop: () async => false,
            child: Consumer<UserDashboardProvider>(
              builder: (context, provider, child) {
                return Scaffold(
                  body: IndexedStack(
                    index: provider.currentIndex,
                    children: _screens,
                  ),
                  bottomNavigationBar: BottomNavigationBar(
                    currentIndex: provider.currentIndex,
                    onTap: provider.changePostion,
                    items: const [
                      BottomNavigationBarItem(
                          icon: Icon(Icons.home), label: 'Home'),
                      BottomNavigationBarItem(
                          icon: Icon(Icons.history), label: 'History'),
                      BottomNavigationBarItem(
                          icon: Icon(Icons.person), label: 'Profile'),
                    ],
                  ),
                );
              },
            ));
  }
}
