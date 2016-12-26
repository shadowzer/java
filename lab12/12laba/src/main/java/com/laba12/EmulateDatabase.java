package com.laba12;


import java.io.Serializable;
import java.util.*;

public class EmulateDatabase <E extends Serializable> extends AbstractList<E> {

    private Map<String,List<E>> table=new HashMap<String, List<E>>();
    private String name;
    private List list=new ArrayList();

    public EmulateDatabase(String tableName){
        this.name=tableName;

        if (Database.isExist(name)) {
            list.clear();
            load();
        }
        else {
            Database.tables.put(tableName,new LinkedList<Object>());
        }
    }

    public void load() {
            list.addAll(Database.tables.get(name).subList(0, Database.tables.get(name).size()));
    }

    public void reload() throws FileOperationException {
        list.clear();
        load();
    }
    private void deleteFromTable(int id) {
        Database.tables.get(name).remove(id);
    }

    private void saveToTable(){
        Database.tables.get(name).clear();
        Database.tables.get(name).addAll(list.subList(0,list.size()));
    }

    private void updateTable(int id, E elem){
        Database.tables.get(name).set(id,elem);
    }
    @Override
    public E get(int index) {
        return (E) list.get(index);
    }

    @Override
    public E set(int index, E element) {
        list.set(index, element);
        updateTable(index, element);
        return this.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveToTable();
    }
    @Override
    public boolean add(E elem) {
        list.add(elem);
        saveToTable();
        return false;
    }

    @Override
    public E remove(int index) {
        E obj=(E) this.list.remove(index);
        deleteFromTable(index);
        return obj;
    }
}
