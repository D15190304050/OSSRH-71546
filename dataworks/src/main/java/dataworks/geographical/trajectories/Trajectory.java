package dataworks.geographical.trajectories;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dataworks.collections.LinkedList;
import org.jetbrains.annotations.Contract;

import java.sql.Timestamp;

// Note: sample rate is not a field in the Trajectory class because it can be calculated by 2 adjacent sampling points.
// And yet the trajectory may not have a stable sample rate. But, timestamp for each sample point is definite.
public class Trajectory
{
    private static final String TRAJECTORY_ID = "TrajectoryId";
    private static final String POINTS = "Points";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String SAMPLING_TIME = "SamplingTime";

    private LinkedList<SamplePoint> points;
    private int trajectoryId;
    private Object tag;


    public Iterable<SamplePoint> getPoints()
    {
        return points;
    }


    public int getTrajectoryId()
    {
        return trajectoryId;
    }

    /**
     * Gets the number of points of the trajectory.
     *
     * @return The number of points of the trajectory.
     */
    public int count()
    {
        return points.count();
    }

    public Trajectory()
    {
        this.trajectoryId = -1;
        this.points = new LinkedList<>();
        this.tag = null;
    }

    /**
     * @param trajectoryJsonText A JSON string that contains the ID and coordinates of the trajectory.<p>
     *                           <code>{</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;"TrajectoryId": [TrajectoryId],</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;"Points": [</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"SamplingTime": [SamplingTime]</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;}</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;...</code><p>
     *                           <code>&nbsp;&nbsp;&nbsp;&nbsp;]</code><p>
     *                           <code>}</code><p>
     *                           Copy and paste to a code editor (or other tools) to view this JSON format clearly.<p>
     *                           [TrajectoryId] represents the ID of the trajectory with type int.<p>
     *                           [X] represents the x-coordinate of the point with type double.<p>
     *                           [Y] represents the y-coordinate of the point with type double.<p>
     *                           [SamplingTime] represents the sampling time of the point with type {@link Timestamp}, this is an optional field.
     */

    public static Trajectory parse(String trajectoryJsonText)
    {
        if (trajectoryJsonText == null)
            throw new NullPointerException("Argument \"trajectoryJsonText\" is null.");

        if (!JSONObject.isValid(trajectoryJsonText))
            throw new IllegalArgumentException("Argument \"trajectoryJsonText\" cannot be parsed as JSON object.");

        Trajectory trajectory = new Trajectory();

        JSONObject trajectoryJson = JSONObject.parseObject(trajectoryJsonText);
        trajectory.trajectoryId = trajectoryJson.getInteger(TRAJECTORY_ID);
        JSONArray points = trajectoryJson.getJSONArray(POINTS);
        for (int i = 0; i < points.size(); i++)
        {
            JSONObject pointJson = points.getJSONObject(i);
            double x = pointJson.getDouble(X);
            double y = pointJson.getDouble(Y);

            Timestamp samplingTime = null;
            if (pointJson.containsKey(SAMPLING_TIME))
                samplingTime = Timestamp.valueOf(pointJson.getString(SAMPLING_TIME));

            trajectory.points.addLast(new SamplePoint(x, y, samplingTime));
        }

        return trajectory;
    }


    public Trajectory(int id, Iterable<SamplePoint> points)
    {
        if (points == null)
            throw new NullPointerException("Argument \"points\" is null.");

        this.trajectoryId = id;
        this.points = new LinkedList<>(points);
        tag = null;
    }

    public Object getTag()
    {
        return tag;
    }

    public void setTag(Object tag)
    {
        this.tag = tag;
    }

    public void addSamplePoint(SamplePoint point)
    {
    }



    public JSONObject toJson()
    {
        JSONObject trajectoryJson = new JSONObject();
        trajectoryJson.put(TRAJECTORY_ID, trajectoryId);

        JSONArray points = new JSONArray();
        for (SamplePoint point : this.points)
        {
            double x = point.getX();
            double y = point.getY();
            JSONObject pointJson = new JSONObject();
            pointJson.put(X, x);
            pointJson.put(Y, y);

            if (point.getSamplingTime() != null)
                pointJson.put(SAMPLING_TIME, point.getSamplingTime().toString());

            points.add(pointJson);
        }
        trajectoryJson.put(POINTS, points);

        return trajectoryJson;
    }
}
