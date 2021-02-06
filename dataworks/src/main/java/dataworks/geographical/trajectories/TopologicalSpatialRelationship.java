package dataworks.geographical.trajectories;

import org.jetbrains.annotations.Contract;

public class TopologicalSpatialRelationship
{
    private TopologicalPredicate predicate;
    private RegionOfInterest roi;

    @Contract("null, _ -> fail; !null, null -> fail")
    public TopologicalSpatialRelationship(TopologicalPredicate predicate, RegionOfInterest roi)
    {
        validatePredicate(predicate);
        validateRoi(roi);

        this.predicate = predicate;
        this.roi = roi;
    }

    @Contract("null -> fail")
    private void validatePredicate(TopologicalPredicate predicate)
    {
        if (predicate == null)
            throw new NullPointerException("Argument \"predicate\" is null.");
    }

    @Contract("null -> fail")
    private void validateRoi(RegionOfInterest roi)
    {
        if (roi == null)
            throw new NullPointerException("Argument \"roi\" is null.");
    }

    public TopologicalPredicate getPredicate()
    {
        return predicate;
    }

    @Contract("null -> fail")
    public void setPredicate(TopologicalPredicate predicate)
    {
        validatePredicate(predicate);
        this.predicate = predicate;
    }

    public RegionOfInterest getRoi()
    {
        return roi;
    }

    @Contract("null -> fail")
    public void setRoi(RegionOfInterest roi)
    {
        validateRoi(roi);
        this.roi = roi;
    }

    @Override
    public String toString()
    {
        return predicate.toString() + " " + roi.getName();
    }
}
