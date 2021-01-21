package com.tarura.RoadInventory.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trr_region_districts")
public class RegionDistricts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name="districtcode", nullable = true, unique = true)
    private String districtCode;

    @Column(name="districtname", nullable = true, unique = true)
    private String districtName;

    @Column(name="regionname", nullable = true)
    private String regionName;

}
