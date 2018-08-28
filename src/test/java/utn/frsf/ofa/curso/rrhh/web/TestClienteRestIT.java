package utn.frsf.ofa.curso.rrhh.web;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestClienteRestIT {

	@Ignore
	public void testGetCliente() throws IOException {
		 String MY_URL = "http://localhost:8080/rrhh-web/api/cliente";
		 HttpGet httpget = new HttpGet(MY_URL);
		 CloseableHttpClient cliente = HttpClients.createDefault();
		 CloseableHttpResponse response1 = (CloseableHttpResponse)cliente.execute(httpget);
		 HttpEntity entity1 = response1.getEntity();
		 String resultado = entidadToString(entity1.getContent());
		 assertEquals("GET", resultado.toUpperCase());
		 EntityUtils.consume(entity1);
		 response1.close();
	}
	private String entidadToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			}
		return sb.toString();
	}
	
	@Test
	public void testPostCliente() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode clienteJson = mapper.createObjectNode();
		clienteJson.put("id", 1);
		clienteJson.put("nombre", "martin");
		clienteJson.put("correo", "mdo@mail.com");
		clienteJson.put("cuit", "203040");
		StringEntity postingString;
		try {
			postingString = new StringEntity(clienteJson.toString());
			HttpPost httpPost = new HttpPost("http://localhost:8080/rrhh-web/api/cliente");
			httpPost.setEntity(postingString);
			httpPost.setHeader("Content-type", "application/json");
			CloseableHttpClient cliente = HttpClients.createDefault();
			CloseableHttpResponse response1 = cliente.execute(httpPost);
			HttpEntity entity1 = response1.getEntity();
			String resultado = entidadToString(entity1.getContent());
			assertEquals("POST MARTIN", resultado.toUpperCase());
			EntityUtils.consume(entity1);
			response1.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
