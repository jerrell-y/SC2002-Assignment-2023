package database;

import java.util.ArrayList;

/**
 * The Database interface defines the structure for database classes that manage data for different entities.
 * Specifies the methods to load data from csv and update the storage with current data.
 *
 * @param <T> the type of entity that the implementing database will manage.
 */
public interface Database<T> {
    /**
     * Loads the data from csv into the application.
     * This method should return a collection of the data entities that were loaded.
     *
     * @return An ArrayList of type T, representing the data entities loaded from storage.
     */
    public abstract ArrayList<T> load();
    /**
     * Updates the csv with the current state of the data entities.
     * This method should ensure that the csv reflects any changes made to the data in the application.
     */
    public abstract void update();
}
