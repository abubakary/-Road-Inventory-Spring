package com.tarura.RoadInventory.Repositories;

import com.tarura.RoadInventory.Entities.RoadLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadLineRepository extends JpaRepository<RoadLine, String> {

//    @Query(value ="", nativeQuery = true)
//    List<RoadLine> findRoadLineBy(String applicationStatusCode, String applicationCategoryCode, String councilCode);

    @Query(value = "SELECT geometry FROM SD_User WHERE roadCode = ?1", nativeQuery = true)
    Geometry saveGeometry(String roadCode);

    public static interface Geometry {

        String getGeometry();

    }
}
