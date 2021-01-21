package com.tarura.RoadInventory.Dtos;

import com.sun.istack.NotNull;
import com.tarura.RoadInventory.Entities.RoadPoints;
import lombok.*;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoadPointsDto {

    private double latitude;

    private double longitude;

    private double accuracy;

    private double altitude;

    private double speed;

    private double speedAccuracy;

    private double heading;

    private double time;
}
