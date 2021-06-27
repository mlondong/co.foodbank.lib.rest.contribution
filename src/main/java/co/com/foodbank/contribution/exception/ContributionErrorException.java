package co.com.foodbank.contribution.exception;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.exception
 *         27/06/2021
 */
public class ContributionErrorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ContributionErrorException(String ex) {
        super(ex);
    }
}
