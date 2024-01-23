<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.service.DetailsProduit" %>
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
                <h1>Liste produit par prix de revient</h1>
                <div class="row">
                    <div class="col-4">
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post">
                            <div class="mb-3">
                                Entre
                                <input type="number" name="min" required value="0" step="0.01" class="form-control" min="0">
                                Et
                                <input type="number" name="max" required value="0" step="0.01" class="form-control" min="0">
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Rechercher" class="btn btn-color btn-success" required>
                            </div>
                        </form>
                    </div>
                </div>
                <% if (request.getAttribute("min") != null) { %>
                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="c-grey-900 mB-20">Prix entre <%= request.getAttribute("min") %> et <%= request.getAttribute("max") %></h4>
                            <% if(request.getAttribute("detailsList")!=null) { %>
                            <table id="table" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>idFormat</th>
                                    <th>cout total</th>
                                    <th>prix de Vente</th>
                                    <th>Benefice</th>
                                    <th>nom Format</th>
                                    <th>nom Modele</th>
                                    <th>nom style</th>

                                </tr>
                                </thead>
                                <tbody>
                                <% for(DetailsProduit details: (List<DetailsProduit>)request.getAttribute("detailsList")) { %>
                                <tr>
                                    <td><%= details.getIdFormat() %></td>
                                    <td><%= details.getCout() %></td>
                                    <td><%= details.getPrixVente() %></td>
                                    <td><%= details.getMarge() %></td>
                                    <td><%= details.getNomFormat() %></td>
                                    <td><%= details.getNomModele() %></td>
                                    <td><%= details.getNomStyle() %></td>



                                </tr>
                                <% } %>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>idFormat</th>
                                    <th>cout total</th>
                                    <th>prix de Vente</th>
                                    <th>Benefice</th>
                                    <th>nom Format</th>
                                    <th>nom Modele</th>
                                    <th>nom style</th>

                                </tr>
                                </tfoot>
                            </table>
                            <p>Fin de la liste <%= ((List<?>)request.getAttribute("detailsList")).size() %> résultat(s)</p>
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