package co.com.foodbank.contribution.v1.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.dto.IStateContribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.v1.model
 *         10/06/2021
 */
@Document(collection = "Contribution")
public class Contribution implements IContribution {

    @Id
    private String id;

    private String description;

    private Date date;

    private IStateContribution state;



    /**
     * Default constructor.
     */
    public Contribution() {

    }


    public Contribution(String description, Date _dateContribution,
            IStateContribution state) {
        this.description = description;
        this.date = _dateContribution;
        this.state = state;
    }


    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }


    @Override
    public Date getDate() {
        // TODO Auto-generated method stub
        return date;
    }


    @Override
    public IStateContribution getState() {
        // TODO Auto-generated method stub
        return state;
    }


    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public void setState(IStateContribution state) {
        this.state = state;
    }



}
