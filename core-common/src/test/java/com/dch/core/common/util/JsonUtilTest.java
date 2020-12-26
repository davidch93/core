package com.dch.core.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class used to test all methods in the {@link JsonUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
public class JsonUtilTest {

    @Test
    public void testCreateEmptyObjectNode() {
        ObjectNode actualObjectNode = JsonUtil.createEmptyObjectNode();
        assertThat(actualObjectNode.toString(), is(equalTo("{}")));
    }

    @Test
    public void testCreateEmptyArrayNode() {
        ArrayNode actualArrayNode = JsonUtil.createEmptyArrayNode();
        assertThat(actualArrayNode.toString(), is(equalTo("[]")));
    }

    @Test
    public void testToJsonNode_withByteArray() {
        byte[] data = "{\"id\":1,\"user\":\"data\"}".getBytes();
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNode_withNullByteArray_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.toJsonNode((byte[]) null));
        assertThat(ex.getMessage(), equalTo("Bytes array can't be null!"));
    }

    @Test
    public void testToJsonNode_withInvalidByteArray_thenExpectThrowException() {
        byte[] data = "data".getBytes();
        RuntimeException ex = assertThrows(RuntimeException.class, () -> JsonUtil.toJsonNode(data));
        assertThat(ex.getMessage(), equalTo("Invalid byte array of data!"));
    }

    @Test
    public void testToJsonNode_withString() {
        String data = "{\"id\":1,\"user\":\"data\"}";
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNode_withEmptyString_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.toJsonNode(""));
        assertThat(ex.getMessage(), equalTo("String data can't be empty!"));
    }

    @Test
    public void testToJsonNode_withNullString_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.toJsonNode((String) null));
        assertThat(ex.getMessage(), equalTo("String data can't be empty!"));
    }

    @Test
    public void testToJsonNode_withInvalidString_thenExpectThrowException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> JsonUtil.toJsonNode("data"));
        assertThat(ex.getMessage(), equalTo("Invalid JSON string!"));
    }

    @Test
    public void testToJsonNode_withInputStream() throws Exception {
        String path = "src/test/resources/valid.json";
        try (InputStream input = new FileInputStream(path)) {
            JsonNode jsonNode = JsonUtil.toJsonNode(input);
            int actualId = jsonNode.get("id").asInt();
            String actualUser = jsonNode.get("user").asText();

            assertThat(actualId, is(equalTo(1)));
            assertThat(actualUser, is(equalTo("data")));
        }
    }

    @Test
    public void testToJsonNode_withNullInputStream_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.toJsonNode((InputStream) null));
        assertThat(ex.getMessage(), equalTo("Input stream can't be null!"));
    }

    @Test
    public void testToJsonNode_withInvalidInputStream_thenExpectThrowException() throws Exception {
        String path = "src/test/resources/test.txt";
        try (InputStream input = new FileInputStream(path)) {
            RuntimeException ex = assertThrows(RuntimeException.class, () -> JsonUtil.toJsonNode(input));
            assertThat(ex.getMessage(), equalTo("Invalid JSON string!"));
        }
    }

    @Test
    public void testToJsonNode_withFile() {
        String path = "src/test/resources/valid.json";
        JsonNode jsonNode = JsonUtil.toJsonNode(new File(path));
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNode_withNullFile_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.toJsonNode((File) null));
        assertThat(ex.getMessage(), equalTo("File can't be null!"));
    }

    @Test
    public void testToJsonNode_withInvalidFile_thenExpectThrowException() {
        String path = "src/test/resources/test.txt";
        RuntimeException ex = assertThrows(RuntimeException.class, () -> JsonUtil.toJsonNode(new File(path)));
        assertThat(ex.getMessage(), equalTo("Invalid JSON string!"));
    }

    @Test
    public void testToJsonNode_withObject() {
        Data data = new Data(1, "data");
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNode_withNullObject_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.toJsonNode((Object) null));
        assertThat(ex.getMessage(), equalTo("Object data can't be null!"));
    }

    @Test
    public void testMerge_withJsonString() {
        String left = "{\"id\":1,\"user\":\"data\"}";
        String right = "{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}";
        JsonNode mergedJsonNode = JsonUtil.merge(left, right);
        int actualId = mergedJsonNode.get("id").asInt();
        String actualUser = mergedJsonNode.get("user").asText();
        String actualCreatedAt = mergedJsonNode.get("created_at").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data1")));
        assertThat(actualCreatedAt, is(equalTo("2019-01-01")));
    }

    @Test
    public void testMerge_withEmptyJsonString_thenExpectThrowException() {
        String left = "";
        String right = "{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.merge(left, right));
        assertThat(ex.getMessage(), equalTo("JSON left can't be empty!"));
    }

    @Test
    public void testMerge_withNullJsonString_thenExpectThrowException() {
        String left = "{\"id\":1,\"user\":\"data\"}";
        String right = null;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.merge(left, right));
        assertThat(ex.getMessage(), equalTo("JSON right can't be empty!"));
    }

    @Test
    public void testMergeJsonObject() {
        JsonNode left = JsonUtil.toJsonNode("{\"id\":1,\"user\":\"data\"}");
        JsonNode right = JsonUtil.toJsonNode("{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}");
        JsonNode mergedJsonNode = JsonUtil.merge(left, right);
        int actualId = mergedJsonNode.get("id").asInt();
        String actualUser = mergedJsonNode.get("user").asText();
        String actualCreatedAt = mergedJsonNode.get("created_at").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data1")));
        assertThat(actualCreatedAt, is(equalTo("2019-01-01")));
    }

    @Test
    public void testMergeJsonObject2() {
        JsonNode left = JsonUtil.createEmptyObjectNode();
        JsonNode right = JsonUtil.toJsonNode("{\"id\":1,\"user\":\"data1\",\"created_at\":\"2019-01-01\"}");
        JsonNode mergedJsonNode = JsonUtil.merge(left, right);
        int actualId = mergedJsonNode.get("id").asInt();
        String actualUser = mergedJsonNode.get("user").asText();
        String actualCreatedAt = mergedJsonNode.get("created_at").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data1")));
        assertThat(actualCreatedAt, is(equalTo("2019-01-01")));
    }

    @Test
    public void testMerge_withNullJsonObject_thenExpectThrowException() {
        JsonNode left = null;
        JsonNode right = JsonUtil.toJsonNode("{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.merge(left, right));
        assertThat(ex.getMessage(), equalTo("JSON left can't be null!"));
    }

    @Test
    public void testMerge_withNullJsonObject2_thenExpectThrowException() {
        JsonNode left = JsonUtil.toJsonNode("{\"id\":1,\"user\":\"data\"}");
        JsonNode right = null;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.merge(left, right));
        assertThat(ex.getMessage(), equalTo("JSON right can't be null!"));
    }

    @Test
    public void testReadValue() {
        String data = "{\"id\":1,\"user\":\"data\"}";
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        Map<String, Object> actualResult = JsonUtil.readValue(data, typeRef);

        assertThat(actualResult, hasEntry("id", 1));
        assertThat(actualResult, hasEntry("user", "data"));
    }

    @Test
    public void testReadValue_withEmptyString_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> JsonUtil.readValue("", null));
        assertThat(ex.getMessage(), equalTo("String data can't be empty!"));
    }

    @Test
    public void testReadValue_withNullTypeRef_thenExpectThrowException() {
        String data = "{\"id\":1,\"user\":\"data\"}";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                JsonUtil.readValue(data, null));
        assertThat(ex.getMessage(), equalTo("Value type reference can't be null!"));
    }

    @Test
    public void testReadValue_withInvalidJsonString_thenExpectThrowException() {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        RuntimeException ex = assertThrows(RuntimeException.class, () -> JsonUtil.readValue("data", typeRef));
        assertThat(ex.getMessage(), equalTo("Invalid JSON string!"));
    }

    /**
     * Class that represent model data for testing purposes.
     */
    static class Data {

        private int id;
        private String user;

        Data(int id, String user) {
            this.id = id;
            this.user = user;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
