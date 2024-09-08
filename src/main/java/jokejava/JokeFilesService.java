package jokejava;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JokeFilesService {
    private final static String jokeFileCode = "public class joke {\n" + "    public static void main(String[] args) {\n" + "        System.out.println(\"Hello world!\");\n" + "    }\n" + "}";

    public static boolean isCorrectPath(Path path) {
        return Files.exists(path) && path.isAbsolute();
    }

    public static void createJokeFiles(Path absolutePath) {
        if (Files.isDirectory(absolutePath)) {
            try {
                Path file = Paths.get(String.valueOf(absolutePath), "joke.java");
                if (!Files.exists(file)) {
                    Files.createFile(file);
                    System.out.println("File created in directory: " + absolutePath);
                    writeCodeIntoJokeFile(file);
                    System.out.println("File changed in directory: " + absolutePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(absolutePath)) {
            for (Path dir : stream) {
                if (Files.isDirectory(dir)) {
                    createJokeFiles(dir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJokeFiles(Path path) {
        if (Files.isDirectory(path)) {
            try {
                Path file = Paths.get(String.valueOf(path), "joke.java");
                Files.deleteIfExists(file);
                System.out.println("File deleted in directory: " + path.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path dir : stream) {
                if (Files.isDirectory(dir)) {
                    deleteJokeFiles(dir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCodeIntoJokeFile(Path absolutePath) {
        try (Writer writer = new FileWriter(String.valueOf(absolutePath))) {
            writer.write(jokeFileCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
