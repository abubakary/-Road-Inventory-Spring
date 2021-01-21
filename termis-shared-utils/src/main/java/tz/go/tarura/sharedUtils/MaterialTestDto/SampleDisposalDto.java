package tz.go.tarura.sharedUtils.MaterialTestDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SampleDisposalDto {



	private String code;


	private String name;
	

}
