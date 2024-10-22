package io.mosip.curp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "curp_bio_data",schema = "curp")
@Entity
public class CurpBioData {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "curp_id", length = 18, nullable = false)
    private String curpId;

    @Column(name = "curp_type", length = 20, nullable = false)
    private String curpType;

    @Column(name = "curp_cr_dtimes", nullable = false)
    private LocalDateTime curpCrDtimes;

    @Column(name = "bio_filepath")
    private String bioFilepath;

    @Column(name = "is_latest_bio")
    private Boolean isLatestBio;

    @Column(name = "cr_by")
    private String crBy;

    @Column(name = "cr_dtimes", nullable = false)
    private LocalDateTime crDtimes;

    @Column(name = "upd_by")
    private String updBy;

    @Column(name = "upd_dtimes")
    private LocalDateTime updDtimes;

    @Column(name = "status")
    private String curpStatus;
}