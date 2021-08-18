package co.com.foodbank.contribution.restcontroller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.foodbank.contribution.dto.DetailContributionDTO;
import co.com.foodbank.contribution.dto.GeneralContributionDTO;
import co.com.foodbank.contribution.dto.interfaces.IContribution;
import co.com.foodbank.contribution.exception.ContributionErrorException;
import co.com.foodbank.contribution.exception.ContributionNotFoundException;
import co.com.foodbank.contribution.v1.controller.ContributionController;
import co.com.foodbank.contribution.v1.model.Contribution;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/contribution")
@Tag(name = "Contribution", description = "the contribution API")
@Validated
public class ContributionRestController {



    @Autowired
    public ContributionController controller;



    /**
     * Method to get all Contributions.
     * 
     * @return {@code Collection<IContribution>}
     */
    @Operation(summary = "Find all Contributions.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the contribution",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findAll")
    public ResponseEntity<Collection<IContribution>> findAllContribution()
            throws ContributionNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(controller.findAll());
    }



    /**
     * Method to get Contribution by Id.
     * 
     * @param _id
     * @return {@code IContribution}
     */
    @Operation(summary = "Find Contribution by Id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the Contributon",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<IContribution> findByIdContribution(
            @PathVariable("id") @NotBlank @NotNull String _id)
            throws ContributionNotFoundException {

        System.out.println("LLEGOOOOOOOOOOOOOOOOOOOOOOOO " + _id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.findById(_id));
    }



    /**
     * Method to search contribution by bar-code.
     * 
     * @param code
     * @return {@code Contribution}
     */
    @Operation(summary = "Find Contribution by bar-code.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the Contributon",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/codeBar/{code}")
    public ResponseEntity<IContribution> findByCodeBarContribution(
            @PathVariable("code") @NotBlank @NotNull String code)
            throws ContributionNotFoundException, ContributionErrorException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.findByCodeBarContribution(code));
    }



    /**
     * Method to create a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @Operation(summary = "Add a new Detail Contribution", description = "",
            tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Contribution created",
                    content = @Content(schema = @Schema(
                            implementation = Contribution.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409",
                    description = "Contribution already exists")})
    @PostMapping(value = "/createDetailContribution",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> createDetailContribution(
            @RequestBody @Valid DetailContributionDTO dto)
            throws ContributionErrorException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.createDC(dto));
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */

    @Operation(summary = "Update Detail Contribution", description = "",
            tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Detail Contribution updated",
                    content = @Content(schema = @Schema(
                            implementation = Contribution.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409",
                    description = "Contribution already exists")})
    @PutMapping(value = "/updateDetailContribution/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> updateDC(
            @RequestBody @Valid DetailContributionDTO dto,
            @PathVariable("id") @NotBlank @NotNull String _id)
            throws ContributionNotFoundException, ContributionErrorException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.updateDC(dto, _id));
    }



    /**
     * Method to create a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @Operation(summary = "Add a new General Contribution", description = "",
            tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "General Contribution updated",
                    content = @Content(schema = @Schema(
                            implementation = Contribution.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409",
                    description = "General Contribution already exists")})
    @PostMapping(value = "/createGeneralContribution",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> createGeneralContribution(
            @RequestBody @Valid GeneralContributionDTO dto)
            throws ContributionErrorException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.createGC(dto));
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @Operation(summary = "Update General Contribution", description = "",
            tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "General Contribution updated",
                    content = @Content(schema = @Schema(
                            implementation = Contribution.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409",
                    description = "General Contribution already exists")})
    @PutMapping(value = "/updateGeneralContribution/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> updateGC(
            @RequestBody @Valid GeneralContributionDTO dto,
            @PathVariable("id") @NotBlank @NotNull String _id)
            throws ContributionNotFoundException, ContributionErrorException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.updateGC(dto, _id));
    }



    /**
     * Method to update the state in contributions (General or Detail) .
     * 
     * @param _id
     * @return {@code ResponseEntity<IContribution>}
     */
    @Operation(
            summary = "Update only state in Contributions: 1. Pending , 2. PickedUp, 3. In House, 4. Shipment, 5. Delivered.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Update the contribution",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/upState/{id}/option/{option}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<IContribution> upState(
            @NotBlank @NotNull @PathVariable("id") String _id,
            @NotBlank @NotNull @PathVariable("option") @Pattern(
                    regexp = "^[1-5]{1,1}$",
                    message = "Option only between 1-5.") String option) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.upState(_id, option));
    }



}
