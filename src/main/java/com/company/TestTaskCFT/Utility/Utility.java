package com.company.TestTaskCFT.Utility;

import java.util.*;
import java.util.stream.Collector;

public class Utility {

    public static <T> Collector<T, TreeSet<T>, TreeSet<T>> collectorSet(Comparator<T> comp) {
        return Collector.of(
                TreeSet::new,
                (set, object) -> {
                    int c;
                    if (set.isEmpty() || (c = comp.compare(object, set.first())) == 0) {
                        set.add(object);
                    } else if (c > 0) {
                        set.clear();
                        set.add(object);
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

    public static <T> Collector<T, HashSet<T>, HashSet<T>> collectorHashSet(Comparator<T> comp) {
        return Collector.of(
                HashSet::new,
                (set, object) -> {
                    int c;
                    if (set.isEmpty() || (c = comp.compare(object, set.stream().findAny().get())) == 0) {
                        set.add(object);
                    } else if (c > 0) {
                        set.clear();
                        set.add(object);
                    }
                },
                (set1, set2) -> {
                    if (set1.isEmpty()) {
                        return set2;
                    }
                    if (set2.isEmpty()) {
                        return set1;
                    }
                    int c = comp.compare(set1.stream().findAny().get(), set2.stream().findAny().get());
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
