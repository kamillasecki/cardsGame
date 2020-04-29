package com.klasecki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Cards {
    Map<String, String> cardsMap = new HashMap<>();

    public void askQuestion(Log log, Statistics stats) {
        Random r = new Random();
        boolean answerToAnother = false;
        log.outToLog("How many times to ask?");
        int rounds = Integer.parseInt(log.inToLog());
        if(cardsMap.isEmpty()) {
            log.outToLog("You don't have any cards.");
        } else {
            for (int i=0; i<rounds; i++) {
                int number = r.nextInt(cardsMap.size());
                Set<String> keys = cardsMap.keySet();
                String [] arr = keys.toArray(new String[keys.size()]);
                String randomKey = arr[number];
                log.outToLog("Print the definition of \"" + randomKey + "\":");
                String userAnswer = log.inToLog();
                if (cardsMap.get(randomKey).equals(userAnswer)) {
                    log.outToLog("Correct answer");
                } else {
                    stats.addMistake(randomKey);
                    for (var v : cardsMap.entrySet()) {
                        if (userAnswer.equals(v.getValue())) {
                            answerToAnother = true;
                            log.outToLog("Wrong answer. The correct one is \"" + cardsMap.get(randomKey) + "\", you've just written the definition of \"" + v.getKey() + "\".");
                            break;
                        }
                    }
                    if (!answerToAnother) {
                        log.outToLog("Wrong answer. The correct one is \"" + cardsMap.get(randomKey) + "\".");
                    }
                }
            }
        }
        log.outToLog("");
    }

    void saveCards(Log log, Statistics stats, String fileName) {
        File file;

        if(fileName.equals("")) {
            log.outToLog("File name:");
            file = new File("./" + log.inToLog());
        } else {
            file = new File("./" + fileName);
        }

        int count = 0;

        try (PrintWriter pw = new PrintWriter(file)) {
            for (var v : cardsMap.entrySet()) {
                count++;
                pw.println(v.getKey());
                pw.println(v.getValue());
                pw.println(stats.getErrors(v.getKey()));
            }
        } catch (IOException e) {
            log.outToLog("IO error");
        }
        log.outToLog(count + " cards have been saved.");
        log.outToLog("");
    }

    void loadCards(Log log, Statistics stats, String fileName) {
        File file;
        if (fileName.equals("")) {
            log.outToLog("File name:");
            file = new File("./" + log.inToLog());
        } else {
            file = new File("./" + fileName);
        }

        int count = 0;


        try (Scanner read = new Scanner(file)) {
            while (read.hasNext()) {
                count++;
                String question = read.nextLine();
                String answer = read.nextLine();
                int errors = Integer.parseInt(read.nextLine());
                cardsMap.put(question, answer);
                if(errors !=0 ) {
                    stats.setError(question, errors);
                }

            }
            log.outToLog(count + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            log.outToLog("File not found.");
        }
        log.outToLog("");
    }

    void removeCard(Log log, Statistics stats) {
        log.outToLog("The card:");
        String toRemove = log.inToLog();
        if(cardsMap.containsKey(toRemove)) {
            cardsMap.remove(toRemove);
            stats.remove(toRemove);
            log.outToLog("The card has been removed.");
        } else {
            log.outToLog("Can't remove \"" + toRemove + "\": there is no such card.");
        }
        log.outToLog("");
    }

    public  void addCard(Log log) {
        log.outToLog("The card:");
        String q = log.inToLog();
        boolean duplicate = false;
        if (cardsMap.containsKey(q)) {
            log.outToLog("The card \"" + q + "\" already exists.");
        } else {
            log.outToLog("The definition of the card:");
            String a = log.inToLog();
            for (var v : cardsMap.entrySet()) {
                if (v.getValue().equals(a)) {
                    log.outToLog("The definition \"" + a + "\" already exists.");
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                cardsMap.put(q, a);
                log.outToLog("The pair (\"" + q + "\":\"" + a + "\") has been added.");
            }
        }
        log.outToLog("");
    }
}
