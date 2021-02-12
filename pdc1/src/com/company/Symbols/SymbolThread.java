package com.company.Symbols;


public class SymbolThread extends Thread {
    private final String symbol;

    public SymbolThread(String symbol){
        this.symbol = symbol;
    }
    @Override
    public void run(){
            for(int i = 0; i < 10; i++){
                System.out.print(symbol);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}