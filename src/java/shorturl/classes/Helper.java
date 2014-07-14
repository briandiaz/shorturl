package shorturl.classes;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import shorturl.entities.Role;
import shorturl.entities.Url;
import shorturl.entities.UrlVisits;
import shorturl.entities.User;
import shorturl.persistence.PersistenceJPA;
import shorturl.servlets.ServletUser;

public class Helper {

    private static int _id;
    private static String _email;
    private static String _username;
    private static String _password;
    private static String _photo;
    private static int _role;

    public static boolean validateParamsUser(HttpServletRequest request) {
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        return (_email != null && _username != null && _password != null && _photo != null
                && !_email.isEmpty() && !_username.isEmpty() && !_photo.isEmpty()
                && !_password.isEmpty());
    }

    public static void createAdminUser() {
        ServletUser servlUser = new ServletUser();
        Role role = new Role();
        Role isRoleSet = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, 1);
        if (isRoleSet == null) {
            role.setName("Admin");
            role.setValue(1);
            System.out.println("Role Admin");
            PersistenceJPA.getSingletonInstance().create(role);
        }
        isRoleSet = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, 2);
        if (isRoleSet == null) {
            role = new Role();
            role.setName("Client");
            role.setValue(2);
            System.out.println("Role Client");
            PersistenceJPA.getSingletonInstance().create(role);
        }
        role = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, 1);
        User usr = new User();
        usr.setUsername("admin");
        usr.setPassword("admin");
        usr.setEmail("admin@admin.com");
        usr.setPhoto("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/t1.0-1/p160x160/10308232_10202539407424858_6494413392333976801_n.jpg");
        usr.setRole(role);
        if (!servlUser.UserValidated(usr)) {
            servlUser.create(usr);
        }
    }

    public static boolean validateUsuario(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userUsuarioProp);
        return (!_username.isEmpty() && _username != null);
    }

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return (currentUser(request) != null);
    }

    public static boolean isAdminUser(HttpServletRequest request) {
        return (isUserLoggedIn(request) && currentUser(request).getUsername().equals("admin"));
    }
    public static User currentUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        return (User) session.getAttribute(Parameters.userSessionProp);
    }

    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        ServletUser servlUser = new ServletUser();
        User user = (User) session.getAttribute(Parameters.userSessionProp);
        return servlUser.read(user);
    }

    public static boolean isUserValidToLogin(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userIDProp);
        _password = (String) request.getParameter(Parameters.userPasswordProp);
        return (!_username.isEmpty() && !_password.isEmpty() && _username != null && _password != null);
    }

    public static User createUsuarioInstance(HttpServletRequest request) {
        _email = request.getParameter(Parameters.userEmailProp);
        _username = request.getParameter(Parameters.userUsuarioProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        _photo = request.getParameter(Parameters.userPhotoProp);
        _role = Integer.parseInt(request.getParameter(Parameters.userRoleProp));
        Role role = (Role) PersistenceJPA.getSingletonInstance().read(Role.class, _role);
        User usr = new User();
        usr.setUsername(_username);
        usr.setPassword(_password);
        usr.setEmail(_email);
        usr.setPhoto(_photo);
        usr.setRole(role);
        return usr;
    }

    public static User createUsuarioLoginInstance(HttpServletRequest request) {
        _username = request.getParameter(Parameters.userIDProp);
        _password = request.getParameter(Parameters.userPasswordProp);
        return new User();
    }

    public static List<Url> getNotRegisteredUserURLs(HttpSession session) {
        return (List<Url>) session.getAttribute(Parameters.sessionDataURL);
    }

    public static List<Url> getRegisteredUserURLs(User current_user) {
        List<Url> urls = PersistenceJPA.getSingletonInstance().getListaUrl();
        List<Url> myUrls = new ArrayList<Url>();
        for (Url url : urls) {
            if (url.getUser() != null) {
                if (url.getUser().getId().equals(current_user.getId())) {
                    myUrls.add(url);
                }
            }
        }
        return myUrls;
    }

    public static List<Url> getAllURL() {
        return PersistenceJPA.getSingletonInstance().getListaUrl();
    }
    
    public static void addURLFromNotRegisteredUser(HttpSession session, Url url) {
        List<Url> urls;
        Date now = new Date();

        if (session.getAttribute(Parameters.sessionDataURL) != null) {
            urls = (List<Url>) session.getAttribute(Parameters.sessionDataURL);
        } else {
            urls = new ArrayList<Url>();
        }

        urls.add(url);
        session.setAttribute(Parameters.sessionDataURL, urls);
    }

    public static String getOS(HttpServletRequest request) {

        String os = "";
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = Parameters.osWindows;
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = Parameters.osMac;
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = Parameters.osUnix;
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = Parameters.osAndroid;
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = Parameters.osiPhone;
        } else {
            os = Parameters.osUnknown;
        }
        return os;
    }

    public static String getBrowserDetails(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();
        String browser = "";

        if (user.contains("msie")) {
            browser = Parameters.browserInternetExplorer;
        } else if (user.contains("safari") && user.contains("version")) {
            browser = Parameters.browserSafari;
        } else if (user.contains("opr") || user.contains("opera")) {
            browser = Parameters.browserOpera;
        } else if (user.contains("chrome")) {
            browser = Parameters.browserChrome;
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            browser = Parameters.browserNetScape;
        } else if (user.contains("firefox")) {
            browser = Parameters.browserFirefox;
        } else {
            browser = Parameters.browserUnknown;
        }
        return browser;
    }

    public static int[] getBrowserChartData(List<UrlVisits> myUrlVisits) {

     //"InternetExplorer", "Firefox", "Chrome", "Safari", "Opera", "NetScape", "Unknown"
        int[] browserVisits = {0, 0, 0, 0, 0, 0, 0};

        for (UrlVisits myUrlVisit : myUrlVisits) {
            if (myUrlVisit.getBrowser().equals(Parameters.browserInternetExplorer)) {
                browserVisits[0]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserFirefox)) {
                browserVisits[1]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserChrome)) {
                browserVisits[2]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserSafari)) {
                browserVisits[3]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserOpera)) {
                browserVisits[4]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserNetScape)) {
                browserVisits[5]++;
            }
            if (myUrlVisit.getBrowser().equals(Parameters.browserUnknown)) {
                browserVisits[6]++;
            }
        }
        return browserVisits;
    }
    
    public static int[] getOsChartData(List<UrlVisits> myUrlVisits) {

     //"Unknown", "Windows", "Mac", "Android", "iPhone", "Unix"
        int[] osVisits = {0, 0, 0, 0, 0, 0};

        for (UrlVisits myUrlVisit : myUrlVisits) {
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osUnknown)) {
                osVisits[0]++;
            }
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osWindows)) {
                osVisits[1]++;
            }
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osMac)) {
                osVisits[2]++;
            }
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osAndroid)) {
                osVisits[3]++;
            }
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osiPhone)) {
                osVisits[4]++;
            }
            if (myUrlVisit.getOperativeSystem().equals(Parameters.osUnix)) {
                osVisits[5]++;
            }
        }
        return osVisits;
    }
    
    public static List<String> getVisitsDateTimeDetail(List<UrlVisits> urlVisits){
        
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh");
        List<String> visitsDateTime = new ArrayList<String>();
        for(UrlVisits visit : urlVisits)
        {
            visitsDateTime.add(simpleDateFormat.format(visit.getCreatedAt()));
        }
        java.util.Collections.sort(visitsDateTime);
        return visitsDateTime;
    }
    
    public static HashMap getVisitsDateTimeChartData(List<String> dates){
        HashMap hm = new HashMap();
        Set<String> unique = new HashSet<String>(dates);
        for (String key : unique) {
            System.out.println(key + ": " + Collections.frequency(dates, key));
            hm.put(key,Collections.frequency(dates, key));
        }
        return hm;
    }
}
