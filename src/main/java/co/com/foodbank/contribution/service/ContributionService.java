package co.com.foodbank.contribution.service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.foodbank.contribution.dto.ContributionData;
import co.com.foodbank.contribution.dto.DetailContributionDTO;
import co.com.foodbank.contribution.dto.GeneralContributionDTO;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.dto.IStateContribution;
import co.com.foodbank.contribution.exception.ContributionErrorException;
import co.com.foodbank.contribution.exception.ContributionNotFoundException;
import co.com.foodbank.contribution.repository.ContributionRepository;
import co.com.foodbank.contribution.state.Delyvered;
import co.com.foodbank.contribution.state.InHouse;
import co.com.foodbank.contribution.state.Pending;
import co.com.foodbank.contribution.state.PickedUp;
import co.com.foodbank.contribution.state.Shipment;
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


    private static final int STATE_PENDING = 1;
    private static final int STATE_PICKED_UP = 2;
    private static final int STATE_IN_HOUSE = 3;
    private static final int STATE_SHIPMENT = 4;
    private static final int STATE_DELIVERED = 5;

    private static final String ERROR_DC = "Is not a DetailContribution";
    private static final String ERROR_GC = "Is not a GeneralContribution";



    /**
     * @return {@code Collection<IContribution>}
     */
    public Collection<IContribution> findAll()
            throws ContributionNotFoundException {
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
     * @throws ContributionErrorException
     */
    public Contribution findByCodeBar(String code)
            throws ContributionNotFoundException, ContributionErrorException {

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
    public Contribution createDC(@Valid DetailContributionDTO dto)
            throws ContributionErrorException {
        DetailContribution context = convertOf(dto);
        ContributionData data = setInitState(context);
        return repository.save(modelMapper.map(data, DetailContribution.class));
    }



    private ContributionData setInitState(DetailContribution context) {
        ContributionData data = fromDetailContributionToCD(context);
        checkOptionState(Integer.valueOf(STATE_PENDING), data);
        return data;
    }



    /**
     * Method to Update a DetailContribution.
     * 
     * @param dto
     * @param _id
     * @return {@code Contribution }
     */
    public Contribution updateDC(@Valid DetailContributionDTO dto, String _id)
            throws ContributionNotFoundException, ContributionErrorException {

        Contribution queryDb = findById(_id);
        if (!checkInstansOfDC(queryDb)) {
            String er = new String(_id + " " + ERROR_DC);
            throw new ContributionErrorException(er);
        }

        DetailContribution detail = fromContributionToDC(queryDb);
        detail.setCodeBar(dto.getCodeBar());
        detail.setDate(dto.getDate());
        detail.setDescription(dto.getDescription());
        detail.setNumOfPackage(Long.valueOf(dto.getNumOfPackage()));
        return repository.save(detail);
    }



    /**
     * Check type object
     * 
     * @param obj
     * @return {@code Boolean}
     */
    private boolean checkInstansOfDC(Contribution obj) {
        return (obj instanceof DetailContribution) ? true : false;
    }



    /**
     * Method to create General Contribution.
     * 
     * @param dto
     * @return {@code Contribution}
     */
    public Contribution createGC(@Valid GeneralContributionDTO dto)
            throws ContributionErrorException {
        GeneralContribution context = convertOfGC(dto);
        ContributionData data = setInitStateGC(context);
        return repository.save(fromContributionDataToGC(data));
    }



    private ContributionData setInitStateGC(GeneralContribution context) {
        ContributionData data = fromGeneralContributionToCD(context);
        checkOptionState(Integer.valueOf(STATE_PENDING), data);
        return data;
    }



    /**
     * Method to update General Contribution.
     * 
     * @param dto
     * @param _id
     * @return {@code IContribution}
     * @throws ContributionErrorException
     */
    public IContribution updateGC(@Valid GeneralContributionDTO dto, String _id)
            throws ContributionNotFoundException, ContributionErrorException {

        Contribution query = findById(_id);

        if (!checkInstansOfGC(query)) {
            String er = new String(_id + " " + ERROR_GC);
            throw new ContributionErrorException(er);
        }

        GeneralContribution detail = fromContributionToGC(query);
        detail.setDate(dto.getDate());
        detail.setDescription(dto.getDescription());
        detail.setVolume(modelMapper.map(dto.getVolume(), Volume.class));

        return repository.save(detail);
    }



    private boolean checkInstansOfGC(Contribution obj) {
        return (obj instanceof GeneralContribution) ? true : false;
    }



    private IContribution safeOfPickedState(DetailContribution gDetail,
            String _id, String option) {
        ContributionData cData = fromDetailContributionToCD(gDetail);
        checkOptionState(Integer.valueOf(option), cData);
        DetailContribution result = fromContributionDataToDC(cData);
        result.setId(_id);
        result.setDate(new Date());
        return repository.save(result);
    }



    private IContribution safeOfPickedState(GeneralContribution gContribution,
            String _id, String option) {

        ContributionData cData = fromGeneralContributionToCD(gContribution);
        checkOptionState(Integer.valueOf(option), cData);
        GeneralContribution result = fromContributionDataToGC(cData);
        result.setId(_id);
        result.setDate(new Date());
        return repository.save(result);
    }



    /**
     * Method to update an State in Contributions.
     * 
     * @param _id
     * @return {@code IContribution}
     */
    public IContribution upState(String _id, String option) {

        Contribution query = findById(_id);

        if (isInstance(query, GeneralContribution.class)) {
            GeneralContribution gContribution = fromContributionToGC(query);
            return safeOfPickedState(gContribution, _id, option);

        } else {
            DetailContribution gDetail = fromContributionToDC(query);
            return safeOfPickedState(gDetail, _id, option);

        }
    }


    /*
     * 1. STATE PENDING (PROVIDER) 2. STATE PICKED (PROVIDER) 3. STATE INHOUSE
     * (THE CONTRIBUTION IS IN FOOD BANK) 4. STATE SHIP (BENEFIACIARY) 5. STATE
     * DELIVERED(NENEFICIARY)
     */

    /**
     * Method to set the State in Contribution.
     * 
     * @param key
     * @param _data
     * @return {@code IStateContribution}
     */
    private void checkOptionState(int key, ContributionData data) {

        IStateContribution state;
        switch (key) {
            case STATE_PICKED_UP:
                state = new PickedUp();
                state.picked(data);
                break;

            case STATE_IN_HOUSE:
                state = new InHouse();
                state.inHouse(data);
                break;

            case STATE_SHIPMENT:
                state = new Shipment();
                state.ship(data);
                break;

            case STATE_DELIVERED:
                state = new Delyvered();
                state.delyvered(data);
                break;

            default:
                state = new Pending();
                state.pending(data);
                break;
        }

    }



    /* Convert TO ContributionData */
    private ContributionData fromGeneralContributionToCD(
            GeneralContribution context) {
        return modelMapper.map(context, ContributionData.class);
    }

    private ContributionData fromDetailContributionToCD(
            DetailContribution gDetail) {
        return modelMapper.map(gDetail, ContributionData.class);
    }

    /* Convert TO GeneralContribution */
    private GeneralContribution convertOfGC(GeneralContributionDTO dto) {
        return modelMapper.map(dto, GeneralContribution.class);
    }

    private GeneralContribution fromContributionDataToGC(
            ContributionData cData) {
        return modelMapper.map(cData, GeneralContribution.class);
    }

    private GeneralContribution fromContributionToGC(Contribution query) {
        return modelMapper.map(query, GeneralContribution.class);
    }

    /* Convert TO GeneralContribution */
    private DetailContribution convertOf(DetailContributionDTO dto) {
        return modelMapper.map(dto, DetailContribution.class);
    }

    /* Convert TO DetailContribution */
    private DetailContribution fromContributionToDC(Contribution query) {
        return modelMapper.map(query, DetailContribution.class);
    }

    private DetailContribution fromContributionDataToDC(
            ContributionData cData) {
        return modelMapper.map(cData, DetailContribution.class);
    }



    /**
     * Check if an object is or DetailContribution or GeneralContribution.
     * 
     * @param object
     * @param type
     * @return {@code boolean }
     */
    private boolean isInstance(Object object, Class<?> type) {
        return type.isInstance(object);
    }



}
