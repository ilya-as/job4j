package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private static final String LN = System.lineSeparator();

    @Test
    public void whenAskHelloAndExit() throws IOException {
        testClient(
                Joiner.on(LN).join("Hi", "exit"),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.",  "","Good bay!"),
                Joiner.on(LN).join("Hello, dear friend, I am Oracle.", "","Good bay!"));
    }

    private void testClient(String consoleInput, String serverInput, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(consoleInputStream);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Client clientOracle = new Client(socket);
        clientOracle.startClient();
        assertThat(consoleOutputStream.toString(), is(expected));
    }
}
