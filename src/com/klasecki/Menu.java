package com.klasecki;

public class Menu {
    Cards cards = new Cards();
    Log log = new Log();
    Statistics stats = new Statistics();
    String exportFile;


    public Menu(String exportFile) {
        this.exportFile = exportFile;
    }

    public void readMenu() {
        log.outToLog("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");

        switch (log.inToLog()) {
            case "add": cards.addCard(log);
                readMenu();
                break;
            case "remove" : cards.removeCard(log, stats);
                readMenu();
                break;
            case "import" : cards.loadCards(log, stats, "");
                readMenu();
                break;
            case "export" : cards.saveCards(log, stats, "");
                readMenu();
                break;
            case "ask" : cards.askQuestion(log, stats);
                readMenu();
                break;
            case "log" : log.logToFile();
                readMenu();
                break;
            case "hardest card" : stats.printHardestQuestion(log);
                readMenu();
                break;
            case "reset stats" : stats.resetStats(log);
                readMenu();
                break;
            case "exit" : exit(log);
                if(!exportFile.equals("")) cards.saveCards(log, stats, exportFile);
                break;
            default :
                log.outToLog("Unrecognised command.");
                readMenu();
                break;
        }
    }

    private static void exit(Log log) {
        log.outToLog("Bye bye!");
    }
}
