package wazzle.controller.common.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public interface Deserializer {
	
	static Supplier<Gson> gson() {
		return () -> new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
				.create();
	}
	
	static <T> List<T> deserialize(final Class<T> myClass, final String path) throws IOException {
	    return gson().get().fromJson(Files.readString(Path.of(path)), 
	    		TypeToken.getParameterized(List.class, myClass).getType());
	}
}