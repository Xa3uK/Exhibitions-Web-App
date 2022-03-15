<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add exhibition</title>
</head>

<body>
<div>
    <h2>Add exhibition</h2>
</div>
<form method="post" class="w3-selection w3-light-grey w3-padding">
    <label>Theme:
        <input type="text" name="theme" ><br/>
    </label>
    <label>Hall:
        <input type="text" name="hall" ><br/>
    </label>
    <label>Start Date:
        <input type="date" name="startDate" ><br/>
    </label>
    <label>End Date:
        <input type="date" name="endDate" ><br/>
    </label>
    <label>Start Time:
        <input type="time" name="startTime" ><br/>
    </label>
    <label>End Time:
        <input type="time" name="endTime" ><br/>
    </label>
    <label>Price:
        <input type="text" name="price" ><br/>
    </label>
    <button type="submit" >Add</button>
</form>

<div>
    <button onclick="location.href='/userPanel.jsp '">Back</button>
</div>
</body>
</html>