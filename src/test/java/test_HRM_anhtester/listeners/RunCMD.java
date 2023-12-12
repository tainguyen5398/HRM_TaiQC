package test_HRM_anhtester.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCMD {

    public static void Allure(String FolderAllureResults) {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"C:\\automation-testing\\Ecommerce_Project\" && " +FolderAllureResults);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }
}
