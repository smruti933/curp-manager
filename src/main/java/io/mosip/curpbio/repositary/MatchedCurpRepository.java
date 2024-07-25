package io.mosip.curpbio.repositary;

import io.mosip.curpbio.entity.MatchedCurp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchedCurpRepository extends JpaRepository<MatchedCurp, String> {

    MatchedCurp findByCurpId(String curpId);

    @Query(value = "SELECT * FROM curp.matched_curp WHERE :matchedCurpIds = ANY(string_to_array(matched_curp_ids, ','))", nativeQuery = true)
    MatchedCurp findByMatchedCurpIds(@Param("matchedCurpIds")String matchedCrupIds);

}
