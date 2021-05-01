// 
// Decompiled by Procyon v0.5.36
// 

package com.mypackage;

import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class kasiski
{
    public static void main(final String[] array) {
        final kasiski kasiski = new kasiski();
        boolean b = false;
        int n = 3;
        String s = null;
        String s2 = null;
        if (array.length == 5) {
            if (array[0].equals("-v")) {
                b = true;
            }
            else {
                kasiski.printUsage();
            }
            if (array[1].equals("-m")) {
                try {
                    n = Integer.parseInt(array[2]);
                }
                catch (NumberFormatException ex) {
                    kasiski.printUsage();
                }
            }
            else {
                kasiski.printUsage();
            }
            s = array[3];
            s2 = array[4];
        }
        else if (array.length == 4) {
            if (array[0].equals("-v")) {
                b = true;
            }
            else if (array[0].equals("-m")) {
                try {
                    n = Integer.parseInt(array[1]);
                }
                catch (NumberFormatException ex2) {
                    kasiski.printUsage();
                }
                s = array[2];
                s2 = array[3];
            }
            else {
                kasiski.printUsage();
            }
            if (array[1].equals("-m")) {
                try {
                    n = Integer.parseInt(array[2]);
                }
                catch (NumberFormatException ex3) {
                    kasiski.printUsage();
                }
                s = array[3];
            }
        }
        else if (array.length == 3) {
            if (array[0].equals("-v")) {
                b = true;
                if (array[1].equals("-m")) {
                    try {
                        n = Integer.parseInt(array[2]);
                    }
                    catch (NumberFormatException ex4) {
                        kasiski.printUsage();
                    }
                }
                else {
                    s = array[1];
                    s2 = array[2];
                }
            }
            else if (array[0].equals("-m")) {
                try {
                    n = Integer.parseInt(array[1]);
                }
                catch (NumberFormatException ex5) {
                    kasiski.printUsage();
                }
                s = array[2];
            }
        }
        else if (array.length == 2) {
            if (array[0].equals("-v")) {
                b = true;
                s = array[1];
            }
            else if (array[0].equals("-m")) {
                try {
                    n = Integer.parseInt(array[1]);
                }
                catch (NumberFormatException ex6) {
                    kasiski.printUsage();
                }
            }
            else {
                s = array[0];
                s2 = array[1];
            }
        }
        else if (array.length == 1) {
            if (array[0].equals("-v")) {
                b = true;
            }
            else {
                s = array[0];
            }
        }
        if (b) {
            System.out.println(invokedynamic(makeConcatWithConstants:(ZILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;, b, n, s, s2));
        }
        final cipherio cipherio = new cipherio();
        cipherio.openRW(s, s2);
        final String file = cipherio.readFile();
        if (b) {
            System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, file));
        }
        kasiski.findSubstr(file, n, b);
        cipherio.closeStreams();
    }
    
    public void printUsage() {
        System.out.println("kasiski [-v] [-m length] [infile [outfile]]");
        System.exit(0);
    }
    
    public void findSubstr(String replaceAll, int n, final boolean b) {
        final LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        final LinkedHashMap<String, LinkedList<Integer>> linkedHashMap2 = new LinkedHashMap<String, LinkedList<Integer>>();
        replaceAll = replaceAll.replaceAll("[^A-Za-z]", "");
        int n2 = 0;
        final int n3 = n;
        boolean b2 = false;
        int i = 0;
        int j = n;
        while (j <= replaceAll.length()) {
            final String substring = replaceAll.substring(i, j);
            if (linkedHashMap.containsKey(substring) && linkedHashMap2.containsKey(substring)) {
                linkedHashMap.put(substring, linkedHashMap.get(substring) + 1);
                final LinkedList<Integer> value = linkedHashMap2.get(substring);
                value.add(i);
                linkedHashMap2.put(substring, value);
                n2 = 1;
                b2 = true;
            }
            else {
                linkedHashMap.put(substring, 1);
                final LinkedList<Integer> value2 = new LinkedList<Integer>();
                value2.add(i);
                linkedHashMap2.put(substring, value2);
            }
            if (j == replaceAll.length() && n2 == 1) {
                n2 = 0;
                i = 0;
                j = ++n;
            }
            else {
                ++i;
                ++j;
            }
        }
        final TreeMap treeMap = new TreeMap<String, Integer>((Comparator<? super Object>)new LengthCompare(linkedHashMap));
        for (final Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
        if (b) {
            for (final Map.Entry<String, LinkedList<Integer>> entry2 : linkedHashMap2.entrySet()) {
                System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;, (String)entry2.getKey(), entry2.getValue()));
            }
            for (final Map.Entry<String, Integer> entry3 : treeMap.entrySet()) {
                System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;, (String)entry3.getKey(), entry3.getValue()));
            }
        }
        if (b2) {
            this.printSubstrResults((TreeMap<String, Integer>)treeMap, linkedHashMap2, n3);
        }
    }
    
    public void printSubstrResults(final TreeMap<String, Integer> treeMap, final LinkedHashMap<String, LinkedList<Integer>> linkedHashMap, final int n) {
        System.out.println("Length Count Word Location (distance)");
        System.out.println("====== ===== ==== =========");
        for (final Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            if (entry.getValue() >= 2) {
                final LinkedList<Integer> list = linkedHashMap.get(entry.getKey());
                System.out.printf("%6d %5d %4s ", entry.getKey().length(), entry.getValue(), entry.getKey());
                for (int i = 0; i < list.size(); ++i) {
                    if (i > 0) {
                        System.out.printf("%d (%d) ", list.get(i), list.get(i) - list.get(i - 1));
                    }
                    else {
                        System.out.print(invokedynamic(makeConcatWithConstants:(Ljava/lang/Object;)Ljava/lang/String;, list.get(i)));
                    }
                }
                System.out.print("\n");
            }
        }
    }
    
    private class LengthCompare implements Comparator<String>
    {
        Map<String, Integer> base;
        
        public LengthCompare(final Map<String, Integer> base) {
            this.base = base;
        }
        
        @Override
        public int compare(final String s, final String anotherString) {
            int compareTo;
            if (s.length() < anotherString.length()) {
                compareTo = 1;
            }
            else if (s.length() > anotherString.length()) {
                compareTo = -1;
            }
            else if (this.base.get(s) < this.base.get(anotherString)) {
                compareTo = 1;
            }
            else if (this.base.get(s) > this.base.get(anotherString)) {
                compareTo = -1;
            }
            else {
                compareTo = s.compareTo(anotherString);
            }
            return compareTo;
        }
    }
}
