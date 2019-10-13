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

        if (args.length != 2) {
            System.out.println("Please write input and output file names");
            System.exit(-1);
        }

        String input = args[0];
        String output = args[1];

        View view = new ConsoleView();
        try {
            DataSource dataSource = new FileDataSource(input, output);
            TriangleDao triangleDao = new TriangleDao(dataSource);
            List<Triangle> triangleList = triangleDao.getAllMaxAreaTriangles();
            triangleDao.saveTriangles(triangleList);
            dataSource.close();
        } catch (Exception e) {
            view.log(e.getMessage());
            System.exit(-1);
        }
    }
}
