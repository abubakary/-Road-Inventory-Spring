package tz.go.tarura.sharedUtils;


import java.math.BigDecimal;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PortalBillItemDto {


    private BigDecimal billedAmount;

    private String sourceName;

    private String  gsfCode;

    @Nullable
    private String parkingDetailsId;

}
