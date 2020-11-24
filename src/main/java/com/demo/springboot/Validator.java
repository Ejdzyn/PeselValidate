package com.demo.springboot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

    /*
    liczby 80 — w przypadku osób urodzonych w latach 1800-1899,
    liczby 0 — w przypadku osób urodzonych w latach 1900-1999,
    liczby 20 — w przypadku osób urodzonych w latach 2000-2099;
    */
    Logger logger;
    public Validator(Logger log){
        this.logger=log;
    }

    public Boolean isValid(String id){
        if(id.length() != 11){
            return false;
        } else {

            // mozna na starcie sprawdzic checkSum ale chyba nie o to chodzilo
            //return checkSum(id);

            int month = Integer.parseInt(id.substring(2,4));
            String day = id.substring(4,6);

            String year = "";
            if(month-80>0 && month-80<=12){
                year+=18+id.substring(0,2);
            } else if(month-20>0 && month-20<=12 ) {
                year+=20+id.substring(0,2);
            } else if(month<=12) {
                year+=19+id.substring(0,2);
            } else return false;

            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);

            try {
                format.parse(day+"/"+month+"/"+year);

                if(id.charAt(id.length()-1)%2==0){
                    logger.info("Kobieta, urodzona "+day+"/"+month+"/"+year);
                } else {
                    logger.info("Mężczyzna, urodzony "+day+"/"+month+"/"+year);
                }

                return true;
            } catch (ParseException e) {
                return false;
            }
        }
    }

    public boolean checkSum(String id) {

        List<Integer> digits = new ArrayList<>();

        for(char c : id.toCharArray()){
            digits.add(Integer.parseInt(String.valueOf(c)));
        }

        System.out.println(digits);

        int sum=0;

        int t = 1;

        for(int i = 0 ; i < digits.size()-1;i++){

            sum+=digits.get(i)*t;
            if(t==9) t = 1;
            else if(t==3)t=7;
            else t+=2;
        }

        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum == digits.get(10)) {
            return true;
        }
        else {
            return false;
        }
    }

}
