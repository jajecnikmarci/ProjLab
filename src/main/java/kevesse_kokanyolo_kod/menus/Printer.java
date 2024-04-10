package kevesse_kokanyolo_kod.menus;


import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Printer {
    public static OutputStreamWriter fileWriter = null;
    public static Scanner scanner = null;
    private static int indentCounter = 0;

    public Printer(String inputFileName, String outputFileName) throws IOException {
        fileWriter = new FileWriter(outputFileName);
        scanner = new Scanner(new File(inputFileName));
    }
    private static String result; 
    public Printer(String inputFileName) throws IOException{
        scanner = new Scanner(new File(inputFileName));
        fileWriter=null;
    } 
    public Printer() {
        fileWriter=null;
        scanner = new Scanner(System.in);
    }

    public void close() throws IOException {
        if(fileWriter != null) {
            fileWriter.close();
        }
        if (scanner != null) {
            scanner.close();
        }
    }
    public void println(String msg) {
        print(msg + "\n");
    }

    public String getOutput() {
        return result;
    
    }
    public void print(String msg) {
        String formattedMsg = "";
        for (int i = 0; i < indentCounter; i++) {
            formattedMsg += "\t";
        }
        formattedMsg += msg;

        if (fileWriter != null) {
            try {
                fileWriter.write(formattedMsg);
            } catch (IOException e) {
                System.out.println("Nem lehet a fájlba írni!");
                System.exit(1);
            }
        } else if(result != null) {
            result += formattedMsg;
        } else {
            System.out.println(formattedMsg);
        }
    }

    public void startPrintObject(String name) {
        println(name);
        indentCounter++;
    }

    public void printField(String name, Object value) {
        println("- " + name + ": " + value);
    }

    public void printFields(String name,  List<? extends Object> values) {
        println("- " + name + ": ");
        indentCounter++;
        for (Object value : values) {
            println(value.toString());
        }
        indentCounter--;
    }

    public void endPrintObject() {
        indentCounter--;
    }
    
    public void printError(String errorMessage) {
        println("Hiba: " + errorMessage);
    }
} 
