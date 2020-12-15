<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Item list">
    <div class="container">
        <br>
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Search item set" aria-label="Recipient's username"
                   aria-describedby="button-addon2">
            <div class="input-group-append">
                <form action="">
                    <button class="btn btn-outline-success my-2 my-sm-0" id="button-addon2" type="submit">Search
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div>
        <h2></h2>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">
                Item name
            </th>
            <th scope="col">
                Creation date
            </th>
            <th scope="col">
                Author
            </th>
            <th scope="col">
                Annotation
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="statusOrderItems">
            <tr class="row-${statusOrderItems.index % 2 == 0 ? "even" : ""}">
                <th scope="row">
                    <a href="${pageContext.servletContext.contextPath}/itemDetails/${item.id}">
                        ${item.name}
                    </a>
                </th>
                <td>${item.creationDate}</td>
                <td>
                        ${item.author}
                </td>
                <td>
                    ${item.annotation}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:master>