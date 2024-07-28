package io.mosip.curpbio.repositary;

import io.mosip.curpbio.entity.CurpBioData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CurpBioDataRepository extends JpaRepository<CurpBioData, String> {


    Optional<CurpBioData> findByCurpId(String curpId);
}

