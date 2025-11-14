import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:loading_animation_widget/loading_animation_widget.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:truenorthdemoproject/apiEndpoint/api_const.dart';
import 'package:truenorthdemoproject/commanUi/logout_ui.dart';
import 'package:truenorthdemoproject/controller/login_controller_provider.dart';
import 'package:truenorthdemoproject/user_screens/user_all_project.dart';
import 'package:truenorthdemoproject/usersModels/user_login_info_model.dart';
import 'package:truenorthdemoproject/usersProvider/user_dashboard_provider.dart';
import 'package:truenorthdemoproject/usersProvider/user_project_provider.dart';
import 'package:truenorthdemoproject/utils/app_image.dart';
import 'package:truenorthdemoproject/utils/mesage_snack_bar.dart';
import 'package:truenorthdemoproject/utils/size_config.dart';
import 'package:truenorthdemoproject/utils/text_style.dart';

class UserHomePage extends StatefulWidget {
  const UserHomePage({super.key});
  @override
  State<UserHomePage> createState() => _MyUserhomePage();
}

class _MyUserhomePage extends State<UserHomePage> {
  late Future<UserLoginInfoModel?> userloginInfo;

  @override
  void initState() {
    super.initState();

    userloginInfo = getLoginDetails();
  }

  Future<UserLoginInfoModel?> getLoginDetails() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? sessionId = await prefs.getString("sessionId");
    final url = Uri.parse('${Apiconstants.userLoginInfo}$sessionId');
    // final headers = {
    //   'Content-Type': 'application/json',
    //   'Authorization': 'Basic ' + base64Encode(utf8.encode('admin:admin123')),
    // };
    try {
      final response = await http.get(url);
      if (response.statusCode == 200 || response.statusCode == 201) {
        try {
          final data = jsonDecode(response.body);
          print(data);
          return UserLoginInfoModel.fromJson(data);
        } catch (jsonError) {
          print("Error parsing JSON: $jsonError");
          return null;
        }
      } else {
        print("Failed to load data. Status code: ${response.statusCode}");
        return null;
      }
    } on PlatformException {
      return null;
      //print("Platform error: $e");
      // ShowTaostMessage.toastMessage(context, "Platform error: $e");
    } on SocketException {
      return null;
      // print("Network error: $e");
      //ShowTaostMessage.toastMessage(context, "Network error: $e");
    } on FormatException {
      return null;
      // print("Format error: $e");
      //ShowTaostMessage.toastMessage(context, "Format error: $e");
    } catch (e) {
      print("Unexpected error: $e");
      return null;
    }
  }

  @override
  Widget build(BuildContext context) {
    Provider.of<UserDashboardProvider>(context, listen: false);
    SizeConFig.init(context);
    return Scaffold(
      body: SafeArea(
          child: SizedBox(
        height: SizeConFig.screenHeight,
        width: SizeConFig.screenWidth,
        child: Column(children: [
          SizedBox(height: SizeConFig.screenHeight * 2 / 100),
          SizedBox(
            width: SizeConFig.screenWidth * 95 / 100,
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        ShowTaostMessage.toastMessage(
                          context,
                          "help amd suppport",
                        );
                      },
                      child: Text("Help & Support"),
                    ),
                    Consumer<LoginProvider>(builder: (context, log, child) {
                      return ElevatedButton(
                          onPressed: () async {
                            bool status =
                                await showLogoutConfirmationDialog(context);

                            if (status) {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                    builder: (context) => Logoutui(),
                                  ));
                            }
                          },
                          child: Text("Logout"));
                    }),
                  ],
                ),
                SizedBox(height: SizeConFig.screenHeight * 1 / 100),
                Container(
                  child: Column(
                    children: [
                      // FutureBuilder(
                      //   future: userloginInfo,
                      //   builder: (context, snapshot) {
                      //     if (snapshot.error != null) {
                      //       return Text(" Error: ${snapshot.error}");
                      //     }
                      //     if (snapshot.hasData) {
                      //       return Column(
                      //         children: [
                      //           Column(
                      //             children: [
                      //               // Container(
                      //               //   height: SizeConFig.screenHeight * 1 / 10,
                      //               //   width: SizeConFig.screenWidth * 20 / 100,
                      //               //   decoration: BoxDecoration(
                      //               //       shape: BoxShape.circle,
                      //               //       image: DecorationImage(
                      //               //           image: NetworkImage(
                      //               //               snapshot.data!.imageURL),
                      //               //           fit: BoxFit.cover)),
                      //               // ),
                      //               ClipOval(
                      //                 child: snapshot.data!.imageURL.isNotEmpty
                      //                     ? Image.network(
                      //                         snapshot.data!.imageURL,
                      //                         height: SizeConFig.screenHeight *
                      //                             1 /
                      //                             10,
                      //                         width: SizeConFig.screenWidth *
                      //                             20 /
                      //                             100,
                      //                         fit: BoxFit.cover,
                      //                         loadingBuilder: (context, child,
                      //                             loadingProgress) {
                      //                           if (loadingProgress == null)
                      //                             return child;
                      //                           return Center(
                      //                               child:
                      //                                   CircularProgressIndicator());
                      //                         },
                      //                         errorBuilder:
                      //                             (context, error, stackTrace) {
                      //                           return Image.asset(
                      //                             Appimage.splash,
                      //                             height:
                      //                                 SizeConFig.screenHeight *
                      //                                     1 /
                      //                                     10,
                      //                             width:
                      //                                 SizeConFig.screenWidth *
                      //                                     20 /
                      //                                     100,
                      //                             fit: BoxFit.cover,
                      //                           );
                      //                         },
                      //                       )
                      //                     : Image.asset(
                      //                         Appimage.splash,
                      //                         height: SizeConFig.screenHeight *
                      //                             1 /
                      //                             10,
                      //                         width: SizeConFig.screenWidth *
                      //                             20 /
                      //                             100,
                      //                         fit: BoxFit.cover,
                      //                       ),
                      //               ),
                      //               customUIandText("Hey,",
                      //                   snapshot.data!.empName, Colors.black),
                      //               customUIandText(
                      //                   "Your Login Time Is: ",
                      //                   snapshot.data!.formattedLoginTime!,
                      //                   Colors.green),
                      //               customUIandText(
                      //                   "Ideal Logout Time: ",
                      //                   snapshot.data!.formattedLogoutTime!,
                      //                   Colors.red),
                      //             ],
                      //           ),
                      //         ],
                      //       );
                      //     }
                      //     return Center(
                      //       child: LoadingAnimationWidget.inkDrop(
                      //         color: Color(0xfffb934d),
                      //         size: 50,
                      //       ),
                      //     );
                      //   },
                      // )
                    ],
                  ),
                ),
                Consumer<UserDashboardProvider>(
                  builder: (context, useFun, child) {
                    return Row(
                      children: [
                        GestureDetector(
                          onTap: () {
                            useFun.chnageColorOnOff();
                          },
                          child: Icon(
                            Icons.view_agenda,
                            size: SizeConFig.screenWidth * 10 / 100,
                            color:
                                useFun.changeColor ? Colors.black : Colors.blue,
                          ),
                        ),
                      ],
                    );
                  },
                ),
                SizedBox(height: SizeConFig.screenHeight * 1 / 100),
                Consumer<UserDashboardProvider>(
                  builder: (context, userFun2, child) {
                    return userFun2.changeColor
                        ? Container()
                        : Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            // children: [
                            //   customBox("Attendance", context),
                            //   customBox(
                            //     "Project",
                            //     context,

                            //     UserAllProject(),
                            //   ),
                            //   customBox("Leave", context)
                            // ],
                            children: [
                              customBox("Attendance", context),
                              customBox(
                                "Project",
                                context,
                                screenName: UserAllProject(),
                                onTapCallback: () async {
                                  final provider =
                                      Provider.of<UserProjectProvider>(context,
                                          listen: false);
                                  await provider.userAllProject(context);

                                  // Navigate only after data is fetched
                                  Navigator.of(context).push(MaterialPageRoute(
                                    builder: (_) => UserAllProject(),
                                  ));
                                },
                              ),
                              customBox("Leave", context),
                            ],
                          );
                  },
                ),
                SizedBox(height: SizeConFig.screenHeight * 1 / 100),
                Consumer<UserDashboardProvider>(
                  builder: (context, use3, child) {
                    return use3.changeColor
                        ? Container()
                        : Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              customBox("Report", context),
                              customBox("To Do", context),
                              customBox("My chart", context),
                            ],
                          );
                  },
                ),
                SizedBox(height: SizeConFig.screenHeight * 1 / 100),
                Consumer<UserDashboardProvider>(
                  builder: (context, use4, child) {
                    return use4.changeColor
                        ? Container()
                        : Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: [
                              customBox("Instrument Libary", context),
                              customBox("Expenses", context),
                              customBox("High Priority", context),
                            ],
                          );
                  },
                ),
                TextButton(
                    onPressed: () async {
                      print(Apiconstants.baseUrlApi);

                      final url = Apiconstants.loginNgrok;
                      final response = await http.get(Uri.parse(url));
                      if (response.statusCode == 200) {
                        final json = jsonDecode(response.body);
                        var baseUrl = json['base_url'];
                        print(baseUrl);

                        return json['base_url'];
                      } else {
                        return null;
                      }
                    },
                    child: Text("click"))
              ],
            ),
          ),
        ]),
      )),
    );
  }

  // Widget customBox(String textMsg, BuildContext context,
  //     [Widget? screenName]) {
  //   return GestureDetector(
  //     onTap: () {
  //       if (screenName != null) {
  //         Navigator.of(
  //           context,
  //         ).push(MaterialPageRoute(builder: (context) => screenName));
  //       } else {
  //         ShowTaostMessage.toastMessage(context, textMsg);
  //       }
  //     },
  //     child: Container(
  //       height: SizeConFig.screenHeight * 8 / 100,
  //       width: SizeConFig.screenWidth * 20 / 100,
  //       decoration: BoxDecoration(
  //         border: Border.all(
  //           color: const Color.fromARGB(255, 239, 211, 193),
  //           width: 2,
  //         ),
  //         borderRadius: BorderRadius.circular(10),
  //         color: const Color.fromARGB(255, 192, 212, 221),
  //       ),
  //       child: Center(
  //         child: Text(
  //           textMsg,
  //           textAlign: TextAlign.center,
  //           style: CustomText.nameOfTextStyle,
  //         ),
  //       ),
  //     ),
  //   );
  // }
  Widget customBox(
    String textMsg,
    BuildContext context, {
    Widget? screenName,
    VoidCallback? onTapCallback,
  }) {
    return GestureDetector(
      onTap: () {
        if (onTapCallback != null) {
          onTapCallback();
        } else if (screenName != null) {
          Navigator.of(context).push(
            MaterialPageRoute(builder: (_) => screenName),
          );
        } else {
          ShowTaostMessage.toastMessage(context, textMsg);
        }
      },
      child: Container(
        height: SizeConFig.screenHeight * 8 / 100,
        width: SizeConFig.screenWidth * 20 / 100,
        decoration: BoxDecoration(
          border: Border.all(
            color: const Color.fromARGB(255, 239, 211, 193),
            width: 2,
          ),
          borderRadius: BorderRadius.circular(10),
          color: const Color.fromARGB(255, 192, 212, 221),
        ),
        child: Center(
          child: Text(
            textMsg,
            textAlign: TextAlign.center,
            style: CustomText.nameOfTextStyle,
          ),
        ),
      ),
    );
  }

  Future<bool> showLogoutConfirmationDialog(BuildContext context) async {
    return await showDialog<bool>(
          context: context,
          barrierDismissible: false,
          builder: (context) => AlertDialog(
            // title: const Text("Thanks for  your effective work have a good day"),
            content: const Text("Do you want to logout  or Continue"),
            actions: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  TextButton(
                    onPressed: () => Navigator.of(context).pop(false),
                    child: const Text("No"),
                  ),
                  TextButton(
                    onPressed: () => Navigator.of(context).pop(true),
                    child: const Text("Yes"),
                  ),
                ],
              )
            ],
          ),
        ) ??
        false;
  }

  Widget logoutAnimation() {
    return Center(
      child: LoadingAnimationWidget.inkDrop(
        color: Color(0xfffb934d),
        size: 50,
      ),
    );
  }

//  hey navendra  you can use this function to create a custom UI and text
  customUIandText(String s, String empName, Color colorName) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text(
          s,
          style: TextStyle(
              fontSize: 12, fontWeight: FontWeight.w600, color: colorName),
        ),
        Text(
          empName,
          style: TextStyle(
              fontSize: 12, fontWeight: FontWeight.w600, color: colorName),
        ),
      ],
    );
  }
}
