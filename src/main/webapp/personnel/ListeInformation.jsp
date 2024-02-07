<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.service.DetailsProduit" %>
<%@ page import="mg.s5poketra.model.personnel.InfoPersonnel" %>
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
                <h1>Liste Personnels</h1>
                <div class="row">
                    <div class="card">
                        <div class="card-body">

                            <table id="table" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>Id Personnel</th>
                                    <th>Nom</th>
                                    <th>Service</th>
                                    <th>Prix horaire de base</th>
                                    <th>Anciennete</th>
                                    <th>Date Affectation</th>
                                    <th>Nom poste</th>
                                    <th>Coefficient</th>
                                    <th>Prix horaire</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for(InfoPersonnel details: (List<InfoPersonnel>)request.getAttribute("infoPersoList")) { %>
                                <tr>
                                    <td><%= details.getId_personnel() %></td>
                                    <td><%= details.getNom() %></td>
                                    <td><%= details.getNom_service() %></td>
                                    <td><%= details.getPrix_horaire_base()%></td>
                                    <td><%= details.getAnciennete() %></td>
                                    <td><%= details.getDate_affectation() %></td>
                                    <td><%= details.getNom_poste() %></td>
                                    <td><%= details.getCoefficient() %></td>
                                    <td><%= details.getPrix_horaire() %></td>
                                </tr>
                                <% } %>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Id Personnel</th>
                                    <th>Nom</th>
                                    <th>Service</th>
                                    <th>Prix horaire de base</th>
                                    <th>Anciennete</th>
                                    <th>Date Affectation</th>
                                    <th>Nom poste</th>
                                    <th>Coefficient</th>
                                    <th>Prix horaire</th>
                                </tr>
                                </tfoot>
                            </table>
                            <p>Fin de la liste <%= ((List<?>)request.getAttribute("infoPersoList")).size() %> résultat(s)</p>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="/global/footer.jsp"%>
    </div>
</div>
</body>
</html>