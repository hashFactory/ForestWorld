
public class Output {
    // permet de donner des messages plus facilement lisible
    public static void errorln(String message) {
        System.out.println("\u001B[31mERROR: " + message + "\u001B[0m");
    }

    public static void warnln(String message) {
        System.out.println("\u001B[33mWARN: " + message + "\u001B[0m");
    }

    public static void debugln(String message) {
        System.out.println("\u001B[34mDEBUG: " + message + "\u001B[0m");
    }

    public static void infoln(String message) {
        System.out.println(message);
    }
}
