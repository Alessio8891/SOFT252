package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

    public String source = "test/data/test.json";

    @BeforeEach
    void setUp() {
        IO.data = null;
    }

    @AfterEach
    void tearDown() throws IOException {
        IO.data = null;

        String content = "{\n  \"int\": 1,\n  \"string\": \"str\",\n  \"list\": [\"str\", \"str\"],\n}";;
        BufferedWriter writer = new BufferedWriter( new FileWriter(source) );
        writer.write(content);
        writer.close();
    }

    @Test
    void read() throws IOException {
        System.out.println("read");

        String result = IO.read(source);

        String expResult = "{\n  \"int\": 1,\n  \"string\": \"str\",\n  \"list\": [\"str\", \"str\"],\n}";

        assertEquals(expResult, result);
    }

    @Test
    void parse() throws IOException, ParseException {
        System.out.println("parse");
        IO.parse(source);

        JSONObject expResult = new JSONObject();

        expResult.put("int", 1);
        expResult.put("string", "str");

        JSONArray list = new JSONArray();
        list.add("str");
        list.add("str");
        expResult.put("list", list);

        assertEquals(expResult.toJSONString(), IO.data.toJSONString());
    }

    @Test
    void write() throws IOException, ParseException {
        System.out.println("write");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse("{\n  \"int\": 1,\n  \"string\": \"str\",\n  \"list\": [\"str\", \"str\"],\n}");
        IO.data = (JSONObject)obj;

        IO.data.put("new", "val");
        IO.write(source);

        String result = new String ( Files.readAllBytes( Paths.get(source) ) );
        String expResult = "{\"new\":\"val\",\"string\":\"str\",\"list\":[\"str\",\"str\"],\"int\":1}";

        assertEquals(expResult, result);
    }
}