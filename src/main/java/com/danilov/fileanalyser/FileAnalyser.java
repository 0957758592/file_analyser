package com.danilov.fileanalyser;

import java.io.*;
import java.nio.file.InvalidPathException;

public class FileAnalyser {

    public static void main(String[] args) throws IOException {
        String path = "I:/txt.txt";
        String word = "duck";

        for (int i = 0; i <args.length ; i++) {
            System.out.println(args[i]);
        }
        analyseFile(path, word);
    }

    private static void analyseFile(String path, String word) throws IOException {
        File pathToFile = new File(path);
        int count = 0;

        if (!pathToFile.exists()) {
            throw new InvalidPathException(path, "no such Path in Directory");
        } else {

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"));

                String line;
                while ((line = reader.readLine()) != null) {

                    for (String sentence : line.split("(?<=[.!?])\\s*")) {
                        if (sentence.contains(word)){
                            count += line.split(word).length-1;
                            System.out.println(sentence);
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
        }
        System.out.println(count);
    }
}