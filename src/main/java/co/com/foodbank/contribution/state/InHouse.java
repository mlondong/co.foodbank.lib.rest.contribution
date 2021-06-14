package co.com.foodbank.contribution.state;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import co.com.foodbank.contribution.dto.ContributionData;
import co.com.foodbank.contribution.dto.IStateContribution;

@JsonAutoDetect(fieldVisibility = Visibility.DEFAULT)
public class InHouse implements IStateContribution, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String context;



    public InHouse() {}


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public void inHouse(ContributionData contribution) {
        this.context = "In House";
        contribution.setState(this);
    }

    @Override
    public void picked(ContributionData contribution) {}

    @Override
    public void pending(ContributionData contribution) {}

    @Override
    public void delyvered(ContributionData contribution) {}

    @Override
    public void ship(ContributionData contribution) {}

}
