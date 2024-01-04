<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.produit.MpModelView" %>
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
                <h1>Liste modèle par matière première</h1>
                <div class="row">
                    <div class="col-4">
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post">
                            <div class="mb-3">
                                <select name="idMatierePremiere" class="form-control">
                                    <% for(MatierePremiere matierePremiere: (List<MatierePremiere>)request.getAttribute("matiereList")) { %>
                                    <option value="<%= matierePremiere.getId() %>">
                                        <%= matierePremiere.getNom() %>
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
                    <% if(request.getAttribute("matiere_premiere")!=null) { %>
                    <h3 class="text-uppercase"><%= ((MatierePremiere)request.getAttribute("matiere_premiere")).getNom() %></h3>
                    <% } %>
                    <div class="col-12">
                        <% if(request.getAttribute("modelViewList")!=null) { %>
                        <ul>
                            <% for(MpModelView mpModelView: (List<MpModelView>)request.getAttribute("modelViewList")) { %>
                            <li><%= mpModelView.getNomModele() %> - <%= mpModelView.getNomFormat() %> (<%= mpModelView.getNomStyle() %>): <%= mpModelView.getQuantite() %></li>
                            <% } %>
                        </ul>
                        <br>
                        <p>Fin de la liste <%= ((List<?>)request.getAttribute("modelViewList")).size() %> résultat(s)</p>
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