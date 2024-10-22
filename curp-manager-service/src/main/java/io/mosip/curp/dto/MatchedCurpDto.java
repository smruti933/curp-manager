package io.mosip.curp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchedCurpDto {
    private String curpId;
    private List<String> matchedCurpIds;
    private String statusCode;
    private String statusComment;
    private String curpStatus;
    private boolean isLatestBio;
}
