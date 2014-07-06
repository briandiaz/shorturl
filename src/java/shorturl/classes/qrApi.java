package shorturl.classes;

/**
 *
 * @author frangel
 */
public class qrApi {

    private String url;
    private String qr;

    public String getQR(String url) {
        return "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + url;
    }

    public String getURL(String qr) {
        return "http(s)://api.qrserver.com/v1/read-qr-code/?fileurl=" + qr;
    }

}
