<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f0f0;
            padding: 50px;
        }
        .container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            margin: auto;
        }
        h1 {
            color: #333;
        }
        .input-field {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .button {
            display: block;
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
<body>
<div class="container">
        <h1>open Account</h1>
        <form action="OpenAccount" method="post">
            <input type="text" name="name" class="input-field" placeholder="Name" required>
            <input type="number" name="ammount" class="input-field" placeholder="Initial Ammount" required>
            <input type="password" name="Security-Pin" class="input-field" placeholder="Security Pin" required>
            <button type="submit" class="button">Get Account Number</button>
        </form>
        <form action="welcome.jsp" method="post">
         <button type="submit" class="button">Already Have an Account</button>
          </form>
    </div>
</body>
</html>