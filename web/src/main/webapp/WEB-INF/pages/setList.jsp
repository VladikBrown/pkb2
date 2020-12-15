<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Set list">
    <div class="container">
        <table class="table">
            <tr>
                <th scope="col">
                    Set name
                </th>
                <th scope="col">
                    Movements
                </th>
            </tr>
            <c:forEach var="set" items="${sets}" varStatus="statusOrderItems">
                <tr class="row-${statusOrderItems.index % 2 == 0 ? "even" : ""}">
                    <th scope="row">
                        <a href="${pageContext.servletContext.contextPath}/itemList/ofMuseumSet/${set.id}">
                                ${set.name}
                        </a>
                    </th>
                    <th scope="row">
                        <a href="${pageContext.servletContext.contextPath}/movements/bySet/${set.id}">
                            Movements
                        </a>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </div>
</tags:master>