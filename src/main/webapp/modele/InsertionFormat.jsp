<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.s5poketra.model.Unite" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.produit.Modele" %>

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
                        <h1>Insertion Format Modele</h1>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post"  id="form">
                            <div class="mb-3">
                                <label class="form-label">Nom du Formqt</label>
                                <input type="text" placeholder="ex: XL" name="nom" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Modele</label>
                                <select name="idModele" class="form-control form-select" required>
                                    <% for(Modele modele: (List<Modele>)request.getAttribute("modeleList")) { %>
                                    <option value="<%= modele.getId() %>">
                                        <%= modele.getNom() %>
                                    </option>
                                    <% } %>
                                </select>
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