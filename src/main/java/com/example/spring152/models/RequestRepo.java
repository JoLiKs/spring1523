package com.example.spring152.models;

import com.example.spring152.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<DataModel, Long> {
    DataModel findById(long id);

}
