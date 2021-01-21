package tz.go.tarura.sharedUtils;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentNoticationDto {
	
	private String paymentId;
	private String billId;
	private String applicationId;
	private BigDecimal paidAmt;
	private BigDecimal billAmt;
	private String payCtrNum;
	
}
