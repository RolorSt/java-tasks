package ru.mail.polis.homework.analyzer;

public abstract class Analyzer {
    protected boolean analyzeText(String txt, String[] examples) {
        for (String s : examples) {
            if (txt.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
