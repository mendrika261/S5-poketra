<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.s5poketra.model.Unite" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.service.Service" %>
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
                        <h1>Insertion service par produit</h1>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post" action="${pageContext.request.contextPath}/service/insertionParProduit" id="form">
                            <div class="mb-3">
                                <label class="form-label">Service</label>
                                <select name="idService" class="form-control form-select" required>
                                    <% for(Service service: (List<Service>)request.getAttribute("serviceList")) { %>
                                    <option value="<%= service.getId() %>">
                                        <%= service.getNomService() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Heures</label>
                                <input type="number" name="heure" class="form-control" step="0.01" min="0" required value="0">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Modele</label>
                                <select name="idModele" class="form-control form-select" required>
                                    <% for(Modele modele: (List<Modele>)request.getAttribute("modeleList")) { %>
                                    <option value="<%= modele.getId() %>">
                                        <%= modele.getNom() %>
                                    </option>
                                    <% } %>s
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Nombre personnel</label>
                                <input type="number" name="personnel" class="form-control" step="0.01" min="0" required value="0">
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