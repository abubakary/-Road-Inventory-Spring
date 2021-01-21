package tz.go.tarura.sharedUtils.MaterialTestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class StructureTypeDto {



    private String code;


    private String name;


    private ItemCategory itemCategory;

}
