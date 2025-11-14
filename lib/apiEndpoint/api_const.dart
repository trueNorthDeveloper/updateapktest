class Apiconstants {
//   static const String baseUrl ="https://fe5f-106-222-214-87.ngrok-free.app/auth/api";
  static const String baseUrl = "https://602ff21e2fd6.ngrok-free.app/auth/api";
  static  String baseUrlApi="";
  static String loginNgrok="$baseUrlApi/auth/api/login";

  // static const String imageBaseUrl="https://fe5f-106-222-214-87.ngrok-free.app/api";
  static const String imageBaseUrl = "https://602ff21e2fd6.ngrok-free.app/api";
  // ignore: unnecessary_string_interpolations
  static const String login = "$baseUrl/login";
  static const String logout = "$baseUrl/logout";
  static const String register = "$baseUrl/register";
  static const String upload = "$imageBaseUrl/v1/image/driveUpload";
  static const String updateImageId = "$imageBaseUrl/v1/image/updatelinkId";
  static const String userProfile = "$baseUrl/";
  static const String userLoginInfo = "$baseUrl/userLoginData/";
  static const String checkUserIdPass = "$baseUrl/checkAccountWithSession";
  //here all admin api...........................start..........................
  static const String adminBaseUrl ="https://602ff21e2fd6.ngrok-free.app/auth/adminApi";

  static const String adminlog = "$adminBaseUrl/adminLogin";
  static const String fatchAllUser = "$adminBaseUrl/allUser";
  //-----------------collection of user project api...........
  static const String  userProjectById = "$adminBaseUrl/projects/";




}
