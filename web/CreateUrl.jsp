<%-- 
    Document   : CreateUrl
    Created on : Jul 4, 2014, 8:14:55 PM
    Author     : frangel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>

 <div class="row">
    
                <div class="row vertical-offset-100">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Create your URL </h3>
                            </div>
                            <div class="panel-body">
                                <form accept-charset="UTF-8" role="form" method="POST" action="./createURL">
                                    <fieldset>
                                         <div class="form-group">
                                             Enter your url,a  long URL is a website address such as:</br><span class="text-primary ">https://www.pucmmsti.edu.do/websise/estudiante/</span> 
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" placeholder="url" name="url" id="url" type="text" value="" autofocus >
                                        </div>
                                        
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Crear">
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                   
                </div>
            </div>
<%@ include file="template/footer.jsp" %>
