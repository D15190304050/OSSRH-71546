package dataworks.mathematics.geometry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link ShapeParser} class provides static methods to parse and return an instance of {@link IShape2D} from a {@link JSONObject} or a JSON string.
 */
public class ShapeParser
{
    // String literals of the keys.
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String CENTER_X = "CenterX";
    private static final String CENTER_Y = "CenterY";
    private static final String RADIUS = "Radius";
    private static final String END_POINT_1 = "EndPoint1";
    private static final String END_POINT_2 = "EndPoint2";
    private static final String POINTS = "Points";
    private static final String MAX_X = "MaxX";
    private static final String MIN_X = "MinX";
    private static final String MAX_Y = "MaxY";
    private static final String MIN_Y = "MinY";


    private ShapeParser()
    {
    }

    /**
     * Parses a straight line from the given {@link JSONObject} and returns a {@link Line} that can represent the straight line.
     *
     * @param lineJson A {@link JSONObject} with following data format.<p>
     *                 <code>{</code><p>
     *                 <code>"A": [A],</code><p>
     *                 <code>"B": [B],</code><p>
     *                 <code>"C": [C]</code><p>
     *                 <code>}</code><p>
     *                 where<p>
     *                 [A], [B], [C] represent the parameters for an equation of a straight line, with type {@code double}. (i.e. Ax + By + C = 0).
     * @return An instance of the {@link Line} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException     If argument "lineJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "A", "B", "C" is not contained in "lineJson".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "A" is "true").
     */
    @NotNull

    public static Line parseLine(JSONObject lineJson)
    {
        if (lineJson == null)
            throw new NullPointerException("Argument \"lineJson\" cannot be null.");

        if (!lineJson.containsKey(A))
            throw new IllegalArgumentException("Bad JSON, argument \"lineJson\" does not contain key \"" + A + "\".");
        if (!lineJson.containsKey(B))
            throw new IllegalArgumentException("Bad JSON, argument \"lineJson\" does not contain key \"" + B + "\".");
        if (!lineJson.containsKey(C))
            throw new IllegalArgumentException("Bad JSON, argument \"lineJson\" does not contain key \"" + C + "\".");

        String aString = lineJson.getString(A);
        String bString = lineJson.getString(B);
        String cString = lineJson.getString(C);

        double a = Double.parseDouble(aString);
        double b = Double.parseDouble(bString);
        double c = Double.parseDouble(cString);
        return new Line(a, b, c);
    }

    /**
     * Parses a straight line from the given JSON string and returns a {@link Line} that can represent the straight line.
     *
     * @param lineJson A JSON string with following data format.<p>
     *                 <code>{</code><p>
     *                 <code>"A": [A],</code><p>
     *                 <code>"B": [B],</code><p>
     *                 <code>"C": [C]</code><p>
     *                 <code>}</code><p>
     *                 where<p>
     *                 [A], [B], [C] represent the parameters for an equation of a straight line, with type {@code double}. (i.e. Ax + By + C = 0).
     * @return An instance of the {@link Line} class with parameters specified by the given JSON string.
     * @throws NullPointerException     If argument "lineJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "A", "B", "C" is not contained in "lineJson"; or if "lineJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "A" is "true").
     * @implNote This method calls {@link ShapeParser#parseLine(JSONObject)}.
     */
    @NotNull

    public static Line parseLine(String lineJson)
    {
        if (lineJson == null)
            throw new NullPointerException("Argument \"lineJson\" cannot be null.");

        if (!JSONObject.isValid(lineJson))
            throw new IllegalArgumentException("Argument \"lineJson\" must be a valid JSON string.");

        return parseLine(JSONObject.parseObject(lineJson));
    }

    /**
     * Parses a circle from the given {@link JSONObject} and returns a {@link Circle} that can represent the circle.
     *
     * @param circleJson A {@link JSONObject} with following data format.<p>
     *                   <code>{</code><p>
     *                   <code>"CenterX": [CenterX],</code><p>
     *                   <code>"CenterY": [CenterY],</code><p>
     *                   <code>"Radius": [Radius]</code><p>
     *                   <code>}</code><p>
     *                   where<p>
     *                   [CenterX] represents the X-coordinate of the center of the circle, with type {@code double};<p>
     *                   [CenterY] represents the Y-coordinate of the center of the circle, with type {@code double};<p>
     *                   [Radius] represents the radius of the circle, with type {@code double}.
     * @return An instance of the {@link Circle} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException     If argument "circleJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "CenterX", "CenterY", "Radius" is not contained in "circleJson".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "CenterX" is "true").
     */
    @NotNull

