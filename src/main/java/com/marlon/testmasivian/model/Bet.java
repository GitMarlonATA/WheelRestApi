package com.marlon.testmasivian.model;

public class Bet {
    private int idPlayer;
    private int money;
    private String bet;
    private String kindBet;
    private int idWheel;

    public Bet(int idPlayer,int money, String bet, String kindBet, int idWheel) {
        this.idPlayer = idPlayer;
        this.bet = bet;
        this.kindBet = kindBet;
        this.money = money;
        this.idWheel = idWheel;
    }

    public Bet() {
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getKindBet() {
        return kindBet;
    }

    public void setKindBet(String kindBet) {
        this.kindBet = kindBet;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getIdWheel() {
        return idWheel;
    }

    public void setIdWheel(int idWheel) {
        this.idWheel = idWheel;
    }
    
    
}
