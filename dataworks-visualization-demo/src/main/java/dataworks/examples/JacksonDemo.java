package dataworks.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dataworks.io.File;
import dataworks.mathematics.geometry.Point;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class JacksonDemo
{
    private static void testReadJson() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(File.readAllText("testData\\json\\TestTrajectoryConstructor.json"));

        // Try to get value of top-level key.
        System.out.println("TrajectoryId: " + rootNode.path("TrajectoryId").intValue());

        // Try to get JSON array.
        JsonNode points = rootNode.path("Points");
        for (int i = 0; i < points.size(); i++)
        {
            JsonNode point = points.get(i);
            System.out.println("X: " + point.path("X").intValue() + ", Y: " + point.path("Y").intValue());
        }
    }

    private static void testWriteJson() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        rootNode.put("Here", "There");
        int[] data = {3, 2, 1, 0};
        ArrayNode dataArray = rootNode.putArray("Data");
        for (int i : data)
            dataArray.add(i);

        PrintWriter writer = new PrintWriter("testData\\json\\TestWrite.json", StandardCharsets.UTF_8);
        mapper.writeValue(writer, rootNode);
    }

    private static void testSerialization() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Point point = new Point(5, 7);
        String pointJson = mapper.writeValueAsString(point);
        System.out.println(pointJson);
    }

    private static void testDeserialization() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        String pointJson = "{\"x\":5.0,\"y\":7.0}";
        Point point = mapper.readValue(pointJson, Point.class);
        System.out.println(point);
    }

    public static void main(String[] args) throws IOException
    {
        testReadJson();
        testWriteJson();
        testSerialization();
        testDeserialization();
    }
}
