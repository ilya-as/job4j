package ru.job4j.tdd;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.*;

import java.util.HashMap;

public class SimpleGeneratorTest {
    @Test
    public void whenUseAssertKeysThenShouldBeExpected() throws Exception {
        String template = "I am a ${name}, Who are ${subject}";
        String expected = "I am a Petr, Who are you";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Petr");
        hashMap.put("subject", "you");
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        assertThat(simpleGenerator.generate(template, hashMap), is(expected));
    }

    @Test(expected = KeyNotFoundException.class)
    public void whenUseUnknownKeysThenThrowException() throws Exception {
        String template = "I am a ${name}, Who are ${subject}";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name1", "Petr");
        hashMap.put("subject1", "you");
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        simpleGenerator.generate(template, hashMap);
    }

    @Test(expected = InvalidKeyException.class)
    public void whenMissingRequiredKeysInTemplateThenThrowException() throws Exception {
        String template = "I am a ${name}, Who are ${subject}";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Petr");
        hashMap.put("subject1", "you");
        hashMap.put("subject", "you");
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        simpleGenerator.generate(template, hashMap);
    }
}
