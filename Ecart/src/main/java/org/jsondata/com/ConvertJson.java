package org.jsondata.com;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJson {
	
	public List<HashMap<String, String>> jsonData() throws IOException {
		String jsondata=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\jsondata\\com\\data.json"),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> rawData=mapper.readValue(jsondata,new TypeReference<List<HashMap<String, String>>>(){});
		
		return rawData;
	}

}
