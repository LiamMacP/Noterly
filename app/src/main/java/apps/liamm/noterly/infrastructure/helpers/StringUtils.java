package apps.liamm.noterly.infrastructure.helpers;

public class StringUtils {

    public static final String EMPTY = "";

    public static boolean IsNullOrEmpty(String value) {
        return value == null || value.equals(EMPTY);
    }

}
