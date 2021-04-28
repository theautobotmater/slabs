package com.slab.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetByRegex {

    /**
     * Get list of extracted values using regex
     *
     * @param pattern pattern
     * @param text text
     * @return list of results
     */
    public List<String> getByRegex(String pattern, String text) {
        List<String> results = new ArrayList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        while(m.find()) {
            results.add(m.group(1));
        }
        return results;
    }

}
