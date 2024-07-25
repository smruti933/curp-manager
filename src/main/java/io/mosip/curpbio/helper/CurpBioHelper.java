package io.mosip.curpbio.helper;
import io.mosip.curpbio.dto.MatchedCurpDto;
import io.mosip.curpbio.entity.CurpBioData;
import io.mosip.curpbio.entity.MatchedCurp;
import io.mosip.curpbio.service.CurpService;
import io.mosip.curpbio.service.MatchedCurpService;
import io.mosip.kernel.core.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CurpBioHelper {
    private static final Logger LOGGER = Logger.getLogger(CurpBioHelper.class.getName());
    @Autowired
    private CurpService curpService;

    @Autowired
    private MatchedCurpService matchedCrupService;

    public String updateCurpBioDataStatus(MatchedCurpDto matchedCurpDto) {
       CurpBioData curpBioData = curpService.findCurpBioDataById(matchedCurpDto.getCurpId());
        LOGGER.info("Updating CurpBioData for curpId: " + matchedCurpDto.getCurpId());
            curpBioData.setCurpStatus(matchedCurpDto.getCrupStatus());
            curpBioData.setStatusComment(matchedCurpDto.getStatusComment());
            curpBioData.setUpdDtimes(DateUtils.getUTCCurrentDateTime());

            String updateResponse = curpService.updateCurpBioData(curpBioData);
            if (!updateResponse.equals("CurpBioData updated successfully")) {
                return updateResponse;
            }
            MatchedCurp matchedCurp = matchedCrupService.findCurpId(matchedCurpDto.getCurpId());
            MatchedCurp matchedCurpId = matchedCrupService.findMatchedCurpId(matchedCurpDto.getMatchedCurpIds());
            if(matchedCurpId!=null){
                LOGGER.info("matched curpId is already exists"+matchedCurpId.getMatchedCurpIds());
            }
        else if (matchedCurp != null) {
                String updatedMatchedCurpIds = matchedCurp.getMatchedCurpIds() + "," + matchedCurpDto.getMatchedCurpIds();
                matchedCurp.setMatchedCurpIds(updatedMatchedCurpIds);
                String matchedUpdateResponse = matchedCrupService.updateMatchedCurp(matchedCurp);
                if (!matchedUpdateResponse.equals("MatchedCurp updated successfully")) {
                    return matchedUpdateResponse;
                }
            } else {
                MatchedCurp newMatchedCurp = new MatchedCurp();
                newMatchedCurp.setCurpId(matchedCurpDto.getCurpId());
                newMatchedCurp.setMatchedCurpIds(matchedCurpDto.getMatchedCurpIds());
                newMatchedCurp.setStatusCode(matchedCurpDto.getStatusCode());
                newMatchedCurp.setStatusComment(matchedCurpDto.getStatusComment());
                newMatchedCurp.setCreatedBy("TEST_USER");
                newMatchedCurp.setCreatedDateTime(DateUtils.getUTCCurrentDateTime());

                String matchedSaveResponse = matchedCrupService.saveMatchedCurp(newMatchedCurp);
                if (!matchedSaveResponse.equals("MatchedCurp saved successfully")) {
                    return matchedSaveResponse;
                }
            }
            return "CurpBioData and MatchedCurp updated successfully";
    }
}
