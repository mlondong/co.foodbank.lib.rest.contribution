package co.com.foodbank.contribution.v1.model;

import java.util.Date;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.contribution.dto.IContribution;
import co.com.foodbank.contribution.dto.IStateContribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.v1.model
 *         10/06/2021
 */
@Document(collection = "Contribution")
@TypeAlias("DetailContribution")
public class DetailContribution extends Contribution implements IContribution {


    private String codeBar;

    private Long numOfPackage;


    public DetailContribution() {}



    public DetailContribution(String description, Date _dateContribution,
            IStateContribution state, String _codeBar, Long _numberOfPackages) {

        super(description, _dateContribution, state);
        this.codeBar = _codeBar;
        this.numOfPackage = _numberOfPackages;
    }



    public String getCodeBar() {
        return codeBar;
    }



    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }



    public Long getNumOfPackage() {
        return numOfPackage;
    }



    public void setNumOfPackage(Long numOfPackage) {
        this.numOfPackage = numOfPackage;
    }



}
