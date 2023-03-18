package com.driver;
import java.util.*;
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(isValidLicenseId(this.tradeLicenseId)){
            return;
        }

        String licenceId = rearrangeLicenseId(this.tradeLicenseId);
        if(licenceId.equals("-1")){
            throw new Exception("Valid License can not be generated");
        }
        else{
            this.tradeLicenseId = licenceId;
        }

    }

    public boolean isValidLicenseId(String licenseId){

        for(int i=1 ; i<licenseId.length() ; i++){
            if(licenseId.charAt(i-1) == licenseId.charAt(i)){
                return false;
            }
        }
        return true;

    }

    public String rearrangeLicenseId(String licenseId){

        Map<Character, Integer> frequency = new HashMap<>();

        for(int i=0 ; i<licenseId.length() ; i++){
            char c = licenseId.charAt(i);
            frequency.put(c, frequency.getOrDefault(c,0)+1);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->frequency.get(b)-frequency.get(a));
        pq.addAll(frequency.keySet());

        while(!pq.isEmpty()){
            char curr = pq.remove();
            sb.append(curr);
            if(frequency.get(curr)>1){
                frequency.put(curr, frequency.get(curr)-1);
                pq.add(curr);
            }
        }

        String tradeLicenseId = sb.toString();

        if(isValidLicenseId(tradeLicenseId)){
            return tradeLicenseId;
        }

        return "-1";
    }
}
