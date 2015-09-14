package ca.cgagnier.lanadept.services;

import org.joda.time.DateTime;

import java.util.List;

import ca.cgagnier.lanadept.models.Lan;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;
import ca.cgagnier.lanadept.services.exceptions.NoLanInFutureException;

public interface ILanService {

    /**
     * Get a list of all the Lan, ordered from the most recent to the oldest
     * @return A list of lan ordered by starting date
     */
    public List<Lan> getAllLans();

    /**
     * Select the current active Lan for the application
     * @param selectedLan Lan to set active
     */
    public void selectLan(Lan selectedLan);

    /**
     * Get the currently selected Lan
     * @return The currently selected Lan
     */
    public Lan getSelectedLan();

    /**
     * Create a new Lan and insert it in the repo
     * @param startingDate The date at which the Lan start
     * @param position The position where the Lan will take place, in a human friendly way
     * @param positionMap The position where the Lan will take place, in a google map friendly way
     * @return The Lan just created
     */
    public Lan create(DateTime startingDate, String position, String positionMap);

    /**
     * Search a Lan by its Id
     * @param id
     * @return the Lan found
     * @throws NotFoundException
     */
    public Lan getById(long id) throws NotFoundException;

    /**
     * update a Lan in the database
     * @param lan The existing Lan to update
     */
    public void update(Lan lan);

    /**
     * Delete an existing Lan
     * @param lan Lan to delete
     */
    public void delete(Lan lan);

    /**
     * Get the nearest Lan in the future
     * @return The closest Lan found
     * @throws NoLanInFutureException This exception is thrown if no Lan can be found in the future
     */
    public Lan getClosestNextLan() throws NoLanInFutureException;
}
