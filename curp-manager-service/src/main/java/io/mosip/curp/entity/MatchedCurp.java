package io.mosip.curp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "matched_curp", schema = "curp")
public class MatchedCurp {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "curp_id")
    private String curpId;


    @Column(name = "matched_curp_ids")
    private String matchedCurpIds;


    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "status_comment")
    private String statusComment;

    @Column(name = "is_latest_bio")
    private Boolean isLatestBio;

    @Column(name = "cr_by")
    private String createdBy;

    @Column(name = "cr_dtimes")
    private LocalDateTime createdDateTime;

    @Column(name = "upd_by")
    private String updatedBy;

    @Column(name = "upd_dtimes")
    private LocalDateTime updatedDateTime;

    public List<String> getMatchedCurpIds() {
        return Arrays.asList(matchedCurpIds.split(","));
    }

    public void setMatchedCurpIds(List<String> matchedCurpIds) {
        this.matchedCurpIds = String.join(",", matchedCurpIds);
    }
}


