package dataworks.examples;

import dataworks.mathematics.Random;
import dataworks.mathematics.Vector;
import dataworks.mathematics.geometry.Point;
import dataworks.mathematics.geometry.RandomPointGenerator;
import dataworks.models.clustering.Dbscan;
import dataworks.models.distances.DistanceMetrics;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * The {@link DbscanVisualization} class is a JavaFXApplication that provides a visual example for the DBSCAN algorithm.<br/><br/>
 * */
public class DbscanVisualization extends Application
{
    /**
     * The collection that contains all Text objects that shows the coordinate of some point.
     * Here the java.util.{@link LinkedList} class is used instead of {@link dataworks.collections.LinkedList}.
     * This is because the {@link Pane#getChildren()}.removeAll() method accepts a {@link java.util.Collection} object,
     * not a {@link dataworks.collections.ICollection} object.
     */
    private static LinkedList<Node> txtPositions;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        txtPositions = new LinkedList<>();

        Pane canvas = new Pane();

        // Generate data for clustering.

        // Cluster 1: a ring shape cluster with center (150, 150) and radius between [30, 5), 200 points.
        Point[] cluster1 = RandomPointGenerator.ring(150, 150, 30, 50, 200);

        // Cluster 2: a ring shape cluster with center (170, 170) and radius between [120, 150), 1000 points.
        Point[] cluster2 = RandomPointGenerator.ring(170, 170, 120, 150, 1000);

        // Cluster 3: a circle shape cluster with center (360, 350) and radius 5, 100 points.
        Point[] cluster3 = RandomPointGenerator.circle(360, 350, 50, 100);

        // Uncomment following 3 for-loops to show original data distribution.
        //        for (Point point : cluster1)
        //            addPoint(canvas, point, Color.CYAN);
        //        for (Point point : cluster2)
        //            addPoint(canvas, point, Color.ORANGE);
        //        for (Point point : cluster3)
        //            addPoint(canvas, point, Color.GREEN);

        // 3 Clusters and 10 noise points.
        Point[] data = new Point[cluster1.length + cluster2.length + cluster3.length + 10];
        System.arraycopy(cluster1, 0, data, 0, cluster1.length);
        System.arraycopy(cluster2, 0, data, cluster1.length, cluster2.length);
        System.arraycopy(cluster3, 0, data, cluster1.length + cluster2.length, cluster3.length);

        // Add 10 noise points.
        int noiseStartIndex = cluster1.length + cluster2.length + cluster3.length;
        data[0 + noiseStartIndex] = new Point(370, 20);
        data[1 + noiseStartIndex] = new Point(480, 50);
        data[2 + noiseStartIndex] = new Point(50, 420);
        data[3 + noiseStartIndex] = new Point(470, 360);
        data[4 + noiseStartIndex] = new Point(200, 250);
        data[5 + noiseStartIndex] = new Point(300, 300);
        data[6 + noiseStartIndex] = new Point(20, 480);
        data[7 + noiseStartIndex] = new Point(200, 400);
        data[8 + noiseStartIndex] = new Point(300, 400);
        data[9 + noiseStartIndex] = new Point(350, 120);

        Random.shuffle(data);
        Dbscan dbscan = new Dbscan(5, 30);
        dbscan.setDistanceMetric(DistanceMetrics.EUCLIDEAN_DISTANCE);

        // Transform Point array to Vector array.
        Vector[] samples = new Vector[data.length];
        for (int i = 0; i < samples.length; i++)
            samples[i] = new Vector(data[i].getX(), data[i].getY());

        // Run DBSCAN.
        int[] labels = dbscan.fitPredict(samples);

        // Draw points according to their labels.
        for (int i = 0; i < data.length; i++)
        {
            switch (labels[i])
            {
                case -1:
                    addPoint(canvas, data[i], Color.BLACK);
                    break;
                case 0:
                    addPoint(canvas, data[i], Color.ORANGE);
                    break;
                case 1:
                    addPoint(canvas, data[i], Color.GREEN);
                    break;
                case 2:
                    addPoint(canvas, data[i], Color.CYAN);
                    break;
                case 3:
                    addPoint(canvas, data[i], Color.PURPLE);
                    break;
                case 4:
                    addPoint(canvas, data[i], Color.BROWN);
                    break;
                case 5:
                    addPoint(canvas, data[i], Color.CRIMSON);
                    break;
                case 6:
                    addPoint(canvas, data[i], Color.YELLOW);
                    break;
                case 7:
                    addPoint(canvas, data[i], Color.BLUE);
                    break;
                case 8:
                    addPoint(canvas, data[i], Color.VIOLET);
                    break;
                default:
                    addPoint(canvas, data[i], Color.RED);
                    break;
            }
        }

        primaryStage.setTitle("DBSCAN Visualization");
        primaryStage.setScene(new Scene(canvas, 500, 500));
        primaryStage.show();
    }

    private static void addPoint(Pane canvas, Point point, Paint fill)
    {
        Circle c = new Circle(point.getX(), point.getY(), 5, fill);
        canvas.getChildren().add(c);

        // Draw a Text to show the coordinate of the clicked point when the mouse press on the point.
        c.setOnMousePressed(e ->
        {
            Text txtPosition = new Text(e.getX(), e.getY(), "(" + c.getCenterX() + ", " + c.getCenterY() + ")");
            canvas.getChildren().add(txtPosition);
            txtPositions.addLast(txtPosition);
        });

        // Clear all Text objects that shows the coordinate of the clicked point when mouse release.
        c.setOnMouseReleased(e ->
        {
            canvas.getChildren().removeAll(txtPositions);
            txtPositions.clear();
        });
    }
}
