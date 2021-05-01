// 
// Decompiled by Procyon v0.5.36
// 

package com.mypackage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main
{
    private static Scanner eingabe;
    private static String geheim;
    private static boolean firstround;
    private static final char[] HEX_ARRAY;
    
    public static String fmt(final String s, final int n) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            if (i != 0 && i % n == 0) {
                s2 = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, s2);
            }
            s2 = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;C)Ljava/lang/String;, s2, s.charAt(i));
        }
        return s2;
    }
    
    public static void help() {
        System.out.printf("Deine Aufgabe ist es, herauszufinden, mit welchem Schl\u00fcsselwort folgende Nachricht verschl\u00fcsselt wurde:\n\n%s\n\nDazu stehen dir folgende Befehle zur Verf\u00fcgung:\n\n * dopp N: Finde Doppler von mindestens N Buchstaben im Geheimtext\n * fact ZAHL: Zerlege ZAHL in seine Primfaktoren\n * freq N: Teile Geheimtext in Bl\u00f6cke der L\u00e4nge N auf und f\u00fchre eine H\u00e4ufigkeitsanalyse durch\n * quad: Das Vigenere-Quadrat ausgeben\n * help: Diese Nachricht anzeigen\n * quit: Programm beenden\n\nWenn du glaubst, das Schl\u00fcsselwort zu kennen, kannst du mit 'check KEY' pr\u00fcfen, ob du richtig lagst. Im Quelltext dieses Programms wirst du nichts finden, was dir weiterhilft.\n", fmt(Main.geheim, 5));
    }
    
    public static void main(final String[] array) {
        help();
        while (true) {
            System.out.printf("\n> ", new Object[0]);
            final String[] split = Main.eingabe.nextLine().split("\\s+");
            final String s = split[0];
            switch (s) {
                case "quit": {
                    System.exit(0);
                    continue;
                }
                case "help": {
                    help();
                    continue;
                }
                case "dopp": {
                    new kasiski().findSubstr(Main.geheim, Integer.parseInt(split[1]), false);
                    continue;
                }
                case "fact": {
                    int int1;
                    for (int1 = Integer.parseInt(split[1]); int1 % 2 == 0; int1 /= 2) {
                        System.out.print("2 ");
                    }
                    for (int n2 = 3; n2 <= Math.sqrt(int1); n2 += 2) {
                        while (int1 % n2 == 0) {
                            System.out.print(invokedynamic(makeConcatWithConstants:(I)Ljava/lang/String;, n2));
                            int1 /= n2;
                        }
                    }
                    if (int1 > 2) {
                        System.out.print(int1);
                    }
                    System.out.println();
                    continue;
                }
                case "freq": {
                    System.out.println("H\u00e4ufigste Buchstaben (zweith\u00e4ufigste in Klammern):");
                    final int int2 = Integer.parseInt(split[1]);
                    final int[][] array2 = new int[int2][26];
                    for (int i = 0; i < Main.geheim.length(); ++i) {
                        final char char1;
                        final char c = char1 = Main.geheim.charAt(i);
                        if (char1 >= 'a' && char1 <= 'z') {
                            final int[] array3 = array2[i % int2];
                            final int n3 = c - 'a';
                            ++array3[n3];
                        }
                    }
                    for (int j = 0; j < int2; ++j) {
                        int n4 = 0;
                        for (int k = 0; k < array2[j].length; ++k) {
                            if (array2[j][k] > n4) {
                                n4 = array2[j][k];
                            }
                        }
                        int n5 = 0;
                        for (int l = 0; l < array2[j].length; ++l) {
                            final int n6 = array2[j][l];
                            if (n6 > n5 && n6 < n4) {
                                n5 = n6;
                            }
                        }
                        String s2 = "";
                        for (int n7 = 0; n7 < array2[j].length; ++n7) {
                            if (array2[j][n7] == n4) {
                                s2 = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;C)Ljava/lang/String;, s2, (char)(n7 + 97));
                            }
                        }
                        String s3 = "";
                        for (int n8 = 0; n8 < array2[j].length; ++n8) {
                            if (array2[j][n8] == n5) {
                                s3 = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;C)Ljava/lang/String;, s3, (char)(n8 + 97));
                            }
                        }
                        System.out.printf("an Blockposition %d: %s (%s)\n", j, s2, s3);
                    }
                    continue;
                }
                case "quad": {
                    System.out.println("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
                    System.out.println("B C D E F G H I J K L M N O P Q R S T U V W X Y Z A");
                    System.out.println("C D E F G H I J K L M N O P Q R S T U V W X Y Z A B");
                    System.out.println("D E F G H I J K L M N O P Q R S T U V W X Y Z A B C");
                    System.out.println("E F G H I J K L M N O P Q R S T U V W X Y Z A B C D");
                    System.out.println("F G H I J K L M N O P Q R S T U V W X Y Z A B C D E");
                    System.out.println("G H I J K L M N O P Q R S T U V W X Y Z A B C D E F");
                    System.out.println("H I J K L M N O P Q R S T U V W X Y Z A B C D E F G");
                    System.out.println("I J K L M N O P Q R S T U V W X Y Z A B C D E F G H");
                    System.out.println("J K L M N O P Q R S T U V W X Y Z A B C D E F G H I");
                    System.out.println("K L M N O P Q R S T U V W X Y Z A B C D E F G H I J");
                    System.out.println("L M N O P Q R S T U V W X Y Z A B C D E F G H I J K");
                    System.out.println("M N O P Q R S T U V W X Y Z A B C D E F G H I J K L");
                    System.out.println("N O P Q R S T U V W X Y Z A B C D E F G H I J K L M");
                    System.out.println("O P Q R S T U V W X Y Z A B C D E F G H I J K L M N");
                    System.out.println("P Q R S T U V W X Y Z A B C D E F G H I J K L M N O");
                    System.out.println("Q R S T U V W X Y Z A B C D E F G H I J K L M N O P");
                    System.out.println("R S T U V W X Y Z A B C D E F G H I J K L M N O P Q");
                    System.out.println("S T U V W X Y Z A B C D E F G H I J K L M N O P Q R");
                    System.out.println("T U V W X Y Z A B C D E F G H I J K L M N O P Q R S");
                    System.out.println("U V W X Y Z A B C D E F G H I J K L M N O P Q R S T");
                    System.out.println("V W X Y Z A B C D E F G H I J K L M N O P Q R S T U");
                    System.out.println("W X Y Z A B C D E F G H I J K L M N O P Q R S T U V");
                    System.out.println("X Y Z A B C D E F G H I J K L M N O P Q R S T U V W");
                    System.out.println("Y Z A B C D E F G H I J K L M N O P Q R S T U V W X");
                    System.out.println("Z A B C D E F G H I J K L M N O P Q R S T U V W X Y");
                    continue;
                }
                case "check": {
                    try {
                        final String bytesToHex = bytesToHex(MessageDigest.getInstance("MD5").digest(split[1].toLowerCase().getBytes(StandardCharsets.UTF_8)));
                        if (Main.firstround && bytesToHex.equals("CA2731CD2B9F72B1C22A5ECFDF980F01")) {
                            Main.geheim = "tpcaoaaqsejastpaneleuehmqrcxeelrfihpianyjtuggroqttozhbyspnhqoiwtxelpfdcqtwyuuelnfovmdhnqouhpblfqtmyxeehibsgusaorgayxmt";
                            System.out.printf("%s ist korrekt! Da die Aufgabe offensichtlich zu leicht f\u00fcr dich war, habe ich noch einen k\u00fcrzeren Geheimtext f\u00fcr dich.\n\n%s\n\nWirst du es schaffen, das Schl\u00fcsselwort herauszufinden?\n", split[1], fmt(Main.geheim, 5));
                            Main.firstround = false;
                        }
                        else if (!Main.firstround && bytesToHex.equals("A4C2063020ABEACDA25F3480F167DA60")) {
                            System.out.printf("%s ist korrekt! Du bist ein wahrer Meisterknacker!\n", split[1]);
                        }
                        else {
                            System.out.printf("%s ist nicht korrekt. Versuche es noch einmal.\n", split[1]);
                        }
                    }
                    catch (Exception ex) {}
                    continue;
                }
                default: {
                    System.out.printf("%s: Ung\u00fcltiger Befehl\n", split[0]);
                    continue;
                }
            }
        }
    }
    
    public static String bytesToHex(final byte[] array) {
        final char[] value = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            value[i * 2] = Main.HEX_ARRAY[n >>> 4];
            value[i * 2 + 1] = Main.HEX_ARRAY[n & 0xF];
        }
        return new String(value);
    }
    
    static {
        Main.eingabe = new Scanner(System.in);
        Main.geheim = "wwdplswxswaoszlkmktmagpwvboizvivveatmwlsxraqkgpwrlwvidxmfoqwwojyjwyvvhmejtmwakstdrcfhwulmjlidxmfftwmjwresvceamjhmfhiciqfialvixvmulbdmkzivcsvkiymivrivyihgkmfecxhmeitlizfejwrloezwrqjkmfheaiidpmtizricyxlszwfhikwqzvsarlzskzfmyejlwmaqkzxmfskznmeevvjzveasqbviawpbwvvktzwgpwvacevvmlaizwrewvqkxvggpeetcpikwmfwxjikziz";
        Main.firstround = true;
        HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    }
}
