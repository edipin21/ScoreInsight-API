package com.example.sport_api.controllers.soccer;

import java.util.List;

import com.example.sport_api.config.OpenApiParameters;
import com.example.sport_api.models.sport.Area;
import com.example.sport_api.models.sport.AreaDto;
import com.example.sport_api.services.soccer.AreaService;
import com.example.sport_api.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soccer")
@Tag(name = "Area", description = "Endpoints for retrieving areas information")
public class AreaConrtoller {

    @Autowired
    private AreaService areaService;

    @Operation(summary = "Areas (Countries)", description = "Retrieves a list of all areas available.  \n"
            + "Recommended Call Interval: 4 Hours.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful response", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Area.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema()) }) })
    @GetMapping("/scores/areas")
    public ResponseEntity<?> retriveAllArea(
            @Parameter(description = OpenApiParameters.API_KEY_DESCRIPTION) @RequestParam String key) {

        try {
            List<AreaDto> areaDtos = areaService.getAllAreasWithCompetitions();
            return ResponseUtil.createOkResponse(areaDtos);
        } catch (DataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }

    }

}
