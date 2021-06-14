package co.com.foodbank.contribution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import co.com.foodbank.contribution.exception.ContributionNotFoundException;
import co.com.foodbank.contribution.v1.model.Contribution;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.repository
 *         10/06/2021
 */
@Repository
public interface ContributionRepository
        extends MongoRepository<Contribution, String> {

    @Query("{'codeBar': ?0}")
    Contribution findByCodeBar(String code)
            throws ContributionNotFoundException;

    // @Query(value = "$or:[{'creatorUsername':?0},{'sharedUsers':{$in:?0}}]",
    // fields = "{'databases':1}")

    /*
     * @Query("update Contribution u set u.state =:state where u.id =:id")
     * Contribution updateContribution(
     * 
     * @Param(value = "state") IStateContribution state,
     * 
     * @Param(value = "id") String id);
     */
}
