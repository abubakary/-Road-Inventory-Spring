package tz.go.tarura.sharedUtils;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tz.go.tarura.sharedUtils.CustomGeneratedData;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * @ Termis Development Team
 *
 *
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BillItem {

    @Id
    @Column(nullable = false, unique = true)
    private String billItemRefId = CustomGeneratedData.GenerateUniqueID();

    private String billItemRef;

    private BigDecimal billItemAmount;

    private BigDecimal billItemMiscAmount;

    private BigDecimal billItemEqvAmount;

    private String billItemDescription;

    private String sourceName;

    private String gsfCode;

    private String parkingDetailsId;



}
