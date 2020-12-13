<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <title>${pageTitle}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link href="<c:url value="/resources/styles/main.css"/>" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
    <style>
        .logo {
            color: #f85c37;
            font-size: xx-large;
        }

        a {
            color: #f85c37;
        }

        a:hover, a:focus {
            color: #7b7b7b;
            text-decoration: none;
            outline: 0;
        }

        header img {
            max-width: 64px;
        }

        .navbar {
            font-weight: 800;
            font-size: 14px;
            padding-top: 15px;
            padding-bottom: 15px;
        }
        .navbar-inverse {
            background: #2d2d2d;
            border-color:  #2d2d2d;
        }
    </style>
</head>


<body class="phonify-head">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-fixed-top">
        <a class="navbar-brand logo" href="${pageContext.servletContext.contextPath}">
              Museum
          </a>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                      <a class="nav-link" href="${pageContext.request.contextPath}/exhibitList">Items list<span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${pageContext.request.contextPath}/addItem">Add item<span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item active">
                      <a class="nav-link" href="${pageContext.request.contextPath}/movements">Movements<span class="sr-only">(current)</span></a>
                  </li>
              </ul>
          </div>
      </nav>
  </header>

  <main>
    <jsp:doBody/>
  </main>
</body>
</html>