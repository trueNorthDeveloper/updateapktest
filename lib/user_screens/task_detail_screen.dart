import 'package:flutter/material.dart';

import 'package:provider/provider.dart';
import 'package:truenorthdemoproject/usersProvider/user_dashboard_provider.dart';
import 'package:truenorthdemoproject/utils/size_config.dart';


class TaskDetailScreen extends StatefulWidget {
  const TaskDetailScreen({super.key});

  @override
  State<TaskDetailScreen> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<TaskDetailScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: Column(
          children: [
            SizedBox(
              height: SizeConFig.screenHeight * 3 / 100,
            ),
            SafeArea(
              child: Column(
                children: [
                  SizedBox(
                    //height: SizeConFig.screenHeight * 25 / 100,
                    width: SizeConFig.screenWidth * 95 / 100,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        SizedBox(height: SizeConFig.screenHeight * 3 / 100),
                        Center(
                          child: Text(
                            "Aastha rani pura survey",
                            style: TextStyle(
                              fontSize: 20,
                              fontWeight: FontWeight.w600,
                              fontStyle: FontStyle.normal,
                            ),
                          ),
                        ),
                        SizedBox(height: SizeConFig.screenHeight * 3 / 100),

                        Center(
                          child: SizedBox(
                            height: SizeConFig.screenHeight * 15 / 100,
                            width: SizeConFig.screenWidth * 80 / 100,
                            //  color: Colors.green,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                costomTextbox("Status", "Completed"),
                                SizedBox(
                                  height: SizeConFig.screenHeight * 0.5 / 100,
                                ),
                                costomTextbox("Assigned to", "Alex herry"),
                                SizedBox(
                                  height: SizeConFig.screenHeight * 0.5 / 100,
                                ),
                                costomTextbox("Due Date", "December 5, 2023"),
                              ],
                            ),
                          ),
                        ),

                        SizedBox(height: SizeConFig.screenHeight * 3 / 100),
                        Padding(
                          padding: const EdgeInsets.only(left: 30),
                          child: SizedBox(
                            width: SizeConFig.screenWidth * 70 / 100,

                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Column(
                                  children: [
                                    customButtomD("Comment", 0),
                                    SizedBox(
                                      height: SizeConFig.screenHeight * 1 / 100,
                                    ),
                                    customLineD(0),
                                  ],
                                ),

                                Column(
                                  children: [
                                    customButtomD("Submit task", 1),
                                    SizedBox(
                                      height: SizeConFig.screenHeight * 1 / 100,
                                    ),
                                    customLineD(1),
                                  ],
                                ),
                              ],
                            ),
                          ),
                        ),

                        SizedBox(height: SizeConFig.screenHeight * 2 / 100),
                        Consumer<UserDashboardProvider>(
                          builder: (context, ch1, child) {
                            if (ch1.intchangeColorInTaskDetail == 0) {
                              return Stack(
                                children: [
                                  Positioned(
                                    child: Container(
                                      height:
                                          SizeConFig.screenHeight * 10 / 100,
                                      decoration: BoxDecoration(
                                        color: const Color.fromARGB(
                                          255,
                                          244,
                                          243,
                                          243,
                                        ),
                                      ),
                                      child: TextField(
                                        decoration: InputDecoration(
                                          hintText: "Add a comment........",
                                          hintStyle: TextStyle(
                                            color: Colors.blueGrey,
                                            fontSize: 14,
                                            fontWeight: FontWeight.w500,
                                          ),
                                          border: OutlineInputBorder(
                                            borderSide: BorderSide.none,
                                            //  borderSide:BorderSide(color:const Color.fromARGB(255, 22, 80, 70)),
                                            borderRadius: BorderRadius.all(
                                              Radius.circular(10),
                                            ),
                                          ),
                                        ),
                                      ),
                                    ),
                                  ),
                                  Positioned(
                                    left: 250,
                                    top: 30,

                                    child: Row(
                                      children: [
                                        ElevatedButton(
                                          onPressed: () {
                                           // print("comment");
                                          },
                                          child: Center(child: Text("Comment")),
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                              );
                            }
                            return Stack(
                              children: [
                                Positioned(
                                  child: Container(
                                    height: SizeConFig.screenHeight * 10 / 100,
                                    decoration: BoxDecoration(
                                      color: const Color.fromARGB(
                                        255,
                                        244,
                                        243,
                                        243,
                                      ),
                                    ),
                                    child: TextField(
                                      decoration: InputDecoration(
                                        hintText: "Submit task...",
                                        hintStyle: TextStyle(
                                          color: Colors.blueGrey,
                                          fontSize: 14,
                                          fontWeight: FontWeight.w500,
                                        ),
                                        border: OutlineInputBorder(
                                          borderSide: BorderSide.none,
                                          //  borderSide:BorderSide(color:const Color.fromARGB(255, 22, 80, 70)),
                                          borderRadius: BorderRadius.all(
                                            Radius.circular(10),
                                          ),
                                        ),
                                      ),
                                    ),
                                  ),
                                ),
                                Positioned(
                                  left: 220,
                                  top: 30,

                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                    children: [
                                      Icon(Icons.picture_as_pdf),
                                      Icon(Icons.image),
                                      ElevatedButton(
                                        onPressed: () {
                                         // print("submit task...");
                                        },
                                        child: Center(child: Text("submit")),
                                      ),
                                    ],
                                  ),
                                ),
                              ],
                            );
                          },
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget costomTextbox(String textName, String textItem) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      // crossAxisAlignment:CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            SizedBox(
              // width: SizeConFig.screenWidth * 22 / 100,
              child: Text(
                textName,
                style: TextStyle(fontWeight: FontWeight.w600, fontSize: 15),
              ),
            ),

            SizedBox(
              width: SizeConFig.screenWidth * 35 / 100,
              child: Text(
                textItem,
                style: TextStyle(
                  fontWeight: FontWeight.w600,
                  fontSize: 15,
                  color: Colors.blueGrey,
                ),
              ),
            ),
          ],
        ),
      ],
    );
  }

  Widget customButtomD(String name, int i) {
    final upDateCommAndDes = Provider.of<UserDashboardProvider>(context);
    bool isSelected = upDateCommAndDes.intchangeColorInTaskDetail == i;

    return SizedBox(
      width: SizeConFig.screenWidth * 25 / 100,
      //color:
      //  Colors.pink,
      child: GestureDetector(
        onTap: () {
          upDateCommAndDes.changeColorInTaskDetail(i);
        },
        child: Text(
          name,
          style: TextStyle(
            fontSize: 15,
            fontWeight: FontWeight.w500,
            color: isSelected ? Colors.green : Colors.black,
          ),
        ),
      ),
    );
  }

  Widget customLineD(int i) {
    final upDateCommAndDes2 = Provider.of<UserDashboardProvider>(context);
    bool isSelected2 = upDateCommAndDes2.intchangeColorInTaskDetail == i;
    return Container(
      height: SizeConFig.screenHeight * 0.5 / 100,
      width: SizeConFig.screenWidth * 25 / 100,
      decoration: BoxDecoration(
        color: isSelected2 ? Colors.green : Colors.white,
      ),
    );
  }
}
