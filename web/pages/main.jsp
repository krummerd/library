
<%@page import="ua.krummer.web.beans.Author"%>
<%@page import="ua.krummer.web.beans.AuthorList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн библиотека</title>
        <link href="../css/style_main.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <div class="container">

            <div class="header">
                
                <div class="logo">
                    <img src="../images/library.png" alt="Р›РѕРіРѕС‚РёРї" name="logo" />
                </div>
                
                <div class="descr">
                    <h3>РћРЅР»Р°Р№РЅ Р±РёР±Р»РёРѕС‚РµРєР° РїСЂРѕРµРєС‚Р° javabegin.ru</h3>
                </div>

                <div class="search_form">
                    <form name="search_form" method="POST">
                        <input type="text" name="search_String" value="" size="110" />
                        <input class="search_button" type="submit" value="Поиск" name="search_button" />
                        <select name="search_option">
                            <option>Название</option>
                            <option>Автор</option>
                        </select>
                    </form>
                </div>
            </div>

             <div class="sidebar1">
                <h4>Список авторов:</h4>
                <ul class="nav">
                    <jsp:useBean id="authorList" class="ua.krummer.web.beans.AuthorList" scope="application"/>
                    <c:forEach var="author" items="${authorList.getAuthorList()}">

                        <li><a href="#">${author.name}</a></li>

                    </c:forEach>
                </ul>
                <p>&nbsp;</p>
            </div>

                
            <div class="content">
                <h1>&nbsp;</h1>
                <p>&nbsp;</p>
            </div>
                    
        </div><!-- end .container -->
                
    </body>
</body>

</body>
</html>
