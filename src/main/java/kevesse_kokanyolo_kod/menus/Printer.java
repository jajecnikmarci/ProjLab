package kevesse_kokanyolo_kod.menus;


import java.io.*;
import java.util.Scanner;

public class Printer {
    public static FileWriter fileWriter = null;
    public static Scanner scanner = null;
    private static int indentCounter = 0;

    public Printer(String inputFileName, String outputFileName) throws IOException {
        fileWriter = new FileWriter(outputFileName);
        scanner = new Scanner(new File(inputFileName));
    }
    public Printer() {
        fileWriter=null;
        scanner = new Scanner(System.in);
    }

    public void println(String msg) {
        try {
            for (int i = 0; i < indentCounter; i++) {
                if (fileWriter!=null) fileWriter.write("\t");
                else System.out.print("\t");
            }
            if (fileWriter!=null) fileWriter.write(" " + msg + "\n");
            else System.out.println(msg);
        } catch (IOException e) {
            System.out.println("Nem lehet a fájlba írni!");
            System.exit(1);
        }
    }
    public void print(String msg) {
        try {
            for (int i = 0; i < indentCounter - 1; i++) {
                if (fileWriter!=null) fileWriter.write("\t");
                else System.out.print("\t");
            }
            if (fileWriter!=null) fileWriter.write(" " + msg);
            else System.out.print(msg);
        } catch (IOException e) {
            System.out.println("Nem lehet a fájlba írni!");
            System.exit(1);
        }
    }

    public void startPrintObject(String name) {
        println(name);
        indentCounter++;
    }

    public void printField(String name, Object value) {
        println("- " + name + ": " + value);
    }

    public void endPrintObject() {
        indentCounter--;
    }
    
} 
