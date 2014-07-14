package shorturl.APIs;

import shorturl.classes.Parameters;

public class QRApi {

    public String size;
    public String url;
    public String chartsetSource;

    public QRApi() {
    }

    public QRApi(String _url) {
        this.url = _url;
    }

    public QRApi(String _url, String _size) {
        this.url = _url;
        this.size = _size;
    }

    public QRApi(String _url, String _size, String _chartsetSource) {
        this.url = _url;
        this.size = _size;
        this.chartsetSource = _chartsetSource;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the chartsetSource
     */
    public String getChartsetSource() {
        return chartsetSource;
    }

    /**
     * @param chartsetSource the chartsetSource to set
     */
    public void setChartsetSource(String chartsetSource) {
        this.chartsetSource = chartsetSource;
    }

    public String getQR() {
        return Parameters.QR_API_CREATE + "?"
                + "data=" + url
                + "&size=" + (this.size.equals("") ? "150x150" : this.size)
                + "&charset-source=" + (this.chartsetSource != null ? 
                                        "UTF-8" : this.chartsetSource);
    }

    public String getURL() {
        return Parameters.QR_API_READ + "?fileurl=" + url;
    }
}
