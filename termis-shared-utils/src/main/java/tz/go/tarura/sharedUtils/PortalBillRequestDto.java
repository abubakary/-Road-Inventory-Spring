package tz.go.tarura.sharedUtils;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PortalBillRequestDto {

  
    private  String phoneNumber;

   
    private String email;

    private String ccy;  

    private String billDescription;

    private String PayerName;

    @Nullable
    private  String applicationId;

    private List<PortalBillItemDto> billItems;

   
    private String councilCode;

  
    private String regionCode;


    private  String type;

    private String itemId;

 
    private String createdBy;

    private String approvedBy;

}
