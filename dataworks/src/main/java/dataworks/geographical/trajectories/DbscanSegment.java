package dataworks.geographical.trajectories;

import dataworks.ArgumentOutOfRangeException;
import dataworks.collections.ArrayHelper;
import dataworks.collections.LinkedList;
import dataworks.mathematics.Vector;
import dataworks.mathematics.geometry.Point;
import dataworks.models.clustering.Dbscan;
import dataworks.models.distances.DistanceMetrics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

// Segments the trajectory to stop and move episodes using DBSCAN.
public class DbscanSegment
{
    @Contract(pure = true)
    private DbscanSegment(){}

    @NotNull
    @Contract("null, _, _ -> fail")
    public static Iterable<TrajectorySegment> segment(Trajectory trajectory, int minPoints, double minDistance)
    {
        if (trajectory == null)
            throw new NullPointerException("Argument \"trajectory\" cannot be null.");
        if (minPoints <= 0)
            throw new ArgumentOutOfRangeException("Argument \"minPoints\" cannot be non-positive.");
        if (minDistance <= 0)
            throw new ArgumentOutOfRangeException("Argument \"minDistance\" cannot be non-positive.");

        LinkedList<TrajectorySegment> segments = new LinkedList<>();

        Dbscan dbscan = new Dbscan(minPoints, minDistance);
        dbscan.setDistanceMetric(DistanceMetrics.EUCLIDEAN_DISTANCE);

        SamplePoint[] points = new SamplePoint[trajectory.count()];
        ArrayHelper.copy(trajectory.getPoints(), points, 0, trajectory.count());
        int[] labels = dbscan.fitPredict(Vector.pointsToVectors(points));

        int i = 0;
        while (i < labels.length)
        {
            boolean moving = labels[i] < 0;
            LinkedList<Point> trajectorySegmentPoints = new LinkedList<>();
            trajectorySegmentPoints.addLast(points[i]);
            int j = i + 1;
            while ((j < labels.length) && (labels[j] == labels[i]))
                trajectorySegmentPoints.addLast(points[j++]);
            TrajectorySegment segment = new TrajectorySegment(trajectorySegmentPoints, moving);
            segments.addLast(segment);
            i = j;
        }

        return segments;
    }
}
