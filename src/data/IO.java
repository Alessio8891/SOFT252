package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IO {

    public static JSONObject data;

    public static String read(String source) throws IOException {
        String json = new String ( Files.readAllBytes( Paths.get(source) ) );
        return json;
    }

    public static void parse(String source) throws ParseException, IOException {
        String json = IO.read(source);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        data = (JSONObject)obj;
    }

    public static void write(String destination) throws IOException {
        String content = IO.data.toJSONString();

        BufferedWriter writer = new BufferedWriter( new FileWriter(destination) );
        writer.write(content);
        writer.close();
    }

}