    public static Circle parseCircle(JSONObject circleJson)
    {
        if (circleJson == null)
            throw new NullPointerException("Argument \"circleJson\" cannot be null.");

        if (!circleJson.containsKey(CENTER_X))
            throw new IllegalArgumentException("Bad JSON, argument \"circleJson\" does not contain key \"" + CENTER_X + "\".");
        if (!circleJson.containsKey(CENTER_Y))
            throw new IllegalArgumentException("Bad JSON, argument \"circleJson\" does not contain key \"" + CENTER_Y + "\".");
        if (!circleJson.containsKey(RADIUS))
            throw new IllegalArgumentException("Bad JSON, argument \"circleJson\" does not contain key \"" + RADIUS + "\".");

        String centerXString = circleJson.getString(CENTER_X);
        String centerYString = circleJson.getString(CENTER_Y);
        String radiusString = circleJson.getString(RADIUS);

        double centerX = Double.parseDouble(centerXString);
        double centerY = Double.parseDouble(centerYString);
        double radius = Double.parseDouble(radiusString);
        return new Circle(centerX, centerY, radius);
    }

    /**
     * Parses a circle from the given JSON string and returns a {@link Circle} that can represent the circle.
     *
     * @param circleJson A JSON string with following data format.<p>
     *                   <code>{</code><p>
     *                   <code>"CenterX": [CenterX],</code><p>
     *                   <code>"CenterY": [CenterY],</code><p>
     *                   <code>"Radius": [Radius]</code><p>
     *                   <code>}</code><p>
     *                   where<p>
     *                   [CenterX] represents the X-coordinate of the center of the circle, with type {@code double};<p>
     *                   [CenterY] represents the Y-coordinate of the center of the circle, with type {@code double};<p>
     *                   [Radius] represents the radius of the circle, with type {@code double}.
     * @return An instance of the {@link Circle} class with parameters specified by the given JSON string.
     * @throws NullPointerException     If argument "circleJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "CenterX", "CenterY", "Radius" is not contained in "circleJson"; or if "circleJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "CenterX" is "true").
     * @implNote This method calls {@link ShapeParser#parseCircle(JSONObject)}.
     */
    @NotNull

    public static Circle parseCircle(String circleJson)
    {
        if (circleJson == null)
            throw new NullPointerException("Argument \"circleJson\" cannot be null.");

        if (!JSONObject.isValid(circleJson))
            throw new IllegalArgumentException("Argument \"circleJson\" must be a valid JSON string.");

        return parseCircle(JSONObject.parseObject(circleJson));
    }

    /**
     * Parses a point from the given {@link JSONObject} and returns a {@link Point} that can represent the point.
     *
     * @param pointJson A {@link JSONObject} with following data format.<p>
     *                  <code>{</code><p>
     *                  <code>"X": [X],</code><p>
     *                  <code>"Y": [Y],</code><p>
     *                  <code>}</code><p>
     *                  where<p>
     *                  [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                  [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link Line} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException     If argument "pointJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "X", "Y" is not contained in "pointJson".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     */
    @NotNull

    public static Point parsePoint(JSONObject pointJson)
    {
        if (pointJson == null)
            throw new NullPointerException("Argument \"pointJson\" cannot be null.");

        if (!pointJson.containsKey(X))
            throw new IllegalArgumentException("Bad JSON, argument \"pointJson\" does not contain key \"" + X + "\".");
        if (!pointJson.containsKey(Y))
            throw new IllegalArgumentException("Bad JSON, argument \"pointJson\" does not contain key \"" + Y + "\".");

        String xString = pointJson.getString(X);
        String yString = pointJson.getString(Y);

        double x = Double.parseDouble(xString);
        double y = Double.parseDouble(yString);
        return new Point(x, y);
    }

    /**
     * Parses a point from the given JSON string and returns a {@link Point} that can represent the point.
     *
     * @param pointJson A JSON string with following data format.<p>
     *                  <code>{</code><p>
     *                  <code>"X": [X],</code><p>
     *                  <code>"Y": [Y],</code><p>
     *                  <code>}</code><p>
     *                  where<p>
     *                  [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                  [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link Line} class with parameters specified by the given JSON string.
     * @throws NullPointerException     If argument "pointJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "X", "Y" is not contained in "pointJson"; or if "pointJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     * @implNote This method calls {@link ShapeParser#parsePoint(JSONObject)}.
     */
    @NotNull

