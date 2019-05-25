package ru.job4j.exam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
    private List<Path> foundFiles = new ArrayList<>();
    private Args args;
    private PathMatcher matcher;

    public SearchFile(Args args) {
        this.args = args;
        if (args.searchByRegular()) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + args.name());
        }
    }

    public void search() throws IOException {
        Files.walkFileTree(Paths.get(args.directory()), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes fileAttributes) {
                String fileName = file.getFileName().toString();
                if (args.fullMatchSearch()) {
                    if (fileName.equals(args.name())) {
                        foundFiles.add(file);
                    }
                } else if (args.searchByMask()) {
                    if (fileName.endsWith(args.name())) {
                        foundFiles.add(file);
                    }
                } else if (args.searchByRegular()) {
                    if (matcher.matches(file)) {
                        foundFiles.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        writeResult();
    }

    private void writeResult() throws IOException {
        File resultLog = new File(args.output());
        try (FileWriter writer = new FileWriter(resultLog, false)) {
            for (Path file : foundFiles) {
                writer.write(file.toAbsolutePath().toString() + "\r\n");
            }
            writer.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        Args params = new Args(args);
        SearchFile searchFile = new SearchFile(params);
        searchFile.search();
    }
}
