package co.com.foodbank.contribution.v1.model;

import java.util.Date;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.contribution.dto.interfaces.IContribution;
import co.com.foodbank.contribution.state.IStateContribution;
import co.com.foodbank.vehicule.dto.Volume;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.v1.model
 *         10/06/2021
 */
@Document(collection = "Contribution")
@TypeAlias("GeneralContribution")
public class GeneralContribution extends Contribution implements IContribution {

    private Volume volume;

    /**
     * Default constructor.
     */
    public GeneralContribution() {}


    public GeneralContribution(String description, Date dateContribution,
            IStateContribution state, Volume volume) {

        super(description, dateContribution, state);
        this.volume = volume;
    }


    public Volume getVolume() {
        return volume;
    }


    public void setVolume(Volume volume) {
        this.volume = volume;
    }


    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return super.getDescription();
    }


    @Override
    public Date getDate() {
        // TODO Auto-generated method stub
        return super.getDate();
    }


    @Override
    public IStateContribution getState() {
        // TODO Auto-generated method stub
        return super.getState();
    }


    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }


    @Override
    public void setId(String id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }


    @Override
    public void setDescription(String description) {
        // TODO Auto-generated method stub
        super.setDescription(description);
    }


    @Override
    public void setDate(Date date) {
        // TODO Auto-generated method stub
        super.setDate(date);
    }


    @Override
    public void setState(IStateContribution state) {
        // TODO Auto-generated method stub
        super.setState(state);
    }



}
