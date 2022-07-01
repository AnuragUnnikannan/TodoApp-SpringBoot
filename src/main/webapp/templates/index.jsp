<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../static/style.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>TODO App</title>
</head>
<body>
    <div class="main-container">
        <div class="main-item">
            <nav id="navbar">
                <div class="nav-items">
                    <h2 id="caption">TODO App</h2>
                </div>

                <div class="nav-items">
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Subscription</a></li>
                        <li><a href="#">About Us</a></li>
                    </ul>
                </div>

            </nav>
        </div>

        <div class="main-item" id="form">
            <form action="/" method="POST">
                <label for="title">Title</label><br>
                <input type="text" name="title" id="title"><br><br>
                <label for="description">Description</label><br>
                <textarea name="description" id="description"></textarea><br>
                <div id="btn-container">
                    <input type="submit" name="submit" id="add" class="btn" value="Add to List">
                    <input type="submit" name="submit" id="clear" class="btn" value="Clear List">
                </div>
            </form>
        </div>

        <hr>

        <div class="main-item" id="tasks">
            <h2>Your tasks</h2>
            <c:forEach items="${datas}" var="data">
                <div class="items">
                    <h3>${data.title}</h3>
                    <p>${data.description}</p>
                    <p>${data.cdate}</p>
                    <label class="switch_label"></label>
                    <input id="s1" type="checkbox" class="switch" data-id="${data.id}" <c:out value="${data.cstatus eq false ? '': 'checked'}"/>>
                    <a href="/delete/${data.id}" class="btn">Delete</a>
                </div>

            </c:forEach>
        </div>

    </div>
    <script>
        $(".switch").bind("click", function(){
                var divs = $(".switch");
                var index = divs.index($(this));
                console.log("clicked")
                var labels = document.getElementsByClassName("switch_label");
                if (this.checked) {
                    labels[index].innerHTML = "Done";
                } else {
                    labels[index].innerHTML = "Due";
                }
                var id=this.dataset.id;
                fetch('/togglestatus/'+id);

            });

            function checksubmitted() {
                    var switchs = document.getElementsByClassName("switch");
                    var labels = document.getElementsByClassName("switch_label");
                    for(var i=0;i<switchs.length;i++){
                        if (switchs[i].checked) {
                            labels[i].innerHTML = "Done";
                        } else {
                            labels[i].innerHTML = "Due";
                        }
                    }
                }
            checksubmitted();
    </script>
</body>
</html>
