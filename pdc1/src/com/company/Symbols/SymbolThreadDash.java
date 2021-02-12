package com.company.Symbols;

public class SymbolThreadDash extends Thread {
    SymbolWriter symbol;

    public SymbolThreadDash(SymbolWriter symbol){
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            symbol.print("-");
        }
    }
}
