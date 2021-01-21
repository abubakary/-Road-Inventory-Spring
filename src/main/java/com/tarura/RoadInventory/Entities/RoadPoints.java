package com.tarura.RoadInventory.Entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import tz.go.tarura.sharedUtils.CustomGeneratedData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trr_road_points")
public class RoadPoints implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private String id = CustomGeneratedData.GenerateUniqueID();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_code", referencedColumnName = "road_code")
    private RoadLine roadLine;

    @NotNull
    @Column(name="latitude", unique =  false, nullable = false)
    private double latitude;

    @NotNull
    @Column(name="longitude", unique =  false, nullable = false)
    private double longitude;

    @NotNull
    @Column(name="accuracy", unique =  false, nullable = false)
    private double accuracy;

    @NotNull
    @Column(name="altitude", unique =  false, nullable = false)
    private double altitude;

    @NotNull
    @Column(name="speed", unique =  false, nullable = false)
    private double speed;

    @NotNull
    @Column(name="speedAccuracy", unique =  false, nullable = false)
    private double speedAccuracy;

    @NotNull
    @Column(name="heading", unique =  false, nullable = false)
    private double heading;

    @NotNull
    @Column(name="time", unique =  false, nullable = false)
    private double time;

   // @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name="geometry", columnDefinition = "Geometry", nullable = true)
    private Geometry geometry;

}
