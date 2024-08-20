package io.mosip.curp.service;

import io.mosip.curp.entity.MatchedCurp;
import io.mosip.curp.repository.MatchedCurpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MatchedCurpService {
    private static final Logger LOGGER = Logger.getLogger(MatchedCurpService.class.getName());

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
            LOGGER.log(Level.SEVERE, "Error updating MatchedCurp", e);
            return "Error updating MatchedCurp: " + e.getMessage();
        }
    }

    public String saveMatchedCurp(MatchedCurp newMatchedCurp) {
        try {
            matchedCurpRepository.save(newMatchedCurp);
            return "MatchedCurp saved successfully";
        } catch (DataAccessException e) {
            LOGGER.log(Level.SEVERE, "Error saving MatchedCurp", e);
            return "Error saving MatchedCurp: " + e.getMessage();
        }
    }

    public MatchedCurp findMatchedCurpId(String matchedCurpIds) {
        MatchedCurp matchedCurpId = matchedCurpRepository.findByMatchedCurpIds(matchedCurpIds);
            return matchedCurpId;
    }
}
