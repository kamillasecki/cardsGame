package com.klasecki;

public class Main {
    public static void main(String[] args) {

        String importFile = "";
        String exportFile = "";

        if(args.length > 0) {
            for (int i=0 ; i<args.length ; i++) {
                if(args[i].equals("-import")) {
                    importFile = args[i+1];
                } else if (args[i].equals("-export")) {
                    exportFile = args[i+1];
                }
            }
        }
        Menu menu = new Menu(exportFile);

        if (!importFile.equals("")) {
            menu.cards.loadCards(menu.log, menu.stats, importFile);
        }

        menu.readMenu();
    }

}
