<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.s5poketra.model.Unite" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.s5poketra.model.personnel.Poste" %>
<%@ page import="mg.s5poketra.model.personnel.Personnel" %>
<%@ page import="mg.s5poketra.model.service.Service" %>

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
            <h1>Insertion affectation</h1>
            <% if(request.getAttribute("error")!=null) { %>
            <p class="text-danger">
              <%= request.getAttribute("error") %>
            </p>
            <% } %>
            <form method="post" action="${pageContext.request.contextPath}/personnel/affectation" id="form">
              <div class="mb-3">
                <label class="form-label">Poste</label>
                <select name="idService" class="form-control form-select" required>
                  <% for(Service poste: (List<Service>)request.getAttribute("serviceList")) { %>
                  <option value="<%= poste.getId() %>">
                    <%= poste.getNomService() %>
                  </option>
                  <% } %>
                </select>
              </div>
              <div class="mb-3">
                <label class="form-label">Personnel</label>
                <select name="idPersonnel" class="form-control form-select" required>
                  <% for(Personnel personnel: (List<Personnel>)request.getAttribute("personnelList")) { %>
                  <option value="<%= personnel.getId() %>">
                    <%= personnel.getNom() %>
                  </option>
                  <% } %>
                </select>
              </div>
              <div class="mb-3">
                <label class="form-label">Date Affectation</label>
                <input type="date" name="dateAffectation" class="form-control" required>
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