package com.company.TestTaskCFT;

import com.company.TestTaskCFT.DAO.Reader;
import com.company.TestTaskCFT.DAO.Writer;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.Service.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Please write input and output file names");
            System.exit(-1);
        }

        String input = args[0];
        String output = args[1];

        try {
            int lineNumber = 1;
            Triangle maxTriangle = null;
            List<String> resultList = new ArrayList<>();
            Reader reader = new Reader(input);
            while(reader.hasNext()){
                String coordinatesString = reader.next();
                Optional<int[]> optionalCoords = Validator.StringToIntArrayConverter(coordinatesString);
                if (optionalCoords.isPresent() && Triangle.isTriangle(optionalCoords.get()) ){
                    Triangle triangle = new Triangle(optionalCoords.get());
                    if(triangle.isTriangleIsosceles()){
                         if (maxTriangle == null){
                             maxTriangle = triangle;
                             resultList.add(triangle.toString());
                         }

                         if (maxTriangle.getArea() < triangle.getArea()){
                             maxTriangle = triangle;
                             resultList.clear();
                             resultList.add(triangle.toString());
                         }

                         if (maxTriangle.getArea() == triangle.getArea() && !maxTriangle.equals(triangle)){
                             resultList.add(triangle.toString());
                         }
                    }
                } else {
                    ConsoleView.consoleLog("Line " + lineNumber + " wrong coordinates : " + coordinatesString);
                }
                lineNumber++;
            }

            Writer writer = new Writer(output, resultList);
            writer.write();

        } catch (Exception e){
            ConsoleView.consoleLog(e.getMessage());
            System.exit(-1);
        }

    }
}
