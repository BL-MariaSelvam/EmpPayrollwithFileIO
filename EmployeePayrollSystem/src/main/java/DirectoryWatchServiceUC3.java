package main.java;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryWatchServiceUC3 {

    private static final Map<WatchKey, Path> watchKeyMap = new HashMap<>();
    private static WatchService watchService;

    public static void main(String[] args) throws IOException, InterruptedException {

        Path dirToWatch = Paths.get("C:\\Users\\sathi\\OneDrive\\Desktop\\selva\\bridgelabz\\FileIo"); // change path as needed

        watchService = FileSystems.getDefault().newWatchService();

        // Register directory and sub-directories
        registerAllDirectories(dirToWatch);

        System.out.println("Watching directory: " + dirToWatch);

        // Event processing loop
        while (true) {
            WatchKey key = watchService.take();
            Path dir = watchKeyMap.get(key);

            for (WatchEvent<?> event : key.pollEvents()) {

                WatchEvent.Kind<?> kind = event.kind();
                Path fileName = (Path) event.context();
                Path fullPath = dir.resolve(fileName);

                if (kind == ENTRY_CREATE) {
                    System.out.println("Created: " + fullPath);

                    if (Files.isDirectory(fullPath)) {
                        registerAllDirectories(fullPath);
                    } else {
                        countFileEntries(fullPath);
                    }
                }

                if (kind == ENTRY_MODIFY && Files.isRegularFile(fullPath)) {
                    System.out.println("Modified: " + fullPath);
                    countFileEntries(fullPath);
                }

                if (kind == ENTRY_DELETE) {
                    System.out.println("Deleted: " + fullPath);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                watchKeyMap.remove(key);
                if (watchKeyMap.isEmpty()) {
                    break;
                }
            }
        }
    }

    // Register directory and all sub-directories
    private static void registerAllDirectories(Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
        watchKeyMap.put(key, dir);
    }

    // Count number of entries (lines) in file
    private static void countFileEntries(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            long count = lines.count();
            System.out.println("Entries in file (" + file.getFileName() + "): " + count);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file);
        }
    }
}
