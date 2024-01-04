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
                        <h1>Insertion unite</h1>

                        <% if(request.getAttribute("error")!=null) { %>
                        <p class="text-danger">
                            <%= request.getAttribute("error") %>
                        </p>
                        <% } %>

                        <form method="post" action="${pageContext.request.contextPath}/unite/insertion" id="form">
                            <div class="mb-3">
                                <label class="form-label">Nom de l'unité</label>
                                <input type="text" placeholder="ex: Kg" name="nom" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <input type="submit" value="Insérer" class="btn btn-success btn-color">
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