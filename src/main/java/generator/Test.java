package generator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String str ="BIZ_TASK_INFO";
        toTableXml(str);
//        toDataConfigXml(str);
//        System.out.println("-------");
//        managedSchema(str);
//        str = "t_lic_equipment_producted";
//        System.out.println(toCamelCase(str));
//        System.out.println(toCapitalizeCamelCase(str));
//        System.out.println(toUnderlineName(str));

    }

    private static final char SEPARATOR = '_';

    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            };

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static List<String> str2List(String str, String regex){
        if(str==null||str=="")
            return null;
        str = str.replaceAll(" ","").replaceAll("\t","").replaceAll("\n","");
        String[] arrayStr = str.split(regex);
        List<String> list = Arrays.asList(arrayStr);
        List arrayList = new ArrayList(list);
        return arrayList;
    }

    public static void toTableXml(String str) {
        List<String> list = str2List(str,",");
        for(String n:list){
            String s = n.toLowerCase();
            StringBuilder sb = new StringBuilder();
            boolean upperCase = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == SEPARATOR) {
                    upperCase = true;
                } else if (i==0 ||upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }
            System.out.println("<table  tableName=\""+n+"\"   domainObjectName=\""+sb.toString()+"\"/>");
        }

    }

    public static void toDataConfigXml(String str) {
        List<String> list = str2List(str,",");
        for(String n:list){
            String s = n.toLowerCase();
            StringBuilder sb = new StringBuilder();
            boolean upperCase = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == SEPARATOR) {
                    upperCase = true;
                } else if (i==0 ||upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }
            System.out.println("<field column=\""+n+"\"   name=\""+sb.substring(0, 1).toLowerCase() + sb.substring(1)+"\"/>");
        }

    }

    public static void managedSchema(String str) {
        List<String> list = str2List(str,",");
        for(String n:list){
            String s = n.toLowerCase();
            StringBuilder sb = new StringBuilder();
            boolean upperCase = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == SEPARATOR) {
                    upperCase = true;
                } else if (i==0 ||upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }
            System.out.println("<field name=\""+sb.substring(0, 1).toLowerCase() + sb.substring(1)+"\" " +
                    "type=\"string\"  indexed=\"true\" stored=\"false\" " +
                    "/>");
        }

    }
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
