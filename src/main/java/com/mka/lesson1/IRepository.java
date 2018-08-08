package com.mka.lesson1;

import java.util.List;

public interface IRepository<T> {
    void insert(T item);
    void delete(int id);
    void update(T item);
    T get(int id);
    List<T> get();
}

class UsersRepository implements IRepository<Users> {

    public void insert(Users item) {

    }

    public void delete(int id) {

    }

    public void update(Users item) {

    }

    public Users get(int id) {
        return null;
    }

    public List<Users> get() {
        return null;
    }
}
