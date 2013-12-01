package org.schooldesk.webservice;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

public class ServletHelper {
	private static final ObjectMapper mapper = new ObjectMapper();

	private ServletHelper(){
	}

	public static <T> T readValue(String content, Class<T> clazz) throws IOException {
		return mapper.readValue(content, clazz);
	}
	public static void writeResponse(boolean success, String message, Object data, OutputStream os) throws IOException {
		Response response = new Response(success, message, data);
		mapper.writeValue(os, response);
	}

	private static class Response{
		boolean success;
		String message;
		Object data;

		Response(boolean success, String message, Object data) {
			this.success = success;
			this.message = message;
			this.data = data;
		}
	}
}
