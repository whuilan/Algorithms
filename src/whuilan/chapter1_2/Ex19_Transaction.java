package whuilan.chapter1_2;

import edu.princeton.cs.algs4.Date;

public class Ex19_Transaction {

    private final String  who;      // customer
    private final Date when;     // date
    private final double  amount;

    /*解析形如Turing 5/22/1998 11.99这样的交易字符串*/
    public Ex19_Transaction(String transactionName){
        String[] transactions = transactionName.split(" ");
        who = transactions[0];
        when = new Date(transactions[1]);
        amount = Double.parseDouble(transactions[2]);
        // System.out.print(who+when+amount);
    }

    public static void main(String[] args){
        String transaction = "Wang 4/23/2019 12.45";
        Ex19_Transaction t = new Ex19_Transaction(transaction);

    }
}
