<!DOCTYPE html>
<html>
<head>
    <title>图片上传</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <h1>图片上传</h1>
        <div>
            <form action="imageController/uploadImage" method="post" enctype="multipart/form-data">
                    File to upload: <input type="file" name="upfile"><br/>
  Notes about the file: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="Press"> to upload the file!
            </form>
        </div>

</body>
</html>