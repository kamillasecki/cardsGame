package com.klasecki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    Map<String, Integer> stats = new HashMap<>();

    void addMistake (String key) {
        stats.put(key, stats.getOrDefault(key, 0) + 1);
    }

    int getErrors (String key) {
        return stats.getOrDefault(key,0);
    }

    void printHardestQuestion (Log log) {
        if(stats.isEmpty()){
            log.outToLog("There are no cards with errors.");
            log.outToLog("");
        } else {
            int maxMistakes = 0;
            List<String> badKeys = new ArrayList<>();
            for(var v : stats.entrySet()) {
                if (maxMistakes < v.getValue()) {
                    badKeys.clear();
                    badKeys.add(v.getKey());
                    maxMistakes = v.getValue();
                } else if (maxMistakes == v.getValue()) {
                    badKeys.add(v.getKey());
                }
            }

            StringBuilder sb = new StringBuilder();
            if (badKeys.size() == 1) {
                sb.append("The hardest card is \"").append(badKeys.get(0)).append("\". ");
            } else {
                sb.append("The hardest cards are ");
                for(String s:badKeys) {
                    sb.append("\"").append(s).append("\", ");
                }
            }
            sb.append("You have ").append(maxMistakes).append(" errors answering them.");
            log.outToLog(sb.toString());
            log.outToLog("");
        }
    }

    void setError(String question, int errors) {
        stats.put(question, errors);
    }

    void resetStats(Log log) {
        stats.clear();
        log.outToLog("Card statistics has been reset.");
    }

    void remove(String toRemove) {
        stats.remove(toRemove);
    }
}
