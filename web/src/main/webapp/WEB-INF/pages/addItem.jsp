<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Add item">
    <div class="container">
       <br>
        <form action="${pageContext.request.contextPath}/addItem" method="post">
            <div class="form-group">
                <label for="fundNameInput" style="font-size: large">Fund name</label>
                <input type="text" class="form-control" id="fundNameInput" aria-describedby="emailHelp" name="fundName">
            </div>
            <div class="form-group">
                <label for="setNameInput"  style="font-size: large">Set name</label>
                <input type="text" class="form-control" id="setNameInput" name="setName">
            </div>
            <div class="form-group">
                <label for="itemNameInput"  style="font-size: large">Item name</label>
                <input type="text" class="form-control" id="itemNameInput" name="itemName">
            </div>
            <div class="form-group">
                <label for="inventoryNumberInput"  style="font-size: large">Inventory number</label>
                <input type="text" class="form-control" id="inventoryNumberInput" name="inventoryNumber" placeholder="only digits">
            </div>
            <div>
                <label for="creationDateInput" style="font-size: large">Creation date</label>
                <input type="text" class="form-control" id="creationDateInput" placeholder="dd.mm.yyyy" name="creationDate">
            </div>
            <br>
            <div>
                <h3>
                    Author
                </h3>
            </div>
            <div>
                <label for="authorFirstNameInput" style="font-size: large" >First name</label>
                <input type="text" class="form-control" id="authorFirstNameInput" name="firstName">
            </div>
            <div>
                <label for="authorMiddleNameInput"  style="font-size: large">Middle name</label>
                <input type="text" class="form-control" id="authorMiddleNameInput" name="middleName">
            </div>
            <div>
                <label for="authorSecondNameInput" style="font-size: large">Second name</label>
                <input type="text" class="form-control" id="authorSecondNameInput" name="secondName">
            </div>
            <p></p>
            <textarea class="form-control " name="annotation" rows="5"
                      placeholder="Annotation"></textarea>
            <p></p>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</tags:master>