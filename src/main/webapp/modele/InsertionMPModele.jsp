<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.s5poketra.model.Unite" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.produit.Format" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.produit.MpModelView" %>
<%@ page import="mg.s5poketra.model.produit.MpModele" %>

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
                        <h1>Insertion MP Modele</h1>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post"  id="form">

                            <div class="mb-3">
                                <label class="form-label">Modele</label>
                                <select name="idFormat" class="form-control form-select" required>
                                    <% for(MpModelView format: (List<MpModelView>)request.getAttribute("modeleList")) { %>
                                    <option value="<%= format.getIdFormat() %>">
                                        <%= format.getNomModele() %> <%= format.getNomFormat() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Matiere Premiere</label>
                                <select name="idMatiereP" class="form-control form-select" required>
                                    <% for(MatierePremiere matierePremiere: (List<MatierePremiere>)request.getAttribute("matiereList")) { %>
                                    <option value="<%= matierePremiere.getId() %>">
                                        <%= matierePremiere.getNom() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Quantite</label>
                                <input type="number" min="0" name="quantite" class="form-control" VALUE="0" required>
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Insérer" class="btn btn-success btn-color cur-p">
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