package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<String> listPhrases = readPhrases();
        List<String> logList = new ArrayList<>();
        boolean flagPause = false;
        while (!OUT.equals(str)) {
            logList.add("USER : " + str);
            if (STOP.equals(str)) {
                flagPause = true;
            } else if (CONTINUE.equals(str)) {
                flagPause = false;
            }
            if (!flagPause) {
                String botAnswer = getBotAnswers(listPhrases);
                logList.add("BOT : " + botAnswer);
                System.out.println("BOT : " + botAnswer);
            }
            str = scanner.nextLine();
        }
        logList.add("USER : " + str);
        saveLog(logList);
    }

    private String getBotAnswers(List<String> listPhrasBot) {
        return listPhrasBot.get((int) (Math.random() * listPhrasBot.size()));
    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            listPhrases = bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("data/logChat.txt", "data/botAnswers.txt");
        chat.run();
    }
}
