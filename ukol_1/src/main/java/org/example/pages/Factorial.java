package org.example.pages;

public class Factorial {

    public static void main(String[] args) {
    }

    public static int factorialCount(int n){
        int result=1,i=1;
        while(i<=n){
            result=result*i;
            i++;
        }

        return result;
    }

}