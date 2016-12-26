package com.github.drxaos.edu;

import java.io.Serializable;

/**
 * Created by user1 on 16.11.2016.
 */
public interface  SavedList<E extends Serializable>{

    E get(int index);
    E set(int index, E element);
    int size();
    void add(int index, E element);
    E remove(int index);

    boolean add(E element);
    void reload() throws FileOperationException;
}