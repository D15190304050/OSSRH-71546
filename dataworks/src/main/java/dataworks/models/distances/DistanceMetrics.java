package dataworks.models.distances;

public class DistanceMetrics
{
    private DistanceMetrics(){}

    public static final EuclideanDistance EUCLIDEAN_DISTANCE = new EuclideanDistance();
    public static final ManhattanDistance MANHATTAN_DISTANCE = new ManhattanDistance();
}
