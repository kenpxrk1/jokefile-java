package jokejava;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private final static String testFilePath = "/Users/dimagrisin/Desktop/A";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(sc.nextLine());
        if (JokeFilesService.isCorrectPath(path)) {
            JokeFilesService.createJokeFiles(path);
            // delete joke files by:
            // JokeService.deleteJokeFiles(path);
        } else {
            System.out.println("Wrong path");
        }
    }
}


