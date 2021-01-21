package com.tarura.RoadInventory.Dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tarura.RoadInventory.Entities.RoadPoints;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RoadLineDto {
    String roadCode;
    List<RoadPointsDto> pointData;
}
