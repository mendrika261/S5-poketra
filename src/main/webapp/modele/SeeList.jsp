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
                <% if(request.getAttribute("matiere_premiere")!=null) { %>
                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="c-grey-900 mB-20">Matière première: <%= ((MatierePremiere)request.getAttribute("matiere_premiere")).getNom() %></h4>
                            <% if(request.getAttribute("modelViewList")!=null) { %>
                            <table id="table" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Modèle</th>
                                        <th>Format</th>
                                        <th>Style</th>
                                        <th>Quantité</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(MpModelView mpModelView: (List<MpModelView>)request.getAttribute("modelViewList")) { %>
                                    <tr>
                                        <td><%= mpModelView.getNomModele() %></td>
                                        <td><%= mpModelView.getNomFormat() %></td>
                                        <td><%= mpModelView.getNomStyle() %></td>
                                        <td><%= mpModelView.getQuantite() %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Modèle</th>
                                        <th>Format</th>
                                        <th>Style</th>
                                        <th>Quantité</th>
                                    </tr>
                                </tfoot>
                            </table>
                            <p>Fin de la liste <%= ((List<?>)request.getAttribute("modelViewList")).size() %> résultat(s)</p>
                            <% } %>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </main>
        <%@include file="/global/footer.jsp"%>
    </div>
</div>
</body>
</html>