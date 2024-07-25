package io.mosip.curpbio.controller;

import io.mosip.curpbio.dto.MatchedCurpDto;
import io.mosip.curpbio.helper.CurpBioHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/curp-bio-data")
public class CurpBioController {

    @Autowired
    private CurpBioHelper curpBioHelper;

    @PostMapping("/updatematch")
    public ResponseEntity<String> updateCurpBioDataStatus(@RequestBody @Valid MatchedCurpDto matchedCurpDto) {
        String response = curpBioHelper.updateCurpBioDataStatus(matchedCurpDto);
        if (response.equals("CurpBioData and MatchedCurp updated successfully")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}