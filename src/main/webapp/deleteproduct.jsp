<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <head>
    <title>Add Product Form</title>
    <style>
      /* Add some basic styles to the form */
      .product-form {
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f7f7f7;
        font-family: Arial, sans-serif;
      }
      .product-form label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
      }
      .product-form input[type="text"],
      .product-form textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        font-size: 16px;
        font-family: Arial, sans-serif;
      }
      .product-form textarea {
        height: 100px;
      }
      .product-form button {
        background-color: #4CAF50;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
      }
      .product-form button:hover {
        background-color: #3e8e41;
      }
    </style>
  </head>
  <body>
    <h1>Product Form</h1>
    <form class="product-form" action="deleteproduct"  method="post" enctype="multipart/form-data">
      <label for="product-name">Product Name:</label>
      <input type="text" id="product-name" name="p_name">

     
      
      <label for="product-category">Product Category:</label>
      <input type="text" id="product-name" name="pc_id">
     
      <br>
    

     

      <button type="submit">Delete Product</button>
    </form>
</body>
</html>