package io.mosip.curp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.curp.dto.MatchedCurpDto;
import io.mosip.curp.helper.CurpBioHelper;
import io.mosip.curp.helper.CurpManagerLogger;
import io.mosip.kernel.core.logger.spi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/curp-bio-data")
public class CurpBioController {

    private static final Logger LOGGER = CurpManagerLogger.getLogger(CurpBioController.class);

    @Autowired
    private CurpBioHelper curpBioHelper;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/updatematch")
    public ResponseEntity<String> updateCurpBioData(@RequestBody @Valid MatchedCurpDto matchedCurpDto) throws JsonProcessingException {
        try {
            LOGGER.info("CurpBioController::updateCurpBioData entry, request {}", mapper.writeValueAsString(matchedCurpDto));
            String response = curpBioHelper.updateCurpBioData(matchedCurpDto);
            if (response.equals("CurpBioData and MatchedCurp updated successfully")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception on updateCurpBioData", ex);
            throw ex;
        }
    }

    @PostMapping("/updatestatus")
    public String updateCurpStatus(@RequestBody @Valid MatchedCurpDto matchedCurpDto) throws JsonProcessingException {
        try {

            LOGGER.info("CurpBioController::updateCurpStatus entry, request: {}", mapper.writeValueAsString(matchedCurpDto));
            return curpBioHelper.findAndUpdateCurpStatus(matchedCurpDto);
        } catch (Exception ex) {
            LOGGER.error("Exception on updateCurpStatus", ex);
            throw ex;
        }
    }

}