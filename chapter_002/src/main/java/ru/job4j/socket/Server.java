package ru.job4j.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final Socket socket;
    private ArrayList<String> AnswerList = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
        populateAnswerList();
    }

    private void populateAnswerList() {
        AnswerList.add("answer#1");
        AnswerList.add("answer#2");
        AnswerList.add("answer#3");
        AnswerList.add("answer#4");
    }

    public void startServer() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if ("exit".equals(ask)) {
                out.println("Good bay!");
                out.println();
            } else {
                out.println(getAnswer());
                out.println();
            }
        }
        while (!"exit".equals(ask));
    }

    private String getAnswer() {
        String result;
        result = AnswerList.get(randomIndex());
        return result;
    }

    private int randomIndex() {
        int max = AnswerList.size();
        return (int) (Math.random() * max);
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new ServerSocket(5555).accept();
        Server server = new Server(socket);
        server.startServer();
    }
}
