package ru.job4j.io.archive;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ZipTest {
    @Test
    public void zipFileIsExist() {
        String[] paramsArray = new String[6];
        paramsArray[0] = "-d";
        paramsArray[1] = "D:\\temp";
        paramsArray[2] = "-e";
        paramsArray[3] = ".java";
        paramsArray[4] = "-o";
        paramsArray[5] = "D:\\temp\\project.zip";
        Args args = new Args(paramsArray);
        Zip zip = new Zip();
        zip.pack(args);
        assertThat(new File(args.output()).exists(), is(true));
    }
}
