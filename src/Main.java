import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public final class Main {

    final static String Version = "0.0.1";

    final static String fail = "URLreader failed";

    static String os;



    // ---------------

    public static void main(String[] args) {

        System.out.println("Necro Mods folder installer");

        version(); // Check the Installer's version

        manageOS(); // Check the System's OS

        decide(); // Run the proper installer based on the OS.





    }

    // ---------------

    static void MacOS () {

        Process process;


        // Creating a File object
        File file = new File(System.getenv("HOME") + "/Library/Application Support/minecraft/mods");

        // Deleting the directory
        boolean bl = file.delete();
        if (bl) {
            System.out.println("Deleted the mods directory");
        } else {
            System.out.println("Could not delete the the mods directory, it might not exist.");
        }



        System.out.println("Cloning the mods repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/mods.git", null, new File (System.getenv("HOME") + "/Library/Application Support/minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("Cloned the mods repository.");




    }

    // ---------------

    static String check (String url) {
        String r;
        try {
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            StringBuilder e = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                e.append(inputLine).append("\n");

            in.close();
            r = e.toString();
        } catch (Exception ignored) {
            r = fail;
        }
        return r;
    }

    static void version () {

        Scanner wait = new Scanner(System.in);

        String latestVersion = check("https://raw.githubusercontent.com/NecroClient/api/main/Installer.yml").replace("\n", "");

        if (latestVersion.equals(fail)) {
            System.out.println("Cannot connect to the Necro Client API.\nMake sure you have actual internet.\nOr, you might just have an old version. Download a new version here: https://github.com/NecroClient/Installer/releases");
            wait.nextLine();
            System.exit(0);
        }
        if (!latestVersion.equals(Version)) {
            System.out.println("You're on an old version of MessageEngine Installer.\nDownload a new version here: https://github.com/NecroClient/Installer/releases");
            wait.nextLine();
            System.exit(0);
        } else {
            System.out.println("Version check: Latest!");
        }

    }

    static void manageOS () {
        os = System.getProperty("os.name");
        System.out.println("OS: " + os);
    }

    static void decide () {

        if (os.contains("Mac OS") || os.contains("MacOS")) {
            MacOS();
        } else if (os.contains("Windows")) {
            // Windows();
        } else if (os.contains("Linux") || os.contains("Ubuntu")) {
            // Linux();
        } else {
            System.out.println("\nYour operating System, [" + os + "], is not supported.");
            System.out.print("This might be caused by an old version of this installer. \nYou can check for a newer version here: ");
            System.out.println("https://github.com/afkvido-development/MessageEngineInstaller-Java/releases/latest");
            System.out.print("If you're already on the latest version, please file a bug report here: ");
            System.out.println("https://github.com/afkvido-development/MessageEngineInstaller-Java/issues\n");
            Scanner wait = new Scanner(System.in);
            wait.nextLine();
            System.exit(0);
        }

    }

    static void printResults (Process process) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception ignored) {}
    }



}
