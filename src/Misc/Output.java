package Misc;

public class Output {
    public static boolean error = true;
    public static boolean warn = true;
    public static boolean debug = true;
    public static boolean info = true;

    // permet de donner des messages plus facilement lisible
    public static void errorln(String message) {
        if (Output.error)
            System.out.println("\u001B[31mERROR: " + message + "\u001B[0m");
    }

    public static void warnln(String message) {
        if (Output.warn)
            System.out.println("\u001B[33mWARN: " + message + "\u001B[0m");
    }

    public static void debugln(String message) {
        if (Output.debug)
            System.out.println("\u001B[34mDEBUG: " + message + "\u001B[0m");
    }

    public static void infoln(String message) {
        if (Output.info)
            System.out.println(message);
    }
}
