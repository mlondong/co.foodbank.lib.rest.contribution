package co.com.foodbank.contribution.v1.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.com.foodbank.contribution.dto.DetailContributionDTO;
import co.com.foodbank.contribution.dto.GeneralContributionDTO;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.service.ContributionService;
import co.com.foodbank.contribution.v1.model.Contribution;

@Controller
public class ContributionController {

    @Autowired
    private ContributionService service;



    /**
     * Method to find all Contributions.
     * 
     * @return {@code Collection<IContribution>}
     */
    public Collection<IContribution> findAll() {
        return service.findAll();
    }



    /**
     * Method to find by Id.
     * 
     * @param _id
     * @return {@code IContribution}
     */
    public Contribution findById(String _id) {
        return service.findById(_id);
    }



    /**
     * Method to find by codeBar DetailContributions.
     * 
     * @param code
     * @return {@code IContribution}
     */
    public Contribution findByCodeBarContribution(String code) {
        return service.findByCodeBar(code);
    }



    /**
     * Method to create an Contribution detail
     * 
     * @param dto
     * @return {@code IContribution}
     */
    public IContribution createDC(DetailContributionDTO dto) {
        return service.createDC(dto);
    }



    /**
     * Method to update a Contribution detail
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution updateDC(DetailContributionDTO dto, String _id) {
        return service.updateDC(dto, _id);
    }



    /**
     * Method to create A GeneralContribution.
     * 
     * @param dto
     * @return {@code IContribution}
     */
    public IContribution createGC(GeneralContributionDTO dto) {
        return service.createGC(dto);
    }



    /**
     * Method to update GereralContribution.
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution updateGC(GeneralContributionDTO dto, String _id) {
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
