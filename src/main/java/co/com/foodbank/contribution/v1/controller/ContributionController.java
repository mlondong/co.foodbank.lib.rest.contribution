package co.com.foodbank.contribution.v1.controller;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.com.foodbank.contribution.dto.DetailContributionDTO;
import co.com.foodbank.contribution.dto.GeneralContributionDTO;
import co.com.foodbank.contribution.dto.interfaces.IContribution;
import co.com.foodbank.contribution.exception.ContributionErrorException;
import co.com.foodbank.contribution.exception.ContributionNotFoundException;
import co.com.foodbank.contribution.service.ContributionService;
import co.com.foodbank.contribution.v1.model.Contribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.service
 *         10/06/2021
 */
@Controller
public class ContributionController {

    @Autowired
    private ContributionService service;



    /**
     * Method to find all Contributions.
     * 
     * @return {@code Collection<IContribution>}
     */
    public Collection<IContribution> findAll()
            throws ContributionNotFoundException {
        return service.findAll();
    }



    /**
     * Method to find by Id.
     * 
     * @param _id
     * @return {@code IContribution}
     */
    public Contribution findById(String _id)
            throws ContributionNotFoundException {
        return service.findById(_id);
    }



    /**
     * Method to find by codeBar DetailContributions.
     * 
     * @param code
     * @return {@code IContribution}
     */
    public Contribution findByCodeBarContribution(String code)
            throws ContributionNotFoundException, ContributionErrorException {
        return service.findByCodeBar(code);
    }



    /**
     * Method to create an Contribution detail
     * 
     * @param dto
     * @return {@code IContribution}
     */
    public IContribution createDC(@Valid DetailContributionDTO dto)
            throws ContributionErrorException {
        return service.createDC(dto);
    }



    /**
     * Method to update a Contribution detail
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution updateDC(@Valid DetailContributionDTO dto, String _id)
            throws ContributionNotFoundException, ContributionErrorException {
        return service.updateDC(dto, _id);
    }



    /**
     * Method to create A GeneralContribution.
     * 
     * @param dto
     * @return {@code IContribution}
     */
    public IContribution createGC(@Valid GeneralContributionDTO dto)
            throws ContributionErrorException {
        return service.createGC(dto);
    }



    /**
     * Method to update GereralContribution.
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution updateGC(@Valid GeneralContributionDTO dto, String _id)
            throws ContributionNotFoundException, ContributionErrorException {
        return service.updateGC(dto, _id);
    }



    /**
     * Method to update state Contribution
     * 
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution upState(String _id, String option) {
        return service.upState(_id, option);
    }



}
