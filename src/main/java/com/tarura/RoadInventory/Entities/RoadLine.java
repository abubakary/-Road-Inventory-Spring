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
@Table(name = "trr_road_line")
public class RoadLine implements Serializable {

    @Id
    @Column(name = "road_code", unique = true, nullable = false)
    private String roadCode;

    @Column(name="geometry", columnDefinition = "Geometry", nullable = true)
    private Geometry geometry;
}
