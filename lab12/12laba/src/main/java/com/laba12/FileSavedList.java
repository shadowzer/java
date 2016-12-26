package com.laba12;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class FileSavedList<E extends Serializable> extends AbstractList<E> implements SavedList {
    private ArrayList list;
    private File file;

    public FileSavedList(File f) {
        list=new ArrayList();
        if (f!=null) {
            this.file = f;
            reload();
        }
        else{
            file=new File ("list.dat");
        }

    }
    public void writeInFile(){
        try
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(list);
            outputStream.close();
        }
        catch (Exception ex){}


    }
    public void reload() throws FileOperationException {
        this.list.clear();
        try {
            FileInputStream fileStream = new FileInputStream(this.file);
            ObjectInputStream inputStream = new ObjectInputStream(fileStream);
            while(true) {
                try {
                    this.list = (ArrayList) inputStream.readObject();
                }
                catch(EOFException ex) {
                    inputStream.close();
                    break;
                }
            }
            fileStream.close();
        }
        catch (Exception e) {}
    }

    @Override
    public E get(int index) {
        Object obj=null;
        try {
            obj=list.get(index);
        }
        catch (Exception e){}
        return (E)obj;
    }

    @Override
    public E set(int index, E element) {
        this.list.set(index,element);
        writeInFile();
        return this.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        writeInFile();
    }

    @Override
    public E remove(int index) {
        Object obj=this.list.get(index);
        this.list.remove(index);
        writeInFile();
        return (E)obj;
    }
}
