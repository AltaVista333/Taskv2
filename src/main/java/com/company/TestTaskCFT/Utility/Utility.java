package com.company.TestTaskCFT.Utility;

import java.util.*;
import java.util.stream.Collector;

public class Utility {

    public static <T> Collector<T, TreeSet<T>, TreeSet<T>> collectorSet(Comparator<T> comp) {
        return Collector.of(
                TreeSet::new,
                (set, t) -> {
                    int c;
                    if (set.isEmpty() || (c = comp.compare(t, set.first())) == 0) {
                        set.add(t);
                    } else if (c > 0) {
                        set.clear();
                        set.add(t);
                    }
                },
                (set1, set2) -> {
                    if (set1.isEmpty()) {
                        return set2;
                    }
                    if (set2.isEmpty()) {
                        return set1;
                    }
                    int c = comp.compare(set1.first(), set2.first());
                    if (c < 0) {
                        return set2;
                    } else if (c > 0) {
                        return set1;
                    } else {
                        set1.addAll(set2);
                        return set1;
                    }
                });
    }
}
