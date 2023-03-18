package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {

        try{
            if(balance<minBalance){
                throw new Exception("Insufficient Balance");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String accountNumber = helperGenerateAccountNumber(digits, sum);
        try{
            if(accountNumber.equals("-1")){
                throw new Exception("Account Number can not be generated");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance = this.balance + amount;

    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        try{
            if(this.balance-amount < minBalance){
                throw new Exception("Insufficient Balance");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        this.balance -= amount;
    }

    public String helperGenerateAccountNumber(int digits, int sum){

        int[] accountNumber = new int[digits];
        sum -= 1;

        while(sum>9){
            accountNumber[--digits] = 9;
            sum -= 9;
        }
        accountNumber[--digits] = sum;
        accountNumber[0] += 1;

        StringBuilder accNo = new StringBuilder();

        for(int i=0 ; i<accountNumber.length ; i++){
            accNo.append(accountNumber[i]);
        }

        return accNo.toString();
    }
}