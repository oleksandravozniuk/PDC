package com.company.Symbols;

public class SymbolThreadVertical extends Thread {
    SymbolWriter symbol;
    private String symbolStr;

    public SymbolThreadVertical(SymbolWriter symbol, String symbolStr){
        this.symbol = symbol;
        this.symbolStr = symbolStr;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            symbol.print(symbolStr);
        }
    }
}
