package com.company.Symbols;

public class SymbolThreadVertical extends Thread {
    SymbolWriter symbol;

    public SymbolThreadVertical(SymbolWriter symbol){
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            symbol.print("|");
        }
    }
}
