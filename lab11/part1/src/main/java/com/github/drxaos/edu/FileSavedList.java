package com.github.drxaos.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.AbstractList;



public class FileSavedList<E extends Serializable> extends AbstractList<E> implements Reloadable {

    private ArrayList<E> savedList = new ArrayList<E>();
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileSavedList(File file) {
        if (file.exists()) {
            this.file = file;
            reload();
        } else {
            this.file = new File(file.toString());
        }
    }

	private void save() {
        try {
            FileOutputStream fileos = new FileOutputStream(file);
            ObjectOutputStream objos = new ObjectOutputStream(fileos);
            objos.writeObject(savedList);
            fileos.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
	
    public void reload() throws FileOperationException {
        if (!file.exists())
            savedList.clear();
        try {
            FileInputStream fileis = new FileInputStream(file);
            ObjectInputStream objis = new ObjectInputStream(fileis);
            savedList = (ArrayList<E>) objis.readObject();
            objis.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        savedList.set(index,element);
        save();
        return element;
    }

    @Override
    public int size() { return savedList.size(); }

    @Override
    public void add(int index, E element) {
        savedList.add(index,element);
        save();
    }

    @Override
    public E remove(int index) {
        E element = savedList.get(index);
        savedList.remove(index);
        save();
        return element;
    }
}