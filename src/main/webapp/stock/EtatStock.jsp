<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.produit.MpModelView" %>
<%@ page import="mg.s5poketra.model.stock.v_stock" %>
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
                <h1>Etat de stock</h1>
                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <table id="table" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Matiere Premiere</th>
                                        <th>Quantité</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(v_stock v: (List<v_stock>)request.getAttribute("stock")) { %>
                                    <tr>
                                        <td><%= v.getNom() %></td>
                                        <td><%= v.getSum() %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Matiere Premiere</th>
                                        <th>Quantité</th>
                                    </tr>
                                </tfoot>
                            </table>
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
