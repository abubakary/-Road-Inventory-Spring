package com.tarura.RoadInventory.Repositories;

import com.tarura.RoadInventory.Entities.ProgramProjects;
import com.tarura.RoadInventory.Entities.RegionDistricts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDistrictRepository extends JpaRepository<RegionDistricts, String> {

}
