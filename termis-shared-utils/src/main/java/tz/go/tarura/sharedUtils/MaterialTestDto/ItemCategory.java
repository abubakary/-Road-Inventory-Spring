package tz.go.tarura.sharedUtils.MaterialTestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ItemCategory implements Serializable {
	
    private static final long serialVersionUID = 1L;



	private ItemGroup itemGroup;

	private String code;


	private String name;

}
