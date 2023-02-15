package com.shujie.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author linshujie
 */
public class DirList {
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(args));
        File path = new File(".");
        String[] list;
        list = path.list(new DirFilter(""));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for (String dirItem :
                list) {
            System.out.println("dirItem = " + dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File file, String s) {
        return pattern.matcher(s).matches();
    }
}
