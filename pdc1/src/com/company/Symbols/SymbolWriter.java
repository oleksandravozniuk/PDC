package com.company.Symbols;


public class SymbolWriter {
    private String lastSymbol;

    public synchronized void print(String symbol){
        if(lastSymbol!=null){
            while (lastSymbol.equals(symbol)) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
        }
        lastSymbol = symbol;
        System.out.print(lastSymbol);
        notify();
    }
}
