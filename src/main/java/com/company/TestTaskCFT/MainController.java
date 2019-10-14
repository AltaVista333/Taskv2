package com.company.TestTaskCFT;

import com.company.TestTaskCFT.DAO.TriangleDao;
import com.company.TestTaskCFT.DataSource.DataSource;
import com.company.TestTaskCFT.DataSource.FileDataSource;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.View.ConsoleView;
import com.company.TestTaskCFT.View.View;

import java.util.Optional;
import java.util.Set;

public class MainController {

    public static void main(String[] args) {
        View view = new ConsoleView();
        view.log("The programm started");
        if (args.length < 2) {
            view.log("Please write input and output file names as command-line arguments.");
            System.exit(-1);
        }
        view.log("Input file : " + args[0] + ". Output file: " + args[1] + ".");
        try {
            DataSource dataSource = new FileDataSource(args[0], args[1]);
            TriangleDao triangleDao = new TriangleDao(dataSource);
            if (args.length == 3 && "all".equals(args[2])) {
                Set<Triangle> triangleList = triangleDao.getUniqMaxAreaIsoscelesTriangles();
                triangleList.forEach(triangleDao::saveTriangle);
            } else {
                Optional<Triangle> maxAreaIsoscelesTriangle = triangleDao.getMaxAreaIsoscelesTriangle();
                maxAreaIsoscelesTriangle.ifPresent(triangleDao::saveTriangle);
            }
            dataSource.close();
            view.log("The program ended successfully. Data recorded.");
        } catch (Exception e) {
            view.log(e.getMessage());
            System.exit(-1);
        }
    }
}
