package jsu.edu.mcis.cs408.lab5;

import androidx.annotation.NonNull;

public class Memo {
    private int id;
    private String name;

    public Memo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Memo(String name) {
       this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
