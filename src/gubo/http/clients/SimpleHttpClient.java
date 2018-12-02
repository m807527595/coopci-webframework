package gubo.http.clients;

import gubo.http.querystring.QueryStringBinder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleHttpClient {

	static final MediaType FORM = MediaType
			.parse("application/x-www-form-urlencoded");

	OkHttpClient client = new OkHttpClient();
	String dateFormatStr = null;

	public Object post(String url, Object param, Class<?> type)
			throws JsonParseException, JsonMappingException, IOException,
			IllegalArgumentException, IllegalAccessException {
		QueryStringBinder binder = new QueryStringBinder();
		String postBody = binder.toQueryString(param, dateFormatStr);
		RequestBody formBody2 = RequestBody.create(FORM, postBody);
		Request request = new Request.Builder().url(url).post(formBody2)
				.build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		Object ret;
		ObjectMapper mapper = new ObjectMapper();
		ret = mapper.readValue(response.body().string(), type);
		return ret;
	}

}