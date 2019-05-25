package ru.job4j.exam;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SearchTest {
    private String pathToDirForSearch;
    private String pathToResultSearch;

    @Before
    public void createFilesForTesting() throws IOException {
        File dirTest = new File(System.getProperty("java.io.tmpdir") + "/dirForSearch");
        dirTest.mkdir();
        pathToDirForSearch = dirTest.getAbsolutePath();
        new File(pathToDirForSearch, "test.txt").createNewFile();
        new File(pathToDirForSearch, "test2.jpeg").createNewFile();
        new File(pathToDirForSearch, "test3.jpeg").createNewFile();
        pathToResultSearch = pathToDirForSearch + File.separator + "result.txt";
    }

    @Test
    public void whenFullMatchSearchThenResult() throws IOException {

        String[] paramsArray = new String[7];
        paramsArray[0] = "-d";
        paramsArray[1] = pathToDirForSearch;
        paramsArray[2] = "-n";
        paramsArray[3] = ".jpeg";
        paramsArray[4] = "-o";
        paramsArray[5] = pathToResultSearch;
        paramsArray[6] = "-m";
        Args params = new Args(paramsArray);

        SearchFile searchFile = new SearchFile(params);

        ArrayList<String> resultList = populateResultList(params);
        searchFile.search();

        List<String> expected = Arrays.asList(
                pathToDirForSearch+File.separator+"test2.jpeg", pathToDirForSearch+File.separator+"test3.jpeg"
        );
        assertThat(resultList, is(expected));
    }

    private ArrayList<String> populateResultList(Args params) {
        ArrayList<String> resultList = new ArrayList<>();
        System.out.println("result: " + params.directory() + File.separator + "result.txt");
        try (BufferedReader read = new BufferedReader(new FileReader(pathToResultSearch))) {
            read.lines().filter(line -> !line.equals("")).forEach(s -> resultList.add(s));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + params.directory());
            e.printStackTrace();
        }
        return resultList;
    }
}
