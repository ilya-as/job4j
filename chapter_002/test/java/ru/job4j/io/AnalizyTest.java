package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Test
    public void whenCheckConfig() throws IOException {
        Analizy analizy = new Analizy();
        File serverLogFile = new File("src/main/resources/server.log");
        File unavailableFile = new File("src/main/resources/unavailable.csv");
        String serverLogPath = serverLogFile.getAbsolutePath();
        String unavailablePath = unavailableFile.getAbsolutePath();
        analizy.unavailable(serverLogPath, unavailablePath);
        try (BufferedReader read = new BufferedReader(new FileReader(unavailableFile))) {
            assertThat(read.readLine(), is("10:58:01;10:59:01"));
            assertThat(read.readLine(), is("11:01:02;11:02:02"));
        }
    }
}
