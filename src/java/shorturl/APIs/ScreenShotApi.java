
package shorturl.APIs;

import shorturl.classes.Parameters;

/**
 *
 * @author Eder
 */
public class ScreenShotApi {
    
    public String url;
    
    public ScreenShotApi(){}
    
    public ScreenShotApi(String _url)
    {
        this.url = _url;
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
    
    public String getScreenShot(){
        return Parameters.ScreenShotApiURL+"?url="+this.url+
                "&instance_id="+Parameters.ScreenShotApiInstance+
                "&width="+Parameters.ScreenShotApiWidth+
                "&height="+Parameters.ScreenShotApiHeight+
                "&key="+Parameters.ScreenShotApiKey+
                "&format="+Parameters.ScreenShotApiFormat+
                "&shot="+Parameters.ScreenShotApiShot+
                "&quality="+Parameters.ScreenShotApiQuality+
                "&shots="+Parameters.ScreenShotApiShot+
                "&t="+Parameters.ScreenShotApiT;
    }
    
}
