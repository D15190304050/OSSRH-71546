package dataworks.geographical.trajectories;

import com.alibaba.fastjson.JSONObject;
import dataworks.mathematics.geometry.IShape2D;
import org.jetbrains.annotations.*;
import dataworks.mathematics.geometry.BoundingBox;

/**
 *
 */
public class RegionOfInterest
{
    private static final String NAME = "Name";
    private static final String REGION = "Region";
    private static final String MIN_X = "MinX";
    private static final String MAX_X = "MaxX";
    private static final String MIN_Y = "MinY";
    private static final String MAX_Y = "MaxY";

    private IShape2D region;
    private String name;

    private RegionOfInterest()
    {
        region = null;
        name = null;
    }

    @Contract(pure = true)
    public RegionOfInterest(IShape2D region, String name)
    {
        validateRegion(region);
        this.region = region;
        this.name = name;
    }

    /**
     * @param roiString A JSON string with following format.
     *                  <p>
     *                  <code>{</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;"Name": [Name],</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;"BoundingBox": {</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"MinX": [MinX],</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"MaxX": [MaxX],</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"MinY": [MinY],</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"MaxY": [MaxY]</code><p>
     *                  <code>&nbsp;&nbsp;&nbsp;&nbsp;}</code><p>
     *                  <code>}</code><p>
     *                  [Name] represents the name of the ROI with type string.<p>
     *                  [MinX], [MaxX], [MinY], [MaxY] represents coordinates of the ROI with type double.
     */
    public RegionOfInterest parse(String roiString)
    {
        RegionOfInterest roi = new RegionOfInterest();

        JSONObject roiJson = JSONObject.parseObject(roiString);

        roi.name = roiJson.getString(NAME);
        JSONObject boundingBoxCoordinates = roiJson.getJSONObject(REGION);
        double minX = boundingBoxCoordinates.getDouble(MIN_X);
        double minY = boundingBoxCoordinates.getDouble(MAX_X);
        double maxX = boundingBoxCoordinates.getDouble(MIN_Y);
        double maxY = boundingBoxCoordinates.getDouble(MAX_Y);
        roi.region = new BoundingBox(minX, minY, maxX, maxY);

        return roi;
    }

    public IShape2D getRegion()
    {
        return region;
    }

    public void setRegion(IShape2D region)
    {
        validateRegion(region);
        this.region = region;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", bounding box: " + region;
    }

    private static void validateRegion(IShape2D region)
    {
        if (region == null)
            throw new NullPointerException("Argument \"region\" cannot be null.");
    }
}
