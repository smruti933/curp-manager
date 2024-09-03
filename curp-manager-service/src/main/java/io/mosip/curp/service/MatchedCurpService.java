package io.mosip.curp.service;

import io.mosip.curp.entity.MatchedCurp;
import io.mosip.curp.helper.CurpManagerLogger;
import io.mosip.curp.repository.MatchedCurpRepository;
import io.mosip.kernel.core.logger.spi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class MatchedCurpService {

    private static final Logger LOGGER = CurpManagerLogger.getLogger(MatchedCurpService.class);

    @Autowired
    private MatchedCurpRepository matchedCurpRepository;


    public MatchedCurp findCurpId(String curpId) {
        return matchedCurpRepository.findByCurpId(curpId);
    }


    public String updateMatchedCurp(MatchedCurp matchedCurp) {
        try {
            matchedCurpRepository.save(matchedCurp);
            return "MatchedCurp updated successfully";
        } catch (DataAccessException e) {
            LOGGER.error("Error updating MatchedCurp", e);
            return "Error updating MatchedCurp: " + e.getMessage();
        }
    }

    public String saveMatchedCurp(MatchedCurp newMatchedCurp) {
        try {
            matchedCurpRepository.save(newMatchedCurp);
            return "MatchedCurp saved successfully";
        } catch (DataAccessException e) {
            LOGGER.error("Error saving MatchedCurp", e);
            return "Error saving MatchedCurp: " + e.getMessage();
        }
    }

    public MatchedCurp findMatchedCurpId(String matchedCurpIds) {
        return matchedCurpRepository.findByMatchedCurpIds(matchedCurpIds);
    }
}
