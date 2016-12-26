package com.laba12;


import java.io.Serializable;

public interface SavedList  <E extends Serializable>{
    void reload() throws FileOperationException;
    E get(int index);
    int size();
}
