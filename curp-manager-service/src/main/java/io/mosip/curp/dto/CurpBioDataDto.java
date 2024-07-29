package io.mosip.curp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurpBioDataDto {

    private String id;
    private String curpId;
    private String curpType;
    private LocalDateTime curpCrDtimes;
    private String bioFilepath;
    private String statusCode;
    private String statusComment;
    private String curpStatus;
}
