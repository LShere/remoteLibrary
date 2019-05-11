<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Book</title>
</head>
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        form {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        form h2 {
            text-align: center;
        }
        .message {
            text-align: center;
        }
    </style>
<body>    

    <s:form action="Store" >
    <h2>
        <s:if test="null == book">
            Add Book
            
        </s:if>
        <s:else>
            Edit Book
        </s:else>
    </h2>
        <s:fielderror fieldName="errorMessage" cssStyle="Color: red;" class="message"></s:fielderror>
        <s:if test="book != null">
            <s:textfield name="book.isbn" label="ISBN" readonly="true"/>
           <input type="hidden" name="flag" value="edit" />
        </s:if>
        <s:else>
            <s:textfield name="book.isbn" label="ISBN"/>
            <input type="hidden" name="flag" value="add" />
        </s:else>
        <s:textfield name="book.title" label="Title" />
        <s:textfield name="book.price" label="Price" />
        <s:submit onclick="panduan();" />
    </s:form>
</body>
</html>