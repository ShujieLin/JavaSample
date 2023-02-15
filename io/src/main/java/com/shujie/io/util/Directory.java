package com.shujie.io.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author linshujie
 */
public class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File file, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        private List<File> files = new ArrayList<>();
        private List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            files.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs : " + PPrint.pformat(dirs) +
            "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(String start,String regex){
        return recurseDirs(new File(start),regex);
    }

    public static TreeInfo walk(File start,String regex){
        return recurseDirs(start,regex);
    }

    public static TreeInfo walk(File start){
        return recurseDirs(start,".*");
    }

    public static TreeInfo walk(String start){
        return recurseDirs(new File(start),".*");
    }


    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item :
                Objects.requireNonNull(startDir.listFiles(),"startDir.listFiles() is null!")) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else {
                if (item.getName().matches(regex)) result.files.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
      /*  File file = new File(".");
        System.out.println("file = " + file);
        File[] files = file.listFiles();
        for (File f :
                files) {
            System.out.println("f = " + f);
        }*/


        /*System.out.println(walk("."));*/

        //所有目录
        PPrint.pprint(Directory.walk(".").dirs);

        //T开头的文件
        for (File file :
                Directory.local(".", "T.*")) {
            System.out.println(file);
        }
        System.out.print("-------------------------");

        //T开头的java文件
        for (File file :
                Directory.walk(".", "T.*\\.java")) {
            System.out.println(file);
        }
        System.out.print("-------------------------");

    }
}
