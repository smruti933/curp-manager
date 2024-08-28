package io.mosip.curp.service;


import io.mosip.curp.entity.CurpBioData;
import io.mosip.curp.helper.CurpManagerLogger;
import io.mosip.curp.repository.CurpBioDataRepository;

import io.mosip.kernel.core.logger.spi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurpService {

    private static final Logger LOGGER = CurpManagerLogger.getLogger(CurpService.class);

    @Autowired
    private CurpBioDataRepository curpBioDataRepository;

    public String updateCurpBioData(CurpBioData curpBioData) {
        try {
            curpBioDataRepository.save(curpBioData);
            return "CurpBioData updated successfully";
        } catch (DataAccessException e) {
            LOGGER.error("Error updating CurpBioData", e);
            return "Error updating CurpBioData: " + e.getMessage();
        }
    }

    public CurpBioData findCurpBioDataById(String curp_id) {

        Optional<CurpBioData> curpId = curpBioDataRepository.findByCurpId(curp_id);
        return curpId.orElse(null);
    }
}

