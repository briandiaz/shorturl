<%-- 
    Document   : showUrl
    Created on : Jul 5, 2014, 1:32:21 AM
    Author     : frangel
--%>

<%@page import="shorturl.persistence.PersistenceJPA"%>
<%@page import="shorturl.classes.Parameters"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="shorturl.entities.Url"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>

<div class="row">

    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">This is your Short URL </h3>
                </div>

                <%
                    
                    // ContextProduct context = new ContextProduct();
                    // context = new ContextURL();
                   // List<Url> urls = context.getAllUrls(request.getServletContext());
                   // for (Url link : urls) {
                         //System.out.print(link.getShortUrl()); 
                    PersistenceJPA persistence = PersistenceJPA.getSingletonInstance();
                    List<Url> urls = persistence.getListaUrl();
                     for(Url e : urls){
                %>



                <div class="panel-body">
                    <div class="list-group">

                        <span class="list-group-item-text"> 
                            
                            <a href="<%= "http://localhost:8080/shorturl/?l="+e.getShortUrl() %>"><%= "http://localhost:8080/shorturl/?l="+e.getShortUrl() %></a> <br/>
                            <%=e.getFullUrl() %>
                        </span> 
                    </div>
                </div>

                <% }%>
            </div>
        </div>

    </div>
</div>

<%@ include file="template/footer.jsp" %>