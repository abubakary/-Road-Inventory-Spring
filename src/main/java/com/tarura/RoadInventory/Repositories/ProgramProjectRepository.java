package com.tarura.RoadInventory.Repositories;

import com.tarura.RoadInventory.Entities.ProgramProjects;
import com.tarura.RoadInventory.Entities.RoadPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramProjectRepository extends JpaRepository<ProgramProjects, String> {

}
