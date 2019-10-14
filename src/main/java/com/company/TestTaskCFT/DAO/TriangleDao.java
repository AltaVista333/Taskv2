package com.company.TestTaskCFT.DAO;

import com.company.TestTaskCFT.DataSource.DataSource;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.Service.Validator;
import com.company.TestTaskCFT.Utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TriangleDao {

    private DataSource dataSource;

    public TriangleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Stream<Triangle> getAllTrianglesFromDataSource() {
        Iterable<String> iterable = () -> dataSource;
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(Validator::StringToIntArrayConverter)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(Triangle::isTriangle)
                .map(Triangle::new);
    }

    public Optional<Triangle> getMaxAreaIsoscelesTriangle() {
        return getAllTrianglesFromDataSource()
                .filter(Triangle::isTriangleIsosceles)
                .max(Triangle::compareTo);
    }

    public List<Triangle> getAllMaxAreaIsoscelesTriangles() {
        return getAllTrianglesFromDataSource()
                .filter(Triangle::isTriangleIsosceles)
                .collect(Utility.collector(Triangle::compareTo));
    }

    public List<Triangle> getAllMaxAreaIsoscelesTrianglesSafe() {
        List<Triangle> list = new ArrayList<>();
        Optional<Triangle> optionalTriangle = getMaxAreaIsoscelesTriangle();
        optionalTriangle.ifPresent(
                triangle -> list.addAll(getAllMaxAreaIsoscelesTriangles(triangle.getArea()))
        );
        return list;
    }

    public List<Triangle> getAllMaxAreaIsoscelesTriangles(double triangleArea){
        return getAllTrianglesFromDataSource()
                .filter(Triangle::isTriangleIsosceles)
                .filter(triangle -> triangle.getArea() == triangleArea)
                .collect(Collectors.toList());
    }

    public void saveTriangle(Triangle triangle) {
        if (triangle != null) {
            dataSource.write(triangle.toString());
        }
    }
}
