package br.edu.utfpr.pb.labquimica.backend.utils;

public class StringUtils {
    public static String getNumbers(String string)
    {
        return string.replaceAll("\\D+","");
    }
}
