package ua.ithillel.list;

import java.util.ArrayList;
import java.util.List;

public abstract class ThreadSafeList {
    protected List<Integer> list = new ArrayList<>();

    public abstract Integer get(int index);
    public abstract void add(Integer value);
    public abstract void remove(Integer value);

}
