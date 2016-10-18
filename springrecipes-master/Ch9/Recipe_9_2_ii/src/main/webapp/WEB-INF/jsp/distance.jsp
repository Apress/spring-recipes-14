<html>
<head>
    <title>City Distance</title>
</head>

<body>
<form method="POST">
    <table>
        <tr>
            <td>Source City</td>
            <td><input type="text" name="srcCity" value="${param.srcCity}"/></td>
        </tr>
        <tr>
            <td>Destination City</td>
            <td><input type="text" name="destCity" value="${param.destCity}"/></td>
        </tr>
        <tr>
            <td>Distance</td>
            <td>${distance}</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Find"/></td>
        </tr>
    </table>
</form>
</body>
</html>
