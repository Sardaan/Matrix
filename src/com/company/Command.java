package com.company;

import com.company.io.ConsoleReader;
import com.company.io.FileReader;
import com.company.io.Randomizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Command {
    PrintStream out = System.out;
    public void readCommand(){
        out.println("Write your command or 'help'");
        try {
            while (true){
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine().trim().toLowerCase();
                MatrixSolver solver = new MatrixSolver();
                switch (command){
                    case "console":
                        solver.solve(new ConsoleReader());
                        break;
                    case "file":
                        solver.solve(new FileReader());
                        break;
                    case "random":
                        solver.solve(new Randomizer());
                        break;
                    case "help":
                        out.println(help());
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Unknown command");
                        break;
                }out.println("Write command");
            }
        }catch (IOException | NullPointerException e){
            System.err.println("command reading error");
        }
    }

    public String help(){
        return "Доступные команды:\n" +
                "   matrix:                 ввести коэффициенты мартицы из консоли\n" +
                "   random:                 зарандомить коэффициенты матрицы указанной размерности\n" +
                "   file:                   получить матрицу из файла\n" +
                "   exit:                   выход из программы \n";
    }
}
