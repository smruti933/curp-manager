# curp-manager

----------------
Overview

The CurpBioManager Service  is responsible for updating the status of CurpBioData and managing MatchedCurp entities. 
It leverages services CurpService and MatchedCurpService to perform the necessary updates and checks.

Tools Required
===============
IntelliJ IDEA

Postman

postages dB

API Details
===========
updateCurpBioDataStatus
Endpoint: /api/curp-bio-data/updatematch

Method: POST

Description: Updates the status of CurpBioData and manages MatchedCurp entities.

(http://localhost:8081/v1/curpManager/api/curp-bio-data/updatematch)

Usage   :
-------
Update CurpBioData Status: This method updates the status of CurpBioData using the CurpService. It logs the update process and the curpId being processed.

Manage MatchedCurp: The method then checks for existing MatchedCurp records. If a match is found, it updates the existing record. If not, it creates a new MatchedCurp record with the provided data.