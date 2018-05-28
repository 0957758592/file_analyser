package com.danilov.fileanalyser;

import java.io.*;
import java.nio.file.InvalidPathException;

public class FileAnalyserIntegrationTest {
    public static void main(String[] args) throws IOException {
        String path = "I:/txt.txt";
        String word = "duck";
//        String path = args[0];
//        String word = args[1];
        analyseFile(path, word);
    }

    private static void analyseFile(String path, String word) throws IOException {
        File pathToFile = new File(path);

        if (!pathToFile.exists()) {
            throw new InvalidPathException(path, "no such Path in Directory");
        } else {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader
                    (new FileInputStream(pathToFile), "UTF-8"))) {

                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                printConcurrency(stringBuilder, word);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printConcurrency(StringBuilder line, String word) {
        int count = 0;

        for (String sentence : line.toString().split("(?<=[.!?])\\s*")) {
            if (sentence.contains(word)) {
                count = line.toString().split(word).length - 1;
                System.out.println(sentence);
            }
        }
        System.out.println(count);
    }
}