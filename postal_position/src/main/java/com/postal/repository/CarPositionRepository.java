package com.postal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postal.bean.CarPosition;

public interface CarPositionRepository extends JpaRepository<CarPosition, String> {

    @Query("from CarPosition p where p.plateNo =:plateNo")
    List<CarPosition> findByName(@Param(value = "plateNo") String plateNo);
    
    @SuppressWarnings("unchecked")
	public CarPosition save(CarPosition carPosition);

}