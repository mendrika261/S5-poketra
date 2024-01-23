<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.s5poketra.model.Unite" %>
<%@ page import="java.util.List" %>

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
                        <h1>Insertion service</h1>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post" action="${pageContext.request.contextPath}/service/insertion" id="form">
                            <div class="mb-3">
                                <label class="form-label">Nom du service</label>
                                <input type="text" placeholder="ex: transport" name="nomService" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">prix Horaire</label>
                                <input type="number" name="prixHoraire" class="form-control" step="0.01" min="0" required value="0">
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