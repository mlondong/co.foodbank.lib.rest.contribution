package co.com.foodbank.contribution.service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.foodbank.contribution.dto.ContributionData;
import co.com.foodbank.contribution.dto.DetailContributionDTO;
import co.com.foodbank.contribution.dto.GeneralContributionDTO;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.exception.ContributionNotFoundException;
import co.com.foodbank.contribution.repository.ContributionRepository;
import co.com.foodbank.contribution.state.Pending;
import co.com.foodbank.contribution.v1.model.Contribution;
import co.com.foodbank.contribution.v1.model.DetailContribution;
import co.com.foodbank.contribution.v1.model.GeneralContribution;
import co.com.foodbank.vehicule.dto.Volume;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.service
 *         10/06/2021
 */
@Service
@Transactional
public class ContributionService {

    @Autowired
    private ContributionRepository repository;

    @Autowired
    private ModelMapper modelMapper;



    /**
     * @return {@code Collection<IContribution>}
     */
    public Collection<IContribution> findAll() {
        return repository.findAll().stream()
                .map(d -> modelMapper.map(d, IContribution.class))
                .collect(Collectors.toList());
    }



    /**
     * Method to get Contribution by ID
     * 
     * @param _id
     * @return {@code IContribution}
     */
    public Contribution findById(String _id)
            throws ContributionNotFoundException {

        return repository.findById(_id)
                .orElseThrow(() -> new ContributionNotFoundException(_id));
    }



    /**
     * Method to find by CodeBar Contribution.
     * 
     * @param code
     * @return {@code IContribution}
     */
    public Contribution findByCodeBar(String code)
            throws ContributionNotFoundException {

        Contribution data = repository.findByCodeBar(code);

        if (Objects.isNull(data)) {
            throw new ContributionNotFoundException(code);
        }

        return data;
    }



    /**
     * Method to create A DetailContribution.
     * 
     * @param dto
     * @return {@code Contribution }
     */
    public Contribution createDC(DetailContributionDTO dto) {
        DetailContribution context = convertOf(dto);
        ContributionData data = setInitState(context);
        return repository.save(modelMapper.map(data, DetailContribution.class));
    }



    private ContributionData setInitState(DetailContribution context) {
        // Generate a ContributionData to handle state.
        ContributionData data =
                modelMapper.map(context, ContributionData.class);

        // Set a State Pending in the Contribution.
        Pending statePending = new Pending();
        statePending.pending(data);
        return data;
    }


    private DetailContribution convertOf(DetailContributionDTO dto) {
        // Change to DetailContribution from DTO to save.
        DetailContribution context =
                modelMapper.map(dto, DetailContribution.class);
        return context;
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @param _id
     * @return {@code Contribution }
     */
    public Contribution updateDC(DetailContributionDTO dto, String _id) {

        Contribution query = repository.findById(_id)
                .orElseThrow(() -> new ContributionNotFoundException(_id));

        DetailContribution detail =
                modelMapper.map(query, DetailContribution.class);
        detail.setCodeBar(dto.getCodeBar());
        detail.setDate(dto.getDate());
        detail.setDescription(dto.getDescription());
        detail.setNumOfPackage(Long.valueOf(dto.getNumOfPackage()));
        return repository.save(detail);
    }



    /**
     * Method to create General Contribution.
     * 
     * @param dto
     * @return {@code Contribution}
     */
    public Contribution createGC(GeneralContributionDTO dto) {
        GeneralContribution context = convertOfGC(dto);
        ContributionData data = setInitStateGC(context);
        return repository
                .save(modelMapper.map(data, GeneralContribution.class));
    }



    private ContributionData setInitStateGC(GeneralContribution context) {
        ContributionData data =
                modelMapper.map(context, ContributionData.class);

        // Set a State Pending in the Contribution.
        Pending statePending = new Pending();
        statePending.pending(data);
        return data;
    }



    private GeneralContribution convertOfGC(GeneralContributionDTO dto) {
        return modelMapper.map(dto, GeneralContribution.class);
    }



    /**
     * Method to update General Contribution.
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution updateGC(GeneralContributionDTO dto, String _id) {

        Contribution query = repository.findById(_id)
                .orElseThrow(() -> new ContributionNotFoundException(_id));

        GeneralContribution detail =
                modelMapper.map(query, GeneralContribution.class);
        detail.setDate(dto.getDate());
        detail.setDescription(dto.getDescription());
        detail.setVolume(modelMapper.map(dto.getVolume(), Volume.class));

        return repository.save(detail);
    }



}
