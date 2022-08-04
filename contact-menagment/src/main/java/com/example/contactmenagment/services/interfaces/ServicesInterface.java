package com.example.contactmenagment.services.interfaces;

import java.util.List;
import java.util.UUID;

public interface ServicesInterface<T>{

    void deleteByUid(UUID uid);

    List<T> getAll();

    T getByUid(UUID uid);

    T save(T t);

}
