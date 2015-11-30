//package ca.cgagnier.tests;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.apache.commons.io.IOUtils;
//import org.junit.Test;
//
///**
// * Tests for client code for the Server. But this is ugly oulala.
// * @author joris
// *
// */
//
//public class DemoTestHTTP {
//
//	// devrez changer entre maintenant et le jour ou vous mettrez en ligne
//	String base = "http://localhost:8080/rest/";
//
//
//	///////////       ATTENTION ON NE CONTRÖLE PAS DU TOUT L'ETAT DU SERVEUR
//
//	@Test
//	public void testSignIn() throws IOException{
//		InputStream is = null;
//		URL url = new URL(base+"social/signin/joris.deguet@gmail.com/pass");
//		try {
//			// on construit la requête HTTP
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setReadTimeout(10000);				/* milliseconds */
//			conn.setConnectTimeout(15000);			/* milliseconds */
//			conn.setRequestMethod("GET");			/*		La methode HTTP			*/
//			conn.setDoInput(true);					/*		va recuperer de l'info		*/
//			conn.connect();
//			int response = conn.getResponseCode();
//			System.out.println("Response code " + response); /*		status code		*/
//			is = conn.getInputStream();				/*		Le contenu de la réponse		*/
//			// Convert the InputStream into a string
//			String content = IOUtils.toString(is, "UTF-8");
//			System.out.println(content);
//		} finally {
//			// always close connection
//			if (is != null) {
//				is.close();
//			}
//		}
//	}
//
//}
