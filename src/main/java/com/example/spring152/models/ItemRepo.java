package com.example.spring152.models;

import com.example.spring152.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepo extends JpaRepository<ItemModel, Long> {
    ItemModel findById(long id);


    @Modifying
    @Transactional
    @Query("update ItemModel s set s.name =:name WHERE s.id = :id")
    int setName(String name, long id);

    @Modifying
    @Transactional
    @Query("update ItemModel s set s.price =:price WHERE s.id = :id")
    int setPrice(String price, long id);

   // "SELECT * FROM Users u WHERE u.status = :status and u.name = :name"


}
