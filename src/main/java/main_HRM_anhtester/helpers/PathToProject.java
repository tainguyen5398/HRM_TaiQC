package main_HRM_anhtester.helpers;

public class PathToProject {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + "/";
        return current;
    }

   public static String relPropertiesFilePathDefault(){
        String Path = "src/main/java/Main_HRM_AnhTester/DataTest/Properties/Data.properties";
        return Path;
    }
}

