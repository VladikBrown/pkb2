<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:master pageTitle="Add movement">
    <div class="container">
        <br>
        <form action="${pageContext.request.contextPath}/addMovement" method="post">
            <div class="form-group">
                <label for="setNameInput" style="font-size: large">Set name</label>
                <input type="text" class="form-control" id="setNameInput" name="museumSetName">
            </div>
            <div class="form-group">
                <label for="acceptDateInput" style="font-size: large">Accept date</label>
                <input type="text" class="form-control" id="acceptDateInput" name="acceptDate" placeholder="yyyy-mm-dd">
            </div>
            <div class="form-group">
                <label for="transferDateInput" style="font-size: large">Transfer date</label>
                <input type="text" class="form-control" id="transferDateInput" name="transferDate"
                       placeholder="yyyy-mm-dd">
            </div>
            <div class="form-group">
                <label for="returnDateInput" style="font-size: large">Return date</label>
                <input type="text" class="form-control" id="returnDateInput" name="returnDate" placeholder="yyyy-mm-dd">
            </div>
            <br>
            <div>
                <h3>
                    Exhibition
                </h3>
            </div>
            <div>
                <label for="exhibitionNameInput" style="font-size: large">Exhibition name</label>
                <input type="text" class="form-control" id="exhibitionNameInput" name="exhibitionName">
            </div>
            <div>
                <label for="exhibitionAddressInput" style="font-size: large">Exhibition address</label>
                <input type="text" class="form-control" id="exhibitionAddressInput" name="exhibitionAddress">
            </div>
            <div>
                <label for="exhibitionPhoneNumberInput" style="font-size: large">Exhibition phone number</label>
                <input type="text" class="form-control" id="exhibitionPhoneNumberInput" name="exhibitionPhoneNumber">
            </div>
            <div>
                <h3>
                    Responsible Person
                </h3>
            </div>
            <div>
                <label for="authorFirstNameInput" style="font-size: large">First name</label>
                <input type="text" class="form-control" id="authorFirstNameInput" name="firstName">
            </div>
            <div>
                <label for="authorMiddleNameInput" style="font-size: large">Middle name</label>
                <input type="text" class="form-control" id="authorMiddleNameInput" name="middleName">
            </div>
            <div>
                <label for="authorSecondNameInput" style="font-size: large">Second name</label>
                <input type="text" class="form-control" id="authorSecondNameInput" name="secondName">
            </div>
            <p></p>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</tags:master>