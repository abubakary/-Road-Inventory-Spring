package tz.go.tarura.sharedUtils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

	private List<Integer> code;
	private Boolean isSuccess;
	private T data;
	
	
	public ResponseData(List<Integer> code, Boolean isSuccess) {
		this.code = code;
		this.isSuccess = isSuccess;
	}
	
	
}
