// 
// Decompiled by Procyon v0.5.36
// 

package com.mypackage;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class cipherio
{
    private BufferedReader read;
    private BufferedWriter write;
    
    public cipherio() {
        this.read = null;
        this.write = null;
    }
    
    public void openRW(final String fileName, final String fileName2) {
        if (fileName != null) {
            try {
                this.read = new BufferedReader(new FileReader(fileName));
            }
            catch (FileNotFoundException ex) {
                System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, fileName));
                System.exit(1);
            }
            if (fileName2 != null) {
                try {
                    this.write = new BufferedWriter(new FileWriter(fileName2));
                }
                catch (IOException ex2) {
                    System.out.println("File exists but cannot be created or openedfor unknown reasons.");
                    System.exit(1);
                }
            }
            else {
                this.write = new BufferedWriter(new OutputStreamWriter(System.out));
            }
        }
        else {
            this.read = new BufferedReader(new InputStreamReader(System.in));
            this.write = new BufferedWriter(new OutputStreamWriter(System.out));
        }
    }
    
    public String readFile() {
        String s = "";
        String s2 = null;
        try {
            s2 = this.read.readLine();
        }
        catch (IOException ex) {
            System.out.println("Unable to read from file.");
            System.exit(1);
        }
        while (s2 != null) {
            s = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, s, s2);
            try {
                s2 = this.read.readLine();
            }
            catch (IOException ex2) {
                System.out.println("Unable to read from file.");
                System.exit(1);
            }
        }
        return s.toUpperCase();
    }
    
    public void writeFile(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            try {
                this.write.write(s.charAt(i));
            }
            catch (IOException ex) {
                System.out.println("Cannot write to file");
                System.exit(1);
            }
        }
    }
    
    public void closeStreams() {
        try {
            this.write.close();
            this.read.close();
        }
        catch (IOException ex) {
            System.out.println("Unable to close files.");
            System.exit(1);
        }
    }
}
