<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tags:master pageTitle="Item Details">
    <body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/itemDetails/${item.id}">
            <br>
            <div class="form-group">
                <label for="inventoryNumberInput" style="font-size: large">Inventory number: </label>
                <input type="text" class="form-control" id="inventoryNumberInput" name="inventoryNumber"
                       placeholder="only digits" value="${item.inventoryNumber}">
            </div>
            <div class="form-group">
                <label for="itemNameInput" style="font-size: large">Item name</label>
                <input type="text" class="form-control" id="itemNameInput" name="itemName" value="${item.name}">
            </div>
            <div class="form-group">
                <label for="setNameInput" style="font-size: large">Set name</label>
                <input type="text" class="form-control" id="setNameInput" name="setName" value="${item.museumSet.name}">
            </div>
            <div class="form-group">
                <label for="fundNameInput" style="font-size: large">Fund name</label>
                <input type="text" class="form-control" id="fundNameInput" aria-describedby="emailHelp" name="fundName"
                       value="${item.fund.name}">
            </div>
            <div>
                <label for="creationDateInput" style="font-size: large">Creation date</label>
                <input type="text" class="form-control" id="creationDateInput" placeholder="dd.mm.yyyy"
                       name="creationDate" value="${item.creationDate}">
            </div>
            <br>
            <div>
                <h3>
                    Author
                </h3>
            </div>
            <input hidden="hidden" type="text" class="form-control" id="idInput" name="personId"
                   value="${item.author.id}">
            <div>
                <label for="authorFirstNameInput" style="font-size: large">First name</label>
                <input type="text" class="form-control" id="authorFirstNameInput" name="firstName"
                       value="${item.author.firstName}">
            </div>
            <div>
                <label for="authorMiddleNameInput" style="font-size: large">Middle name</label>
                <input type="text" class="form-control" id="authorMiddleNameInput" name="middleName"
                       value="${item.author.middleName}">
            </div>
            <div>
                <label for="authorSecondNameInput" style="font-size: large">Second name</label>
                <input type="text" class="form-control" id="authorSecondNameInput" name="secondName"
                       value="${item.author.secondName}">
            </div>
            <p></p>
            <textarea class="form-control " name="annotation" rows="5"
                      placeholder="Annotation">${item.annotation}</textarea>
            <p></p>
            <button type="submit" class="btn btn-primary">
                Update
            </button>
            <input type="hidden" name="_method" value="put"/>
        </form>
        <form action="${pageContext.servletContext.contextPath}/itemDetails/${item.id}" method="post">
            <button type="submit" class="btn btn-outline-danger">
                Delete
            </button>
            <input type="hidden" name="_method" value="delete"/>
        </form>

    </div>
    </body>
</tags:master>