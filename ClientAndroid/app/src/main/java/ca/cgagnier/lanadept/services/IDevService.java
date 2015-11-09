package ca.cgagnier.lanadept.services;

/**
 * Created by Christophe on 2015-10-25.
 */
public interface IDevService {

    /**
     * Clear all the repositories and create a fake user, fake lan, fake sections, fake places, ...
     */
    public void ResetEverything() throws Exception;

}
