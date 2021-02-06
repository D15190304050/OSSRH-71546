package dataworks.geographical.trajectories;

import dataworks.mathematics.geometry.Point;

import java.sql.Timestamp;

public class SamplePoint extends Point
{
    private Timestamp samplingTime;

    public SamplePoint(double x, double y, Timestamp samplingTime)
    {
        super(x, y);

        this.samplingTime = samplingTime;
    }

    public Timestamp getSamplingTime()
    {
        return samplingTime;
    }

    public void setSamplingTime(Timestamp samplingTime)
    {
        this.samplingTime = samplingTime;
    }

    @Override
    public String toString()
    {
        return "[" + super.toString() + ", " + samplingTime + "]";
    }
}
