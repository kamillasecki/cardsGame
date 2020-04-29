package com.klasecki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Log {
    List<String> log = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<String> getLog() {
        return log;
    }

    public void outToLog(String text) {
        System.out.println(text);
        this.log.add(text);
    }

    public String inToLog() {
        String s = sc.nextLine();
        this.log.add("> " + s);
        return s;
    }

    void logToFile() {
        outToLog("File name:");
        File file = new File("./" + inToLog());
        try (PrintWriter pw = new PrintWriter(file)) {
            for (String s : getLog()) {
                pw.println(s);
            }
            outToLog("The log has been saved.");
        } catch (FileNotFoundException e) {
            outToLog("File not found.");
        }
        outToLog("");
    }
}
