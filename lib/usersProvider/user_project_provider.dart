import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;
import 'package:truenorthdemoproject/apiEndpoint/api_const.dart';
import 'package:truenorthdemoproject/usersModels/user_all_project.dart';
import 'package:truenorthdemoproject/utils/mesage_snack_bar.dart';


class UserProjectProvider extends ChangeNotifier {




  List<AllProject> _projects = [];
  bool _isLoading = false;

  List<AllProject> get projects => _projects;
    bool get isLoading => _isLoading;
  Future<void> userAllProject(BuildContext context) async {
    final url = Uri.parse(Apiconstants.userProjectById + "${1}");
    final headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + base64Encode(utf8.encode('admin:admin123')),
    };
    try {
      final response = await http
          .get(url, headers: headers)
          .timeout(const Duration(seconds: 30));
      if (response.statusCode == 200 || response.statusCode == 201) {
        // final List<dynamic> data = jsonDecode(response.body);
        // final user = data.length;

          final data = jsonDecode(response.body) as List;
        _projects = data.map((e) => AllProject.fromJson(e)).toList();
        
      }
    } on SocketException {
      ShowTaostMessage.toastMessage(context, "No Internet connection");
    } on TimeoutException {
      ShowTaostMessage.toastMessage(context, "Request timed out");
    } on FormatException {
      ShowTaostMessage.toastMessage(context, "Invalid response format");
    } catch (e) {
      debugPrint("Unexpected error: $e");
      ShowTaostMessage.toastMessage(context, "Something went wrong");
    }
  }
}
