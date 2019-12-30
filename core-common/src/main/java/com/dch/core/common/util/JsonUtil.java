package com.dch.core.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Classes that provides function to manipulate JSON.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
public class JsonUtil {

    /**
     * Default config for {@link ObjectMapper}
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Create empty {@link JsonNode}.
     *
     * @return the empty {@link JsonNode}.
     */
    public static ObjectNode createEmptyObjectNode() {
        return objectMapper.createObjectNode();
    }

    /**
     * Create empty {@link ArrayNode}.
     *
     * @return the empty {@link ArrayNode}.
     */
    public static ArrayNode createEmptyArrayNode() {
        return objectMapper.createArrayNode();
    }

    /**
     * Convert byte arrays to {@link JsonNode}.
     *
     * @param data the byte arrays of data.
     * @return the {@link JsonNode}
     */
    public static JsonNode toJsonNode(byte[] data) {
        Assert.notNull(data, "Bytes array can't be null!");

        try {
            return objectMapper.readTree(data);
        } catch (IOException ex) {
            throw new RuntimeException("Invalid byte array of data!", ex);
        }
    }

    /**
     * Convert json string to {@link JsonNode}.
     *
     * @param data the JSON string.
     * @return the {@link JsonNode}
     */
    public static JsonNode toJsonNode(String data) {
        Assert.hasLength(data, "String data can't be empty!");

        try {
            return objectMapper.readTree(data);
        } catch (IOException ex) {
            throw new RuntimeException("Invalid JSON string!", ex);
        }
    }

    /**
     * Convert object data to {@link JsonNode}.
     *
     * @param data the object data.
     * @return the {@link JsonNode}
     */
    public static JsonNode toJsonNode(Object data) {
        Assert.notNull(data, "Object data can't be null!");

        return objectMapper.valueToTree(data);
    }

    /**
     * Merge two {@link JsonNode} instances if they are compatible.
     * <p>
     * <b>*Note</b> this function will be merge the two given {@link JsonNode} with right priority.
     * So the right {@link JsonNode} must be hold the latest data.
     * </p>
     *
     * @param left  a JSON string.
     * @param right a JSON string.
     * @return a merged {@link JsonNode}.
     */
    public static JsonNode merge(String left, String right) {
        Assert.hasLength(left, "JSON left can't be empty!");
        Assert.hasLength(right, "JSON right can't be empty!");

        return JsonUtil.merge(JsonUtil.toJsonNode(left), JsonUtil.toJsonNode(right));
    }

    /**
     * Merge two {@link JsonNode} instances if they are compatible.
     * <p>
     * <b>*Note</b> this function will be merge the two given {@link JsonNode} with right priority.
     * So the right {@link JsonNode} must be hold the latest data.
     * </p>
     *
     * @param left  a {@link JsonNode}.
     * @param right a {@link JsonNode}.
     * @return a merged {@link JsonNode}.
     */
    public static JsonNode merge(JsonNode left, JsonNode right) {
        Assert.notNull(left, "JSON left can't be null!");
        Assert.notNull(right, "JSON right can't be null!");

        ObjectNode merged = left.deepCopy();
        right.fields().forEachRemaining(field -> merged.set(field.getKey(), field.getValue()));
        return merged;
    }
}
