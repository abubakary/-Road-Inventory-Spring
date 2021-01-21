package tz.go.tarura.sharedUtils;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class BillDto {

	private String billId;

	private String approvedBy;

	private String billDescription;

	private boolean billPayed;

	private String billReference;

	private BigDecimal billedAmount;

	private String billControlNumber;

	private BigDecimal billEquivalentAmount;

	private Date expiryDate;

	private Date generatedDate;

	private BigDecimal miscellaneousAmount;

	private String payerEmail;

	private String remarks;

	private String payerPhone;

	private String payerName;

	private boolean reminderFlag;

	private String spSystemId;

	private String billPayType;

	private LocalDate receivedTime;

	private String currency;

	private String applicationId;

	private String collectionCode;

	private String type;

	private String createdBy;

	private String itemId;

	private String parkingDetailsId;

	List<BillItem> billItems;
}
