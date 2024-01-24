<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.produit.Modele" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.produit.MpModelView" %>

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

                <div class="row">
                    <div class="col-4">
                        <h1>Fabrication</h1>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post"  id="form">
                            <div class="mb-3">
                                <label class="form-label">Modèle</label>
                                <select name="idModele" class="form-control form-select" required>
                                    <% for(MpModelView format: (List<MpModelView>)request.getAttribute("modeleList")) { %>
                                    <option value="<%= format.getIdFormat() %>-<%= format.getIdStyle() %>">
                                        <%= format.getNomModele() %> - <%= format.getNomFormat() %> (<%= format.getNomStyle() %>)
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Quantité</label>
                                <input type="number" value="0" name="quantite" class="form-control" required step="0.02" min="0">
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Fabriquer" class="btn btn-success btn-color cur-p">
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </main>
        <%@include file="/global/footer.jsp"%>
    </div>
</div>
</body>
</html>
