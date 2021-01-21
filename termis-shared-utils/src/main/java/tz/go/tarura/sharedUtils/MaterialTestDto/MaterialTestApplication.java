package tz.go.tarura.sharedUtils.MaterialTestDto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialTestApplication{

    private String fileNumber;

    private SampleDisposalDto sampleDisposal;

    private String specialInstructions;

    private String backgroundStudy;

    private ResultsRequiredDto resultsRequired;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateRequired;

    private String description;

  
    private String approvedBy;


    private Applicant applicant;


    private String sampleDescription;
    
    private List<MaterialTests> materialTest;


}
