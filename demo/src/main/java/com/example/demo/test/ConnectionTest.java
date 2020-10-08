package com.example.demo.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by 24345 on 2020/9/10.
 */
public class ConnectionTest {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(6);
        list1.add(4);

        list2.add(12);
        list2.add(10);
        list2.add(17);
        list2.add(15);

        list1.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1<o2){
                    return -1;
                }
                return 0;
            };
        });

        for (int a:list1) {
            System.out.println(a);
        }


        list2.get(0);

        int a[] = {1,3,67,24,5,16};
        for (int i=0; i<a.length-1; i++){
            for(int j=0; j<a.length-i-1; j++){
                int dump;
                if(a[j] < a[j+1]){
                    dump = a[j];
                    a[j] = a[j+1];
                    a[j+1]= dump;
                };
            }
        }
        for (int b:a) {
            System.out.println(b);
        }
    }
}
