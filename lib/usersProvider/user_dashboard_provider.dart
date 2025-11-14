import 'package:flutter/material.dart';

class UserDashboardProvider extends ChangeNotifier {
  bool _changeColor = false;
  bool get changeColor => _changeColor;
  void chnageColorOnOff() {
    _changeColor = !_changeColor;
    notifyListeners();
  }

  int _currentIndex = 0;
 int get currentIndex => _currentIndex;

  void changePostion(int index) {
      print("🔁 Setting currentIndex = $index");
    _currentIndex = index;
    notifyListeners();
  }

  int _initailCount = 0;

  int get initailCount => _initailCount;
//this provider for project details screen
  void chanageListview(int update) {
    _initailCount = update;
    notifyListeners();
  }
  // this provider for task detaails screeen
  int _intchangeColorInTaskDetail=0;
  
  int get intchangeColorInTaskDetail=>_intchangeColorInTaskDetail;
   void changeColorInTaskDetail(int indexNumber)
   {
    _intchangeColorInTaskDetail=indexNumber;
    notifyListeners();
   }

}
