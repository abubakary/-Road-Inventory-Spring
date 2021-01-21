package com.tarura.RoadInventory.Repositories;

import com.tarura.RoadInventory.Entities.RoadLine;
import com.tarura.RoadInventory.Entities.RoadPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadPointsRepository extends JpaRepository<RoadPoints, String> {

//    @Query(value ="", nativeQuery = true)
//    List<RoadLine> findByApplicationStatusAndItemGroupCodeAndCouncilCode(String applicationStatusCode, String applicationCategoryCode, String councilCode);

    //@Query(value = "SELECT geometry FROM SD_User WHERE roadCode = ?1", nativeQuery = true)
    @Query(value = "SELECT ST_SetSRID(ST_MakePoint(?1, ?2), 4326) as geometry;", nativeQuery = true)
    Geometry saveGeometry(Double latitude, Double longitude);

    public static interface Geometry {

        String getGeometry();

    }
}
