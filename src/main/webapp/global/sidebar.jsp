<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="loader">
  <div class="spinner"></div>
</div>

<script>
  window.addEventListener('load', function load() {
    const loader = document.getElementById('loader');
    setTimeout(function() {
      loader.classList.add('fadeOut');
    }, 300);
  });
</script>

<!-- #Left Sidebar ==================== -->
<div class="sidebar">
  <div class="sidebar-inner">
    <!-- ### $Sidebar Header ### -->
    <div class="sidebar-logo">
      <div class="peers ai-c fxw-nw">
        <div class="peer peer-greed">
          <a class="sidebar-link td-n" href="#">
            <div class="peers ai-c fxw-nw">
              <div class="peer">
                <div class="logo">
                  <img src="${pageContext.request.contextPath}/assets/logo.png" alt="">
                </div>
              </div>
              <div class="peer peer-greed">
                <h5 class="lh-1 mB-0 logo-text">PokE-tra</h5>
              </div>
            </div>
          </a>
        </div>
        <div class="peer">
          <div class="mobile-toggle sidebar-toggle">
            <a href="" class="td-n">
              <i class="ti-arrow-circle-left"></i>
            </a>
          </div>
        </div>
      </div>
    </div>

    <!-- ### $Sidebar Menu ### -->
    <ul class="sidebar-menu scrollable pos-r">
      <li class="nav-item mT-30 actived">
        <a class="sidebar-link" href="index.html">
                <span class="icon-holder">
                  <i class="c-blue-500 ti-home"></i>
                </span>
          <span class="title">Vue d'ensemble</span>
        </a>
      </li>

      <li class="nav-item dropdown">
        <a class="dropdown-toggle" href="javascript:void(0);">
                <span class="icon-holder">
                    <i class="c-orange-500 ti-pencil-alt"></i>
                  </span>
          <span class="title">Matières premières</span>
          <span class="arrow">
            <i class="ti-angle-right"></i>
          </span>
        </a>
        <ul class="dropdown-menu">
          <li>
            <a href="${pageContext.request.contextPath}/unite/insertion">Unité</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/matiere_premiere/insertion">Nouvelle matière première</a>
          </li>
        </ul>
      </li>

      <li class="nav-item dropdown">
        <a class="dropdown-toggle" href="javascript:void(0);">
                <span class="icon-holder">
                    <i class="c-purple-500 ti-palette"></i>
                  </span>
          <span class="title">Styles</span>
          <span class="arrow">
            <i class="ti-angle-right"></i>
          </span>
        </a>
        <ul class="dropdown-menu">
          <li>
            <a href="${pageContext.request.contextPath}/style/insertion">Nouveau style</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/style/insertion-matiere-premiere">Spécifier matière première</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/style/liste-matiere-premiere">Matières premières</a>
          </li>
        </ul>
      </li>



      <li class="nav-item dropdown">
        <a class="dropdown-toggle" href="javascript:void(0);">
                <span class="icon-holder">
                    <i class="c-purple-500 ti-shopping-cart"></i>
                  </span>
          <span class="title">Modèles</span>
          <span class="arrow">
            <i class="ti-angle-right"></i>
          </span>
        </a>
        <ul class="dropdown-menu">
          <li>
            <a href="${pageContext.request.contextPath}/modele/insertion">Insertion modèle</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/modele_format/insertion">Insertion format</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/modele_mpModele/insertion">Insertion matière première</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/liste/produit">Liste par matière première</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/listeParPrix/produit">Liste par Prix</a>
          </li>
        </ul>
      </li>

      <li class="nav-item dropdown">
        <a class="dropdown-toggle" href="javascript:void(0);">
                <span class="icon-holder">
                    <i class="c-purple-500 ti-blackboard"></i>
                  </span>
          <span class="title">Fabrication</span>
          <span class="arrow">
            <i class="ti-angle-right"></i>
          </span>
        </a>
        <ul class="dropdown-menu">
          <li>
            <a href="${pageContext.request.contextPath}/stock/entree">Entrée de stock</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/stock/sortie">Sortie de stock</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/stock/etat">Etat de stock</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/service/insertion">Insertion Service</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/service/insertionParProduit">Insertion Service Par Produit</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/listeServiceDetails/produit">Lister Details par benefice</a>
          </li>

        </ul>
      </li>
    </ul>
  </div>
</div>
