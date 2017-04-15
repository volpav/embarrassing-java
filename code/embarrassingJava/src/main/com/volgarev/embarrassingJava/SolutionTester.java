package com.volgarev.embarrassingJava;

import com.volgarev.embarrassingJava.leetcode.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class SolutionTester {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<SolutionCategory> categories = getSolutionCategories();

        for (int i = 0; i < categories.size(); i++) {
            SolutionCategory category = categories.get(i);

            System.out.println(String.format("(%d) %s:", (i + 1), category.name));

            if (category.solutions.size() == 0) {
                System.out.println("  (No solutions under this category.)");
            } else {
                for (int j = 0; j < category.solutions.size(); j++) {
                    System.out.format("  %-10s ", String.format("(%d.%d)", (i + 1), (j + 1)));
                    System.out.println(category.solutions.get(j).getSimpleName());
                }
            }
        }

        System.out.println();

        int categoryIndex = -1, solutionIndex = -1;
        boolean skipNextPrompt = false;

        while (true) {
            if (!skipNextPrompt) {
                System.out.print("Make your choice: ");
            } else {
                skipNextPrompt = false;
            }

            String choice = s.nextLine();

            if (choice == null || choice.isEmpty()) {
                skipNextPrompt = true;
            } else {
                if (choice.compareTo("-1") == 0) {
                    System.out.println("Exiting...");
                    break;
                }

                String[] parts = choice.split("\\.");

                if (parts.length >= 2) {
                    categoryIndex = parseInt(parts[0]) - 1;
                    solutionIndex = parseInt(parts[1]) - 1;
                }

                if (categoryIndex < 0 || categoryIndex >= categories.size() ||
                        solutionIndex < 0 || solutionIndex >= categories.get(categoryIndex).solutions.size()) {

                    System.out.println("Incorrect choice! Please use \"[category #].[solution #]\" format.");
                } else {
                    SolutionRunner runner = createRunnerFromClass(categories.get(categoryIndex).solutions.get(solutionIndex));

                    if (runner == null) {
                        System.out.println("This solution is not runnable! Maybe there's a \"Runner\" type that you should choose?");
                    } else {
                        System.out.println(String.format("Running \"%s\"...", categories.get(categoryIndex).solutions.get(solutionIndex).getSimpleName()));
                        System.out.println();

                        runner.run(s);

                        System.out.println();
                        System.out.println();

                        categoryIndex = -1;
                        solutionIndex = -1;
                    }
                }
            }
        }
    }

    private static int parseInt(String input) {
        int ret = -1;
        boolean isValidNumber = true;

        for (char ch : input.toCharArray()) {
            if (!Character.isDigit(ch)) {
                isValidNumber = false;
                break;
            }
        }

        if (isValidNumber) {
            ret = Integer.parseInt(input);
        }

        return ret;
    }

    private static SolutionRunner createRunnerFromClass(Class clazz) {
        return invokeIgnoreExceptions(() -> {
            return (SolutionRunner)clazz.getConstructor(new Class[] { }).newInstance();
        });
    }

    private static List<SolutionCategory> getSolutionCategories() {
        List<SolutionCategory> ret = new ArrayList<SolutionCategory>();

        ret.add(new SolutionCategory("codejam", getClassesForPackage("com.volgarev.embarrassingJava.codejam")));
        ret.add(new SolutionCategory("kattis", getClassesForPackage("com.volgarev.embarrassingJava.kattis")));
        ret.add(new SolutionCategory("leetcode", getClassesForPackage("com.volgarev.embarrassingJava.leetcode")));
        ret.add(new SolutionCategory("misc", getClassesForPackage("com.volgarev.embarrassingJava.misc")));

        return ret;
    }

    private static List<Class> getClassesForPackage(String packageName) {
        return invokeIgnoreExceptions(() -> {
            ArrayList<File> directories = new ArrayList<>();
            String packageToPath = packageName.replace('.', '/');
            ClassLoader cld = Thread.currentThread().getContextClassLoader();

            Enumeration<URL> resources = cld.getResources(packageToPath);

            while (resources.hasMoreElements()) {
                directories.add(new File(URLDecoder.decode(resources.nextElement().getPath(), "UTF-8")));
            }

            ArrayList<Class> classes = new ArrayList<>();

            while (!directories.isEmpty()){
                File directoryFile  = directories.remove(0);

                if (directoryFile.exists()) {
                    File[] files = directoryFile.listFiles();

                    if (files != null) {
                        for (File file : files) {
                            if ((file.getName().endsWith(".class")) && (!file.getName().contains("$"))) {
                                int index = directoryFile.getPath().indexOf(packageToPath);
                                String packagePrefix = directoryFile.getPath().substring(index).replace('/', '.');
                                String className = packagePrefix + '.' + file.getName().substring(0, file.getName().length() - 6);

                                Class clazz = Class.forName(className);

                                if (Modifier.isPublic(clazz.getModifiers()) && !Modifier.isAbstract(clazz.getModifiers())) {
                                    classes.add(Class.forName(className));
                                }
                            } else if (file.isDirectory()) {
                                directories.add(new File(file.getPath()));
                            }
                        }
                    }
                }
            }

            return classes;
        });
    }

    private static <T> T invokeIgnoreExceptions(StatementBlock<T> block) {
        try {
            return block.execute();
        } catch (Exception ex) { }

        return null;
    }
}

class SolutionCategory {
    public String name;
    public List<Class> solutions;

    public SolutionCategory(String name, List<Class> solutions) {
        this.name = name;
        this.solutions = solutions;
    }
}

interface StatementBlock<T> {
    T execute() throws Exception;
}
