<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Movement list">
    <div class="container">
        <br>
        <div class="input-group mb-3">
            <input form="search" type="text" class="form-control" placeholder="Search by date"
                   aria-label="Recipient's username"
                   aria-describedby="button-addon2" name="date">
            <div class="input-group-append">
                <form id="search" action="${pageContext.request.contextPath}/movements/bySetAndDay/${setId}">
                    <button class="btn btn-outline-success my-2 my-sm-0" id="button-addon2" type="submit">
                        Search
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
                Set name
            </th>
            <th scope="col">
                Exhibition
            </th>
            <th scope="col">
                Transfer date
            </th>
            <th scope="col">
                Return date
            </th>
            <th scope="col">
                Responsible person
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="statusOrderItems">
            <tr class="row-${statusOrderItems.index % 2 == 0 ? "even" : ""}">
                <th scope="row">
                    <a href="${pageContext.servletContext.contextPath}/itemMovementDetails/${item.id}">
                            ${item.museumSet.name}
                    </a>
                </th>
                <td>${item.exhibition.name}</td>
                <td>${item.transferDate}</td>
                <td>${item.returnDate}</td>
                <td>${item.responsiblePerson}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:master>