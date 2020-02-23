<%@ page language="java"  contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>


springmvc上传文件
<form name="form1" action="/mmall/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile">
    <input type="submit" value="springmvc上传文件"/>
</form>

富文本图片上传文件
<form name="form1" action="/mmall/product/richtext_imp_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile">
    <input type="submit" value="富文本图片上传文件"/>
</form>

</body>
</html>
