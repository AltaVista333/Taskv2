package com.company.TestTaskCFT;

import com.company.TestTaskCFT.DAO.TriangleDao;
import com.company.TestTaskCFT.DataSource.DataSource;
import com.company.TestTaskCFT.DataSource.FileDataSource;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.View.ConsoleView;
import com.company.TestTaskCFT.View.View;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        View view = new ConsoleView();
        view.log("The programm started");
        if (args.length != 2) {
            view.log("Please write input and output file names");
            System.exit(-1);
        }
        view.log("Input file : " + args[0] + ". Output file: " + args[1] + ".");
        try {
            DataSource dataSource = new FileDataSource(args[0], args[1]);
            TriangleDao triangleDao = new TriangleDao(dataSource);
            List<Triangle> triangleList = triangleDao.getAllMaxAreaIsoscelesTriangles();
            triangleDao.saveTriangles(triangleList);
            dataSource.close();
            view.log("The program ended successfully. Data recorded.");

        } catch (Exception e) {
            view.log(e.getMessage());
            System.exit(-1);
        }
    }
}
