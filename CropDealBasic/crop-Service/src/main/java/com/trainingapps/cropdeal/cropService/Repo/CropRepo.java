package com.trainingapps.cropdeal.cropService.Repo;

import com.trainingapps.cropdeal.cropService.Model.Crop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepo extends JpaRepository <Crop, Integer>{
//    public List<Crop> findByName(String CropName);
//    public List<Crop> findByType(String CropType);
	public List<Crop> findByFarmerId(int farmer_id);

}
