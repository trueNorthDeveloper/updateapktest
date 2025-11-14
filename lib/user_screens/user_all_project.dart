import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:truenorthdemoproject/user_screens/user_project_details_screen.dart';

import 'package:truenorthdemoproject/usersProvider/user_project_provider.dart';
import 'package:truenorthdemoproject/utils/app_image.dart';
import 'package:truenorthdemoproject/utils/size_config.dart';

class UserAllProject extends StatefulWidget {
  const UserAllProject({super.key});

  @override
  State<UserAllProject> createState() => _UserAllProjectState();
}

class _UserAllProjectState extends State<UserAllProject> {
  List<dynamic> projectItem = <dynamic>[
    {
      "projectCode": "TNP01",
      "projectName": "Endward Thomas  map and  geolocation",
      "date": "1-1-2025",
      "time": "10:15 AM",
      "description": "Site preparation and garden layout.",
      "remainingDays": "5 day left",
      "progress report": "1",
      "totalTask": "10",
      "completeTask": "2",
    },
    {
      "projectCode": "TNP02",
      "projectName": "Edward Thomas",
      "date": "1-1-2025",
      "time": "10:15 AM",
      "description": "Site preparation and garden layout.",
      "remainingDays": "5 day left",
      "progress report": "1",
      "totalTask": "10",
      "completeTask": "2",
    },
    {
      "projectCode": "TNP03",
      "projectName": "Edward Thomas",
      "date": "1-1-2025",
      "time": "10:15 AM",
      "description": "Site preparation and garden layout.",
      "remainingDays": "5 day left",
      "progress report": "1",
      "totalTask": "10",
      "completeTask": "2",
    },
    {
      "projectCode": "TNP04",
      "projectName": "Edward Thomas",
      "date": "1-1-2025",
      "time": "10:15 AM",
      "description": "Site preparation and garden layout.",
      "remainingDays": "5 day left",
      "progress report": "1",
      "totalTask": "10",
      "completeTask": "2",
    },
  ];

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    final screenWidth = MediaQuery.of(context).size.width;
    return Scaffold(
      
      body: 
       RefreshIndicator(
      onRefresh: () async {
        // Fetch fresh data
        // await pro.fetchUserProjects(forceRefresh: true);
      }, child: 
      
      SafeArea(
        child: Center(
          child: Container(
            height: screenHeight, // ✅ 90% of screen height
            width: screenWidth * 0.95, // ✅ 90% of screen width
            decoration: BoxDecoration(
              //  color: Colors.grey[200],
              //borderRadius: BorderRadius.circular(20),
              //border: Border.all(color: Colors.grey),
              image: DecorationImage(
                image: AssetImage(Appimage.whitebg),
                fit: BoxFit.cover,
              ),
            ),
            child: Column(
              // mainAxisAlignment: MainAxisAlignment.start,
              //crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(height: screenHeight * 2 / 100),
                Container(
                  height: SizeConFig.screenHeight * 0.25,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(20)),
                    color: const Color.fromARGB(255, 255, 255, 255),
                    border: Border.all(
                      color: const Color.fromARGB(255, 232, 235, 230),
                      width: 2,
                      style: BorderStyle.values[1],
                    ),
                  ),
                  child: Center(child: Text("Progrees bar")),
                ),
                Container(
                  padding: EdgeInsets.all(10),
                  height: SizeConFig.screenHeight * 0.35,
                  width: SizeConFig.screenHeight * 0.95,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(20)),
                    color: const Color.fromARGB(255, 235, 235, 235),
                    border: Border.all(
                      color: const Color.fromARGB(255, 245, 244, 244),
                      width: 2,
                      style: BorderStyle.values[1],
                    ),
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Text(
                      //   "Projects",
                      //   style: TextStyle(
                      //     fontSize: 20,
                      //     fontWeight: FontWeight.w800,
                      //   ),
                      // ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          customText("S NO"),
                          customText("Name"),
                          customText("Started"),
                          customText("Status"),
                        ],
                      ),
                      SizedBox(height: SizeConFig.screenHeight * 1 / 100),
                      Consumer<UserProjectProvider>(
                        builder: (context, pro, child) {
                          return Expanded(
                            child: ListView.separated(
                              itemCount: pro.projects.length,
                              separatorBuilder: (context, index) =>
                                  SizedBox(width: 15),
                              scrollDirection: Axis.vertical,
                              itemBuilder: (context, index) {
                                return GestureDetector(
                                  onTap: () {
                                    Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                        builder: (context) => ProjectDetails(),
                                      ),
                                    );
                                  },
                                  child: Column(
                                    children: [
                                      Container(
                                        height:
                                            SizeConFig.screenHeight * 5 / 100,
                                        width:
                                            SizeConFig.screenWidth * 92 / 100,
                                        decoration: BoxDecoration(
                                          borderRadius: BorderRadius.all(
                                            Radius.circular(20),
                                          ),
                                          color: const Color.fromARGB(
                                            255,
                                            254,
                                            253,
                                            253,
                                          ),
                                          border: Border.all(
                                            color: const Color.fromARGB(
                                              255,
                                              245,
                                              244,
                                              244,
                                            ),
                                            width: 2,
                                            style: BorderStyle.values[1],
                                          ),
                                        ),
                                        child: Row(
                                          children: [
                                            customWidget(
                                              pro.projects[index].projectUid
                                                  .toString(),
                                            ),
                                            customWidget(
                                              pro.projects[index].projectName,
                                            ),
                                            SizedBox(
                                              height: SizeConFig.screenHeight *
                                                  1 /
                                                  100,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ],
                                  ),
                                );
                              },
                            ),
                          );
                        },
                      ),
                      Text(
                        "you recently worked with",
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.w800,
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
       )
    );
  }

  Widget customWidget(String projectUid) {
    return Padding(
      padding: const EdgeInsets.all(0.0),
      child: SizedBox(
        // color:Colors.red,
        width: SizeConFig.screenWidth * 25 / 100,
        child: Text(
          projectUid,
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          softWrap: false,
          style: TextStyle(
            color: const Color.fromARGB(255, 164, 172, 171),
            fontWeight: FontWeight.bold,
            fontSize: 11.0,
          ),
        ),
      ),
    );
  }

  Widget customText(String textName) {
    return SizedBox(
      width: SizeConFig.screenWidth * 22 / 100,
      child: Center(
        child: Text(
          textName,
          style: TextStyle(
            fontSize: 12,
            fontWeight: FontWeight.w600,
            color: const Color.fromARGB(255, 112, 108, 112),
          ),
        ),
      ),
    );
  }

  Widget customstringform(p1, p2) {
    return Text(
      "$p1/$p2",
      maxLines: 1,
      overflow: TextOverflow.ellipsis,
      softWrap: false,
      style: TextStyle(
        color: const Color.fromARGB(255, 164, 172, 171),
        fontWeight: FontWeight.bold,
        fontSize: 11.0,
      ),
    );
  }
}
