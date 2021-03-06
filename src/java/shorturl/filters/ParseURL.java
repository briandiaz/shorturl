/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shorturl.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shorturl.APIs.IPApi;
import shorturl.classes.Helper;
import shorturl.classes.Parameters;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.persistence.PersistenceJPA;

/**
 *
 * @author frangel
 */
public class ParseURL implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ParseURL() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ParseURL:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ParseURL:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("ParseURL:doFilter()");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI().toString();
        String encode = httpRequest.getParameter("l");
        List<Url> urls = PersistenceJPA.getSingletonInstance().getListaUrl();
        doBeforeProcessing(request, response);
        String uri = "";
        Url myUrlVisited = null;
        if (encode != null && !encode.isEmpty()) {
            myUrlVisited = PersistenceJPA.getSingletonInstance().getUrlByShortURL(encode);
            uri = myUrlVisited.getFullUrl();
        }
        Helper.createAdminUser();
        if (!uri.equals("")) {
            createVisit(httpRequest,httpResponse,myUrlVisited,uri);
        } else {
            proccessRedirections(httpRequest,httpResponse,url);
        }
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }


    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("ParseURL:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ParseURL()");
        }
        StringBuffer sb = new StringBuffer("ParseURL(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }


    private void createVisit(HttpServletRequest httpRequest, 
            HttpServletResponse httpResponse,
            Url myUrlVisited, String uri) throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getLocalHost();
        IPApi ipApi = new IPApi(null);
        UrlVisits urlvisit = new UrlVisits(1);
        urlvisit.setClientDomain(address.getLoopbackAddress().toString());
        urlvisit.setBrowser(Helper.getBrowserDetails(httpRequest));
        urlvisit.setOperativeSystem(Helper.getOS(httpRequest));
        urlvisit.setIp(httpRequest.getRemoteAddr());
        urlvisit.setUrl(myUrlVisited);
        urlvisit.setCreatedAt(new Date());
        urlvisit.setCountry(ipApi.getCountry());
        urlvisit.setCountryCode(ipApi.getCountryCode());
        PersistenceJPA.getSingletonInstance().create(urlvisit);
        httpResponse.sendRedirect(uri);
    }

    private void proccessRedirections(HttpServletRequest httpRequest, 
            HttpServletResponse httpResponse, String url) throws IOException {
       
            boolean isCurrentUser = Helper.isUserLoggedIn(httpRequest);
        if (!isCurrentUser
                && !url.equals(Parameters.rootPath + Parameters.loginPage)
                && !url.equals(Parameters.rootPath + Parameters.registerPage)
                && !url.equals(Parameters.rootPath + Parameters.homePage)
                && !url.equals(Parameters.rootPath + Parameters.notUserURLSPage)
                && !url.equals(Parameters.rootPath + Parameters.showURLPage)) {

            httpResponse.sendRedirect(Parameters.loginPage);

        } else if (isCurrentUser
                && url.equals(Parameters.rootPath + Parameters.loginPage)
                && !url.equals(Parameters.rootPath + Parameters.homePage)) {

            httpResponse.sendRedirect("myURL.jsp");
        }else if( !Helper.isAdminUser(httpRequest) && 
                ((url.equals(Parameters.rootPath + Parameters.manageUrlsPage))
                || url.equals(Parameters.rootPath + Parameters.manageUsersPage))
                ){
            httpResponse.sendRedirect(Parameters.homePage);
        } 
    }

}
