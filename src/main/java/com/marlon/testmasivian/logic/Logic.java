package com.marlon.testmasivian.logic;

import com.marlon.testmasivian.model.Bet;
import java.util.List;

public class Logic {
    public Logic() {
    }
    public boolean verifyBet(Bet bet){
        if(bet.getKindBet().equals("color")){
            if(bet.getBet().equals("black") || bet.getBet().equals("red")){
                return true;
            }
        }
        if(bet.getKindBet().equals("number")){
            int number = verifyNumber(bet.getBet());
            if(number != -1){
                return true;
            }
        }
        return false;
    }
    
    public int verifyNumber(String number){
        try {
            int resultado = Integer.parseInt(number);
            if(resultado >= 0 && resultado <= 36)
                return resultado;
            return -1;
        } catch (Exception e) {
            System.out.println("Excepcion " + e.getMessage());
            return -1;
        }
    }
    
    public String defineWinners(List<Bet> bets){
        int numberWinner = (int) (0 + (Math.random() * 37));
        System.out.println("number Winner " + numberWinner);
        String colorWinner = defineColor(numberWinner);
        System.out.println("color Winner " + colorWinner);
        return findAndInfoWinners(bets,numberWinner,colorWinner);
    }
    
    public String findAndInfoWinners(List<Bet> bets,int numberWinner, String colorWinner){
        String res = "{ \"winners\": ";
        for(int i = 0; i < bets.size(); i++){
            Bet w = bets.get(i);
            if(w.getKindBet().equals("color")){
                if(w.getBet().equals(colorWinner)){
                    res += "[ \"idWinner\":" + w.getIdPlayer() + ", \"earnedMoney\":" + w.getMoney()*1.8 + "],";
                }
            }
            if(w.getKindBet().equals("number")){
                if(Integer.parseInt(w.getBet()) == numberWinner){
                    res += "[ \"idWinner\":" + w.getIdPlayer() + ", \"earnedMoney\":" + w.getMoney()*5.0 + "],";
                }
            }
        }
        res = res.substring(0, res.length()-1);
        res += "}";
        return res;
    }
    
    public String defineColor(int number){
        if(number % 2 == 0){
            return "red";
        }
        return "black";
    }
}
