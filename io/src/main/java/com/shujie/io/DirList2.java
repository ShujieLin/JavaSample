package com.shujie.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author linshujie
 */
public class DirList2 {
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(args));
        File path = new File(".");
        String[] list;
        list = path.list(filter(""));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem :
                list) {
            System.out.println("dirItem = " + dirItem);
        }
    }

    private static FilenameFilter filter(final String s) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(s);

            @Override
            public boolean accept(File file, String s) {
                return pattern.matcher(s).matches();
            }
        };
    }
}