    public static Point parsePoint(String pointJson)
    {
        if (pointJson == null)
            throw new NullPointerException("Argument \"pointJson\" cannot be null.");

        if (!JSONObject.isValid(pointJson))
            throw new IllegalArgumentException("Argument \"pointJson\" must be a valid JSON string.");

        return parsePoint(JSONObject.parseObject(pointJson));
    }

    /**
     * Parses a line segment from the given {@link JSONObject} and returns a {@link LineSegment} that can represent the line segment.
     *
     * @param lineSegmentJson A {@link JSONObject} with following data format.<p>
     *                        <code>{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;"EndPoint1":</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;},</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;"EndPoint2":</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;}</code><p>
     *                        <code>}</code><p>
     *                        where<p>
     *                        "EndPoint1" and "EndPoint2" encapsulate 2 end points of the line segment;<p>
     *                        [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                        [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link LineSegment} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException     If "lineSegmentJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "END_POINT_1", "END_POINT_2" is not contained in "pointJson"; or if at least one of the keys "X", "Y" is not contained in "EndPoint1" or "EndPoint2".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     * @throws JSONException            If the value associated with key "EndPoint1" or key "EndPoint2" is not a valid JSON object.
     */
    @NotNull

    public static LineSegment parseLineSegment(JSONObject lineSegmentJson)
    {
        if (lineSegmentJson == null)
            throw new NullPointerException("Argument \"lineSegmentJson\" cannot be null.");

        if (!lineSegmentJson.containsKey(END_POINT_1))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + END_POINT_1 + "\".");
        if (!lineSegmentJson.containsKey(END_POINT_2))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + END_POINT_2 + "\".");

        JSONObject endPoint1Json = lineSegmentJson.getJSONObject(END_POINT_1);
        JSONObject endPoint2Json = lineSegmentJson.getJSONObject(END_POINT_2);

        if (!endPoint1Json.containsKey(X))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + X + "\"" + " for its internal JSON object with key \"" + END_POINT_1 + "\".");
        if (!endPoint1Json.containsKey(Y))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + Y + "\"" + " for its internal JSON object with key \"" + END_POINT_1 + "\".");
        if (!endPoint2Json.containsKey(X))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + X + "\"" + " for its internal JSON object with key \"" + END_POINT_2 + "\".");
        if (!endPoint2Json.containsKey(Y))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + Y + "\"" + " for its internal JSON object with key \"" + END_POINT_2 + "\".");

        String endPoint1XString = endPoint1Json.getString(X);
        String endPoint1YString = endPoint1Json.getString(Y);
        String endPoint2XString = endPoint2Json.getString(X);
        String endPoint2YString = endPoint2Json.getString(Y);

        double endPoint1X = Double.parseDouble(endPoint1XString);
        double endPoint1Y = Double.parseDouble(endPoint1YString);
        double endPoint2X = Double.parseDouble(endPoint2XString);
        double endPoint2Y = Double.parseDouble(endPoint2YString);

        Point endPoint1 = new Point(endPoint1X, endPoint1Y);
        Point endPoint2 = new Point(endPoint2X, endPoint2Y);

        return new LineSegment(endPoint1, endPoint2);
    }

    /**
     * Parses a line segment from the given JSON string and returns a {@link LineSegment} that can represent the line segment.
     *
     * @param lineSegmentJson A JSON string with following data format.<p>
     *                        <code>{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;"EndPoint1":</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;},</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;"EndPoint2":</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                        <code>&nbsp;&nbsp;&nbsp;&nbsp;}</code><p>
     *                        <code>}</code><p>
     *                        where<p>
     *                        "EndPoint1" and "EndPoint2" encapsulate 2 end points of the line segment;<p>
     *                        [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                        [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link LineSegment} class with parameters specified by the given JSON string.
     * @throws NullPointerException     If "lineSegmentJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "END_POINT_1", "END_POINT_2" is not contained in "pointJson"; or if at least one of the keys "X", "Y" is not contained in "EndPoint1" or "EndPoint2"; or "lineSegmentJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     * @throws JSONException            If the value associated with key "EndPoint1" or key "EndPoint2" is not a valid JSON object.
     * @implNote This method calls {@link ShapeParser#parseLineSegment(JSONObject)}.
     */
    @NotNull

    public static LineSegment parseLineSegment(String lineSegmentJson)
    {
        if (lineSegmentJson == null)
            throw new NullPointerException("Argument \"lineSegmentJson\" cannot be null.");

        if (!JSONObject.isValid(lineSegmentJson))
            throw new IllegalArgumentException("Argument \"lineSegmentJson\" must be a valid JSON string.");

        return parseLineSegment(JSONObject.parseObject(lineSegmentJson));
    }

    /**
     * Parses a polyline from the given {@link JSONObject} and returns a {@link Polyline} that can represent the polyline.
     *
     * @param polylineJson A {@link JSONObject} with following data format.<p>
     *                     <code>{</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;"Points": [</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;},</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;...</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;]</code><p>
     *                     <code>}</code><p>
     *                     where<p>
     *                     "Points" is the key of the array that contains all points of the polyline;<p>
     *                     [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                     [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link Polyline} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException     If "polylineJson" is null.
     * @throws IllegalArgumentException If "polylineJson" does not contain key "Points"; or if there is a {@link JSONObject} in the point array that does not contain key "X" or key "Y".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     * @throws JSONException            If the value associated with key "Points" is not a valid JSON array.
     */
    @NotNull

    public static Polyline parsePolyline(JSONObject polylineJson)
    {
        if (polylineJson == null)
            throw new NullPointerException("Argument \"polylineJson\" cannot be null.");

        if (!polylineJson.containsKey(POINTS))
            throw new IllegalArgumentException("Bad JSON, argument \"lineSegmentJson\" does not contain key \"" + POINTS + "\".");

        JSONArray pointArray = polylineJson.getJSONArray(POINTS);
        Polyline polyline = new Polyline();
        for (int i = 0; i < pointArray.size(); i++)
        {
            JSONObject pointJson = pointArray.getJSONObject(i);

            if (!pointJson.containsKey(X))
                throw new IllegalArgumentException("Bad JSON, The JSON object with index \"" + i + "\" does not contain key \"" + X + "\" in the JSON array associated with key \"" + POINTS + "\".");
            if (!pointJson.containsKey(Y))
                throw new IllegalArgumentException("Bad JSON, The JSON object with index \"" + i + "\" does not contain key \"" + Y + "\" in the JSON array associated with key \"" + POINTS + "\".");

            String xString = pointJson.getString(X);
            String yString = pointJson.getString(Y);

            double x = Double.parseDouble(xString);
            double y = Double.parseDouble(yString);

            polyline.addEnd(x, y);
        }

        return polyline;
    }

    /**
     * Parses a polyline from the given JSON string and returns a {@link Polyline} that can represent the polyline.
     *
     * @param polylineJson A JSON string with following data format.<p>
     *                     <code>{</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;"Points": [</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;{</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"X": [X],</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Y": [Y]</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;},</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;...</code><p>
     *                     <code>&nbsp;&nbsp;&nbsp;&nbsp;]</code><p>
     *                     <code>}</code><p>
     *                     where<p>
     *                     "Points" is the key of the array that contains all points of the polyline;<p>
     *                     [X] represents the X-coordinate of the point, with type {@code double};<p>
     *                     [Y] represents the Y-coordinate of the point, with type {@code double}.
     * @return An instance of the {@link Polyline} class with parameters specified by the given JSON string.
     * @throws NullPointerException     If "polylineJson" is null.
     * @throws IllegalArgumentException If "polylineJson" does not contain key "Points"; or if there is a {@link JSONObject} in the point array that does not contain key "X" or key "Y"; or if "polylineJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "X" is "true").
     * @throws JSONException            If the value associated with key "Points" is not a valid JSON array.
     * @implNote This method calls {@link ShapeParser#parsePolyline(JSONObject)}.
     */
    @NotNull

    public static Polyline parsePolyline(String polylineJson)
    {
        if (polylineJson == null)
            throw new NullPointerException("Argument \"polylineJson\" cannot be null.");

        if (!JSONObject.isValid(polylineJson))
            throw new IllegalArgumentException("Argument \"polylineJson\" must be a valid JSON string.");

        return parsePolyline(JSONObject.parseObject(polylineJson));
    }

    /**
     * Parses a bounding box from the given {@link JSONObject} and returns a {@link BoundingBox} that can represent the bounding box.
     * @param boundingBoxJson A {@link JSONObject} with following data format.<p>
     *                        <code>{</code><p>
     *                        <code>"MinX": [MinX],</code><p>
     *                        <code>"MinY": [MinY],</code><p>
     *                        <code>"MaxX": [MaxX],</code><p>
     *                        <code>"MaxY": [MaxY]</code><p>
     *                        <code>}</code><p>
     *                        where<p>
     *                        ([MinX], [MinY]) represents the coordinate of the bottom left point of the bounding box, with type ({@code double}, {@code double});<p>
     *                        ([MaxX], [MaxY]) represents the coordinate of the top right point of the bounding box, with type ({@code double}, {@code double}).<p>
     *                        Term "bottom left" and "top right" are measured in the Cartesian coordinate system, where right is X-axis positive direction, and up is Y-axis positive direction.
     *
     * @return An instance of the {@link BoundingBox} class with parameters specified by the given {@link JSONObject}.
     * @throws NullPointerException If "boundingBoxJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "MinX", "MinY", "MaxX", "MaxY" is not contained in "boundingBoxJson".
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "MaxX" is "true").
     */
    @NotNull

    public static BoundingBox parseBoundingBox(JSONObject boundingBoxJson)
    {
        if (boundingBoxJson == null)
            throw new NullPointerException("Argument \"boundingBoxJson\" cannot be null.");

        if (!boundingBoxJson.containsKey(MAX_X))
            throw new IllegalArgumentException("Bad JSON, argument \"boundingBoxJson\" does not contain key \"" + MAX_X + "\".");
        if (!boundingBoxJson.containsKey(MIN_X))
            throw new IllegalArgumentException("Bad JSON, argument \"boundingBoxJson\" does not contain key \"" + MIN_X + "\".");
        if (!boundingBoxJson.containsKey(MAX_Y))
            throw new IllegalArgumentException("Bad JSON, argument \"boundingBoxJson\" does not contain key \"" + MAX_Y + "\".");
        if (!boundingBoxJson.containsKey(MIN_Y))
            throw new IllegalArgumentException("Bad JSON, argument \"boundingBoxJson\" does not contain key \"" + MIN_Y + "\".");

        String maxXString = boundingBoxJson.getString(MAX_X);
        String minXString = boundingBoxJson.getString(MIN_X);
        String maxYString = boundingBoxJson.getString(MAX_Y);
        String minYString = boundingBoxJson.getString(MIN_Y);

        double maxX = Double.parseDouble(maxXString);
        double minX = Double.parseDouble(minXString);
        double maxY = Double.parseDouble(maxYString);
        double minY = Double.parseDouble(minYString);

        return new BoundingBox(minX, minY, maxX, maxY);
    }

    /**
     * Parses a bounding box from the given JSON string and returns a {@link BoundingBox} that can represent the bounding box.
     * @param boundingBoxJson A JSON string with following data format.<p>
     *                        <code>{</code><p>
     *                        <code>"MinX": [MinX],</code><p>
     *                        <code>"MinY": [MinY],</code><p>
     *                        <code>"MaxX": [MaxX],</code><p>
     *                        <code>"MaxY": [MaxY]</code><p>
     *                        <code>}</code><p>
     *                        where<p>
     *                        ([MinX], [MinY]) represents the coordinate of the bottom left point of the bounding box, with type ({@code double}, {@code double});<p>
     *                        ([MaxX], [MaxY]) represents the coordinate of the top right point of the bounding box, with type ({@code double}, {@code double}).<p>
     *                        Term "bottom left" and "top right" are measured in the Cartesian coordinate system, where right is X-axis positive direction, and up is Y-axis positive direction.
     *
     * @return An instance of the {@link BoundingBox} class with parameters specified by the given JSON string.
     * @throws NullPointerException If "boundingBoxJson" is null.
     * @throws IllegalArgumentException If at least one of the keys "MinX", "MinY", "MaxX", "MaxY" is not contained in "boundingBoxJson"; or "boundingBoxJson" is not a valid JSON string.
     * @throws NumberFormatException    If there is a value associated with a required key, while the value does not contain a parsable {@code double}. (i.e. the value associated with key "MaxX" is "true").
     * @implNote This method calls {@link ShapeParser#parseBoundingBox(JSONObject)}.
     */
    @NotNull

    public static BoundingBox parseBoundingBox(String boundingBoxJson)
    {
        if (boundingBoxJson == null)
            throw new NullPointerException("Argument \"boundingBoxJson\" cannot be null.");

        if (!JSONObject.isValid(boundingBoxJson))
            throw new IllegalArgumentException("Argument \"boundingBoxJson\" is not a valid JSON string.");

        return parseBoundingBox(JSONObject.parseObject(boundingBoxJson));
    }
}
