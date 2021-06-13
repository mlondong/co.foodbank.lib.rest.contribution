package co.com.foodbank.contribution.exception;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.exception
 *         10/06/2021
 */
public class ContributionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ContributionNotFoundException(String id) {
        super(id);
    }
}
