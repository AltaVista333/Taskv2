package com.company.TestTaskCFT.DAO;

import com.company.TestTaskCFT.DataSource.DataSource;
import com.company.TestTaskCFT.Model.Triangle;
import com.company.TestTaskCFT.Service.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TriangleDao {

    private DataSource dataSource;

    public TriangleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static <T> Collector<T, ?, List<T>> collector(Comparator<? super T> comp) {
        return Collector.of(
                ArrayList::new,
                (list, t) -> {
                    int c;
                    if (list.isEmpty() || (c = comp.compare(t, list.get(0))) == 0) {
                        list.add(t);
                    } else if (c > 0) {
                        list.clear();
                        list.add(t);
                    }
                },
                (list1, list2) -> {
                    if (list1.isEmpty()) {
                        return list2;
                    }
                    if (list2.isEmpty()) {
                        return list1;
                    }
                    int r = comp.compare(list1.get(0), list2.get(0));
                    if (r < 0) {
                        return list2;
                    } else if (r > 0) {
                        return list1;
                    } else {
                        list1.addAll(list2);
                        return list1;
                    }
                });
    }

    public Stream<Triangle> getAllTrianglesFromDataSource() {
        Iterable<String> iterable = () -> dataSource;
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(Validator::StringToIntArrayConverter)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(x -> Validator.validateCoordinates(x, Validator.MINIMAL_TRIANGLE_COORDINATES_COUNT))
                .filter(Triangle::isTriangle)
                .map(Triangle::new);
    }

    public Optional<Triangle> getMaxAreaTriangle() {
        return getAllTrianglesFromDataSource().max(Comparator.comparing(Triangle::getArea));
    }

    public List<Triangle> getAllMaxAreaTriangles() {
        return getAllTrianglesFromDataSource()
                .filter(Triangle::isTriangleIsosceles)
                .collect(collector(Comparator.comparing(Triangle::getArea)));
    }

    public void saveTriangles(List<Triangle> list) {
        list.stream()
                .map(Triangle::toString)
                .forEach(dataSource::write);
    }
}
