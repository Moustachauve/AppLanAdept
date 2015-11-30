package ca.cgagnier.lanadept.common.transferObjects.jsonMapper;

import ca.cgagnier.lanadept.common.models.Lan;
import ca.cgagnier.lanadept.common.models.PlaceSection;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import java.util.List;

public class LanModel {

    @JsonSerialize(using = DatetimeParser.class)
    public DateTime startingDate;
    public String position;
    public String positionMap;
    public List<PlaceSection> sections;

    public LanModel(Lan lan) {
        startingDate = lan.startingDate;
        position = lan.position;
        positionMap = lan.positionMap;

    }
}
