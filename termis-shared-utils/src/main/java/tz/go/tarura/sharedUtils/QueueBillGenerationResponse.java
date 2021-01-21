package tz.go.tarura.sharedUtils;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class QueueBillGenerationResponse {

    private  String billId;
    private  String applicationId;
    private String parkingDetailsId;
    private  int responseCode;
    private  List<BillItem> billItems;

}
