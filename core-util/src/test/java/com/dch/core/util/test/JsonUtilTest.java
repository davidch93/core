package com.dch.core.util.test;

import com.dch.core.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class used to test all methods in the {@link JsonUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
public class JsonUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
    public void testToJsonNodeByteArray() {
        byte[] data = "{\"id\":1,\"user\":\"data\"}".getBytes();
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNodeNullByteArray() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Bytes array can't be null!");

        JsonUtil.toJsonNode((byte[]) null);
    }

    @Test
    public void testToJsonNodeString() {
        String data = "{\"id\":1,\"user\":\"data\"}";
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNodeEmptyString() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("String data can't be empty!");

        JsonUtil.toJsonNode("");
    }

    @Test
    public void testToJsonNodeNullString() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("String data can't be empty!");

        JsonUtil.toJsonNode((String) null);
    }

    @Test
    public void testToJsonNodeObject() {
        Data data = new Data(1, "data");
        JsonNode jsonNode = JsonUtil.toJsonNode(data);
        int actualId = jsonNode.get("id").asInt();
        String actualUser = jsonNode.get("user").asText();

        assertThat(actualId, is(equalTo(1)));
        assertThat(actualUser, is(equalTo("data")));
    }

    @Test
    public void testToJsonNodeNullObject() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Object data can't be null!");

        JsonUtil.toJsonNode((Object) null);
    }

    @Test
    public void testMergeJsonString() {
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
    public void testMergeEmptyJsonString() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("JSON left can't be empty!");

        String left = "";
        String right = "{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}";
        JsonUtil.merge(left, right);
    }

    @Test
    public void testMergeNullJsonString() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("JSON right can't be empty!");

        String left = "{\"id\":1,\"user\":\"data\"}";
        String right = null;
        JsonUtil.merge(left, right);
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
    public void testMergeNullJsonObject() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("JSON left can't be null!");

        JsonNode left = null;
        JsonNode right = JsonUtil.toJsonNode("{\"user\":\"data1\",\"created_at\":\"2019-01-01\"}");
        JsonUtil.merge(left, right);
    }

    @Test
    public void testMergeNullJsonObject2() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("JSON right can't be null!");

        JsonNode left = JsonUtil.toJsonNode("{\"id\":1,\"user\":\"data\"}");
        JsonNode right = null;
        JsonUtil.merge(left, right);
    }

    /**
     * Class that represent model data for testing purposes.
     */
    public class Data {

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
