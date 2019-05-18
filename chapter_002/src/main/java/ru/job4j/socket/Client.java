package ru.job4j.socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void startClient() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String question;
        String answer;
        do {
            question = console.nextLine();
            out.println(question);
            while (!(answer = in.readLine()).isEmpty()) {
                System.out.println(answer);
            }
        } while (!"exit".equals(question));
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5555);
        Client client = new Client(socket);
        client.startClient();
    }
}
