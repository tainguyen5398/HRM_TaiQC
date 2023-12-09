package Main_HRM_AnhTester.Helpers;

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

