package database;

import java.util.ArrayList;

public interface Database<T> {
    public abstract ArrayList<T> load();
    public abstract void update();
}
