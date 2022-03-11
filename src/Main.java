import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;


public final class Main {

    /** Version of Installer */
    final static String Version = "0.2.0";

    /** Result if URLreader failed. */
    final static String fail = "URLreader failed";

    /** Operating system. */
    static String os;



    // ---------------

    /** What is run. */
    public static void main(String[] args) {

        System.out.println("[NecroInstaller] Necro Mods folder installer");

        version(); // Check the Installer's version.

        manageOS(); // Check the System's OS.

        decide(); // Run the proper installer based on the OS.



    }

    // ---------------

    /** MacOS file system installer. */
    static void MacOS () {

        // New command line process
        Process process;


        // Mods backups directory path
        File backupDir = new File(System.getenv("HOME") + "/Library/Application Support/minecraft/NecroBackups");

        // Create the backup directory
        if (backupDir.mkdir()) {
            System.out.println("[NecroInstaller] Created the NecroBackups directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't create the NecroBackups directory, it probably already exists.");
        }

        // -------------------------------


        // Mods directory path
        File modsDir = new File(System.getenv("HOME") + "/Library/Application Support/minecraft/mods");

        // New random
        Random r = new Random();

        // Random number, from 1 to 999
        String random = String.valueOf(r.nextInt(1, 999));

        // Move the directory
        boolean mdMoved = modsDir.renameTo(new File(backupDir + "/mods_" + random));
        if (mdMoved) {
            System.out.println("[NecroInstaller] Moved the mods directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the mods directory");
        }

        // -------------------------------


        // Config directory path
        File configDir = new File(System.getenv("HOME") + "/Library/Application Support/minecraft/config");

        // Moving the config directory
        boolean cdMoved =  configDir.renameTo(new File(backupDir + "/config_" + random));
        if (cdMoved) {
            System.out.println("[NecroInstaller] Moved the config directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the config directory");
        }

        // -------------------------------


        System.out.println("[NecroInstaller] Cloning the mods repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/mods.git", null, new File (System.getenv("HOME") + "/Library/Application Support/minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the mods repository.");



        System.out.println("[NecroInstaller] Cloning the config repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/config.git", null, new File (System.getenv("HOME") + "/Library/Application Support/minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the config repository.");



        System.out.println("[NecroInstaller] Your mods folder backup number: " + random);

        System.out.println("[NecroInstaller] Done!");


    }

    /** Windows file system installer. */
    static void Windows () {

        // New command line process
        Process process;


        // Mods backups directory path
        File backupDir = new File(System.getenv("APPDATA") + "\\.minecraft\\NecroBackups");

        // Create the backup directory
        if (backupDir.mkdir()) {
            System.out.println("[NecroInstaller] Created the NecroBackups directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't create the NecroBackups directory, it probably already exists.");
        }

        // -------------------------------


        // Mods directory path
        File modsDir = new File(System.getenv("APPDATA") + "\\.minecraft\\mods");

        // New random
        Random r = new Random();

        // Random number, from 1 to 999
        String random = String.valueOf(r.nextInt(1, 999));

        // Move the directory
        boolean mdMoved = modsDir.renameTo(new File(backupDir + "\\mods_" + random));
        if (mdMoved) {
            System.out.println("[NecroInstaller] Moved the mods directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the mods directory");
        }

        // -------------------------------


        // Config directory path
        File configDir = new File(System.getenv("APPDATA") + "\\.minecraft\\config");

        // Deleting all the files in the config directory
        boolean cdMoved =  configDir.renameTo(new File( backupDir + "\\config_" + random));
        if (cdMoved) {
            System.out.println("[NecroInstaller] Moved the config directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the config directory");
        }

        // -------------------------------



        System.out.println("[NecroInstaller] Cloning the mods repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/mods.git", null, new File (System.getenv("APPDATA") + "\\.minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the mods repository.");



        System.out.println("[NecroInstaller] Cloning the config repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/config.git", null, new File (System.getenv("APPDATA") + "\\.minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the config repository.");



        System.out.println("[NecroInstaller] Your mods folder backup number: " + random);

        System.out.println("[NecroInstaller] Done!");


    }

    /** Linux file system installer. */
    static void Linux () {

        // New command line process
        Process process;


        // Mods backups directory path
        File backupDir = new File(System.getenv("HOME") + "/.minecraft/NecroBackups");

        // Create the backup directory
        if (backupDir.mkdir()) {
            System.out.println("[NecroInstaller] Created the NecroBackups directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't create the NecroBackups directory, it probably already exists.");
        }

        // -------------------------------


        // Mods directory path
        File modsDir = new File(System.getenv("HOME") + "/.minecraft/mods");

        // New random
        Random r = new Random();

        // Random number, from 1 to 999
        String random = String.valueOf(r.nextInt(1, 999));

        // Move the directory
        boolean mdMoved = modsDir.renameTo(new File(backupDir + "/mods_" + random));
        if (mdMoved) {
            System.out.println("[NecroInstaller] Moved the mods directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the mods directory");
        }

        // -------------------------------


        // Config directory
        File configDir = new File(System.getenv("HOME") + "/.minecraft/config");

        // Deleting all the files in the config directory
        boolean cdMoved =  configDir.renameTo(new File( backupDir + "/config_" + random));
        if (cdMoved) {
            System.out.println("[NecroInstaller] Moved the config directory");
        } else {
            System.out.println("[NecroInstaller] Couldn't move the config directory");
        }

        // -------------------------------



        System.out.println("[NecroInstaller] Cloning the mods repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/mods.git", null, new File (System.getenv("HOME") + "/.minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the mods repository.");



        System.out.println("[NecroInstaller] Cloning the config repository...");
        try {

            process = Runtime.getRuntime().exec("git clone https://github.com/NecroClient/config.git", null, new File (System.getenv("HOME") + "/.minecraft"));

            printResults(process);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("[NecroInstaller] Cloned the config repository.");




        System.out.println("[NecroInstaller] Your mods folder backup number: " + random);

        System.out.println("[NecroInstaller] Done!");


    }

    // ---------------

    /** Read a URL. */
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

    /** Enforce the version of the installer. */
    static void version () {

        Scanner wait = new Scanner(System.in);

        String latestVersion = check("https://raw.githubusercontent.com/NecroClient/api/main/Installer.yml").replace("\n", "");

        System.out.println("[NecroInstaller] Current version: " + Version);
        System.out.println("[NecroInstaller] Latest version: " + latestVersion);

        if (latestVersion.equals(fail)) {
            System.out.println("[NecroInstaller] Cannot connect to the Necro Client API.\nMake sure you have actual internet.\nOr, you might just have an old version. Download a new version here: https://github.com/NecroClient/Installer/releases");
            wait.nextLine();
            System.exit(0);
        }
        if (!latestVersion.equals(Version)) {
            System.out.println("[NecroInstaller] You're on an old version of Necro Client Installer.\nDownload a new version here: https://github.com/NecroClient/Installer/releases");
            wait.nextLine();
            System.exit(0);
        } else {
            System.out.println("[NecroInstaller] Version check: Latest!");
        }

    }

    /** Check the OS. */
    static void manageOS () {
        os = System.getProperty("os.name");
        System.out.println("[NecroInstaller] OS: " + os);
    }

    /** Run the proper installer based on the operating system. */
    static void decide () {

        if (os.contains("Mac OS") || os.contains("MacOS")) {
            MacOS();
        } else if (os.contains("Windows")) {
            Windows();
        } else if (os.contains("Linux") || os.contains("Ubuntu")) {
            Linux();
        } else {
            System.out.println("\n[NecroInstaller] Your operating System, [" + os + "], is not supported.");
            System.out.print("[NecroInstaller] This might be caused by an old version of this installer. \n[NecroInstaller] You can check for a newer version here: ");
            System.out.println("https://github.com/NecroClient/Installer/releases/latest");
            System.out.print("[NecroInstaller] If you're already on the latest version, please file a bug report here: ");
            System.out.println("https://github.com/NecroClient/Installer/issues\n");
            Scanner wait = new Scanner(System.in);
            wait.nextLine();
            System.exit(0);
        }

    }

    /** Print the results of the command line process. */
    static void printResults (Process process) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception ignored) {}
    }

    /** Utility class <b>Main</b> cannot be initialized. */
    private Main () {

    }

}
