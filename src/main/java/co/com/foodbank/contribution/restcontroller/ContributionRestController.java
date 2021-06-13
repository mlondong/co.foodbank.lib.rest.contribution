package co.com.foodbank.contribution.restcontroller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.v1.controller.ContributionController;
import co.com.foodbank.contribution.v1.model.Contribution;

@RestController
@RequestMapping(value = "/contribution")
@Validated
public class ContributionRestController {



    @Autowired
    public ContributionController controller;



    /**
     * Method to get all Contributions.
     * 
     * @return {@code Collection<IContribution>}
     */
    @GetMapping(value = "/findAll")
    public Collection<IContribution> findAllContribution() {
        return controller.findAll();
    }



    /**
     * Method to get Contribution by Id.
     * 
     * @param _id
     * @return {@code IContribution}
     */
    @GetMapping(value = "/id/{id}")
    public Contribution findByIdContribution(
            @PathVariable("id") @NotBlank @NotNull String _id) {
        return controller.findById(_id);
    }



    /**
     * @param code
     * @return
     */
    @GetMapping(value = "/codeBar/{code}")
    public Contribution findByCodeBarContribution(
            @PathVariable("code") @NotBlank @NotNull String code) {
        return controller.findByCodeBarContribution(code);
    }



    /**
     * Method to create a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @PostMapping(value = "/createDc",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> createDetailContribution(
            @RequestBody @Valid DetailContributionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.createDC(dto));
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @PutMapping(value = "/updateDc/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> updateDC(
            @RequestBody @Valid DetailContributionDTO dto,
            @PathVariable("id") @NotBlank @NotNull String _id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.updateDC(dto, _id));
    }



    /**
     * Method to create a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @PostMapping(value = "/createGc",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> createGeneralContribution(
            @RequestBody @Valid GeneralContributionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.createGC(dto));
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @return {@code ResponseEntity<IContribution>}
     */
    @PutMapping(value = "/updateGc/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IContribution> updateGC(
            @RequestBody @Valid GeneralContributionDTO dto,
            @PathVariable("id") @NotBlank @NotNull String _id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.updateGC(dto, _id));
    }



}
