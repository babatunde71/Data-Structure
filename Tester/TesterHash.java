package Tester;

import Hash.Hash;

import java.util.Objects;

public class TesterHash {
    static Hash<String,String> list = new Hash<String, String>(4);

    public static void main(String[] args) {
        list.add("H","Hello");
        list.add("b","Bye");
        list.add("c","Good morning");
        System.out.println(list.getTableSize());
        list.remove("H","Hello");
        System.out.println(list.getTableSize());
       // list.add(3,"Night");
       // list.add(4,"JOE");




       for(String key: list){
           System.out.println(list.getValue(key));
       }

    }
}
