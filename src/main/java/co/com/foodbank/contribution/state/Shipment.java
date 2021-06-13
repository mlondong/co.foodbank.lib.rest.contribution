package co.com.foodbank.contribution.state;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import co.com.foodbank.contribution.dto.ContributionData;
import co.com.foodbank.contribution.dto.IStateContribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.dto
 *         31/05/2021
 */
@JsonAutoDetect(fieldVisibility = Visibility.DEFAULT)
public class Shipment implements IStateContribution, Serializable {



    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String context;


    public Shipment() {

    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public void ship(ContributionData contribution) {
        this.context = "Shipment";
        contribution.setState(this);
    }


    @Override
    public void picked(ContributionData contribution) {

    }

    @Override
    public void pending(ContributionData contribution) {

    }

    @Override
    public void delyvered(ContributionData contribution) {

    }

    @Override
    public void inHouse(ContributionData contribution) {

    }

}
