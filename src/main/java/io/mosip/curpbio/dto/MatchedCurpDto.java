package io.mosip.curpbio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchedCurpDto {
    private String curpId;
    private String matchedCurpIds;
    private String statusCode;
    private String statusComment;
    private String crupStatus;
}
