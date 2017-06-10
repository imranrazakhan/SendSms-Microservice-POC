package org.restcomm.connect.http.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class GsonJaxRsProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

  private final Gson gson;

  public GsonJaxRsProvider() {
    gson = new Gson();
  }

  public long getSize( T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType  ) {
    return -1;
  }

  public boolean isWriteable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType ) {
    return true;
  }

  public void writeTo( T object,
                       Class<?> type,
                       Type genericType,
                       Annotation[] annotations,
                       MediaType mediaType,
                       MultivaluedMap<String, Object> httpHeaders,
                       OutputStream entityStream ) throws IOException, WebApplicationException
  {
    PrintWriter printWriter = new PrintWriter( entityStream );
    try {
      String json = gson.toJson( object );
      printWriter.write( json );
      printWriter.flush();
    } finally {
      printWriter.close();
    }
  }

  public boolean isReadable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType ) {
    return true;
  }

  public T readFrom( Class<T> type,
                     Type gnericType,
                     Annotation[] annotations,
                     MediaType mediaType,
                     MultivaluedMap<String, String> httpHeaders,
                     InputStream entityStream ) throws IOException, WebApplicationException
  {
    InputStreamReader reader = new InputStreamReader( entityStream, "UTF-8" );
    try {
    	
    	Map<String, String> map= gson.fromJson( reader, new TypeToken<Map<String, String>>() {}.getType() );
    	
    	//MultivaluedMap<String, String> form=new MultivaluedStringMap();
    	
    	MultivaluedMap<String, String> form=new MultivaluedHashMap();
    	
    	form.putSingle("To", map.get("To"));
    	form.putSingle("From", map.get("From"));
    	form.putSingle("Body", map.get("Body"));
    	
      return (T) form;
      
    } finally {
      reader.close();
    }
  }
  
//convert InputStream to String
	private  String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}