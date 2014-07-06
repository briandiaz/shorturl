/*
 URL aURL = new URL(url);
 protocol = aURL.getProtocol();
 authority = aURL.getAuthority();
 host = aURL.getHost();
 port = aURL.getPort();
 path = aURL.getPath();
 query = aURL.getQuery();
 filename = aURL.getFile();
 ref = aURL.getRef();
 */
package shorturl.classes;

import com.sun.xml.wss.impl.misc.Base64;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

public class urlParser {

    private static String protocol;
    private static String authority;
    private static String host;
    private static int port;
    private static String path;
    private static String query;
    private static String filename;
    private static String ref;

    public boolean VirusScan(String filename) {
        return true;
    }

    public static boolean validateUrl(HttpServletRequest request) throws MalformedURLException {
        boolean flag = true;
        // creando un URL mediante el url que se manda por request
        URL link = new URL(request.getParameter("url"));
        // si se tiene HOST ya se tiene compuesta una URL         
        if (link.getHost() != null) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public static String EncodeUrl(String url) {
        String encoded = Base64.encode(url.getBytes());
        return encoded.substring(encoded.length() - 15, encoded.length() - 5);
    }
    /*
     public static String DecodeUrl(String url) {
     byte[] decoded = Base64.decode(url);
     return new String(decoded);
     }*/

    public static void main(String[] args) throws MalformedURLException {
        String vinculo = "http://www.pucmmsti.edu.do/domingo/sabado/index.jsp";
        System.out.println(EncodeUrl(vinculo));
    }
}
