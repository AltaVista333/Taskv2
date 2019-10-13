package com.company.TestTaskCFT.DataSource;

import java.util.Iterator;
import java.util.List;

public interface DataSource extends Iterator<String> {

    public boolean hasNext();
    public String next();
    public void write(String s);

}
