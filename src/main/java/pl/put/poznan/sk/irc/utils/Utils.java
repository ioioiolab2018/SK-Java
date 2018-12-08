package pl.put.poznan.sk.irc.utils;

public class Utils {
    public static String deleteInvalidCharcters(String val) {
        return val.replace("#","")
                .replace("$","")
                .replace(":","")
                .replace(";","");
    }
}
