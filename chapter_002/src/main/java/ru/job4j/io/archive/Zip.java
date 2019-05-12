package ru.job4j.io.archive;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивировать проект.
 * 1. Задана директория проекта, например: c:\project\job4j\
 * 2. В качестве ключей передаются расширения файлов, которые должны попасть в архив.
 * 3. Архив должен сохранять структуру проекта.
 * 4. Запуск проекта java -jar pack.jar -d c:\project\job4j\ -e java.xml -o project.zip
 * -d - directory - которую мы ходим архивировать
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 */

public class Zip {

    private List<File> fileList = new LinkedList<>();

    private void populateFilesList(File file, String ext) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                this.populateFilesList(child, ext);
            }
        } else {
            if (file.getName().endsWith(ext)) {
                fileList.add(file);
            }
        }
    }

    public void pack(Args args) {
        File inputDir = new File(args.directory());
        File target = new File(args.output());
        populateFilesList(inputDir, args.exclude());
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : fileList) {
                zip.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(args.directory().length() + 1)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Args params = new Args(args);
        Zip zip = new Zip();
        zip.pack(params);
    }
}
