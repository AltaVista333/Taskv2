package com.company.TestTaskCFT.Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public class Utility {

    public static <T> Collector<T, List<T>, List<T>> collector(Comparator<T> comp) {
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
                    int c = comp.compare(list1.get(0), list2.get(0));
                    if (c < 0) {
                        return list2;
                    } else if (c > 0) {
                        return list1;
                    } else {
                        list1.addAll(list2);
                        return list1;
                    }
                });
    }
}
