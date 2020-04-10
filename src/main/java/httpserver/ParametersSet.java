package httpserver;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersSet {
    private final Map<String, String> keyValuePair;

    ParametersSet() {
        keyValuePair = new HashMap<String, String>();
    }

    void parseParameters(String parameterSet) throws UnsupportedEncodingException {
        parameterSet = URLDecoder.decode(parameterSet, "UTF-8");
        Pattern pattern = Pattern.compile("([^&?]*?)=([^&?]*)");
        Matcher matcher = pattern.matcher(parameterSet);

        while(matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            keyValuePair.put(key, value);
        }
    }

    public String getValue(String key) {
        if(!hasKey(key))
            return null;
        return keyValuePair.get(key);
    }

    public boolean hasKey(String key) {
        return keyValuePair.containsKey(key);
    }
}
