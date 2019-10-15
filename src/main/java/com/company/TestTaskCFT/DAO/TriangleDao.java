package com.company.TestTaskCFT.DAO;

import com.company.TestTaskCFT.DataSource.DataSource;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.Service.Validator;
import com.company.TestTaskCFT.Utility.Utility;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TriangleDao {

    private DataSource dataSource;

    public TriangleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Stream<Triangle> getAllTrianglesFromDataSourceStream() {
        Iterable<String> iterable = () -> dataSource;
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(Validator::StringToIntArrayConverter)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(Triangle::isTriangle)
                .map(Triangle::new);
    }

    public Optional<Triangle> getMaxAreaIsoscelesTriangle() {
        return getAllTrianglesFromDataSourceStream()
                .filter(Triangle::isTriangleIsosceles)
                .max(Triangle::compareTo);
    }

    public Set<Triangle> getUniqMaxAreaIsoscelesTriangles() {
        return getAllTrianglesFromDataSourceStream()
                .filter(Triangle::isTriangleIsosceles)
                .collect(Utility.collectorSet(Triangle::compareTo));
    }

    public Stream<Triangle> getAllMaxAreaIsoscelesTrianglesStream(double triangleArea){
        return getAllTrianglesFromDataSourceStream()
                .filter(Triangle::isTriangleIsosceles)
                .filter(triangle -> triangle.getArea() == triangleArea);
    }


    public void saveTriangle(Triangle triangle) {
        if (triangle != null) {
            dataSource.write(triangle.toString());
        }
    }
}
