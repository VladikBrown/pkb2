<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Exhibtion Details">
    <body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/itemDetails/${item.id}">
            <br>
            <div>
                <h2>
                        ${item.museumSet.name}
                </h2>
            </div>
            <br>
            <div>
                <h3>
                    Responsible person
                </h3>
            </div>
            <div class="form-group">
                <label style="font-size: large">First name</label>
                <label style="font-size: large">${item.responsiblePerson.firstName}</label>
            </div>
            <div class="form-group">
                <label style="font-size: large">Middle name</label>
                <label style="font-size: large">${item.responsiblePerson.middleName}</label>
            </div>
            <div class="form-group">
                <label style="font-size: large">Second name</label>
                <label style="font-size: large">${item.responsiblePerson.secondName}</label>
            </div>
            <br>
            <div>
                <h3>
                    Exhibition
                </h3>
            </div>
            <div>
                <label style="font-size: large">Exhibition name</label>
                <label style="font-size: large">${item.exhibition.name}</label>
            </div>
            <div>
                <label style="font-size: large">Exhibition address</label>
                <label style="font-size: large">${item.exhibition.address}</label>
            </div>
            <div>
                <label style="font-size: large">Exhibition phone number</label>
                <label style="font-size: large">${item.exhibition.phoneNumber}</label>
            </div>
        </form>
    </div>
    </body>
</tags:master>