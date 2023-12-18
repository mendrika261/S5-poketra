<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.Style" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<%@include file="/global/header.jsp"%>
<body class="app">
<div>
    <%@include file="/global/sidebar.jsp"%>
    <div class="page-container">
        <%@include file="/global/navbar.jsp"%>
        <main class="main-content bgc-grey-100">
            <div id="mainContent">
                <h1>Liste matière première pour un style</h1>
                <div class="row">
                    <div class="col-4">
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="get" action="${pageContext.request.contextPath}/style/liste-matiere-premiere">
                            <div class="mb-3">
                                <select name="idStyle" class="form-control">
                                    <% for(Style style: (List<Style>)request.getAttribute("styleList")) { %>
                                    <option value="<%= style.getId() %>">
                                        <%= style.getNom() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Rechercher" class="btn btn-color btn-success">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <% if(request.getAttribute("matierePremiereList")!=null) { %>
                        <ul>
                            <% for(MatierePremiere matierePremiere: (List<MatierePremiere>)request.getAttribute("matierePremiereList")) { %>
                            <li><%= matierePremiere.getNom() %></li>
                            <% } %>
                        </ul>
                        <br>
                        <p>Fin de la liste <%= ((List<?>)request.getAttribute("matierePremiereList")).size() %> résultat(s)</p>
                        <% } %>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="/global/footer.jsp"%>
    </div>
</div>
</body>
</html>