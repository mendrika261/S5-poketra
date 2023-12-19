<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.MatierePremiere" %>
<%@ page import="mg.s5poketra.model.Style" %>
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
                <div class="row">
                    <div class="col-4">
                        <h2>Insertion matière première pour un style</h2>
                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>
                        <form method="post" action="${pageContext.request.contextPath}/style/insertion-matiere-premiere" id="form">
                            <div class="mb-3">
                                <label class="form-label">Matière première</label>
                                <select name="idMatierePremiere" class="form-control" required>
                                    <% for(MatierePremiere mp: (List<MatierePremiere>)request.getAttribute("matierePremiereList")) { %>
                                    <option value="<%= mp.getId() %>">
                                        <%= mp.getNom() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Style</label>
                                <select name="idStyle" class="form-control" required>
                                    <% for(Style style: (List<Style>)request.getAttribute("styleList")) { %>
                                    <option value="<%= style.getId() %>">
                                        <%= style.getNom() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Spécifier" class="btn btn-success btn-color btn">
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