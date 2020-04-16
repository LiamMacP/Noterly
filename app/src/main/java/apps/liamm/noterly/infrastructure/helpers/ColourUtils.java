package apps.liamm.noterly.infrastructure.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColourUtils {
    public static boolean IsHexString(String value) {
        if (StringUtils.IsNullOrEmpty(value)) {
            return false;
        }

        if (value.charAt(0) != '#') {
            return false;
        }

        if (value.length() > 7) {
            value = value.substring(0,7);
        }

        String colourString = value.substring(1);

        int colourStringLength = colourString.length();
        if (colourStringLength != 2 && colourStringLength != 3 && colourStringLength != 6) {
            return false;
        }

        Pattern hexPattern = Pattern.compile("[0-9A-Fa-f]{2,6}");
        Matcher hexMatcher = hexPattern.matcher(colourString);
        return hexMatcher.find();
    }
}
