package ca.cgagnier.lanadept.repositories;

import ca.cgagnier.lanadept.models.Lan;

public interface ILanRepository extends IGenericRepository<Lan> {

    public void delete(Lan lan);

}
