<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
<link rel="stylesheet" href="view/home/widgEditor.css" />
body {font-family: Arial;}

/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
.accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
}

.active, .accordion:hover {
    background-color: #ccc; 
}

.panel {
    padding: 0 18px;
    display: none;
    background-color: white;
    overflow: hidden;
}
</style>
</head>
<body>

<h2>Welcome ${user.fullName} || ${user.id}</h2>

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'createBlog')">Create Blog</button>
  <button class="tablinks" id="myBlogBtn" onclick="openCity(event, 'myBlogs')">My Blogs</button>
</div>

<form name="CreateBlogForm" class="modal-content animate" method="POST" action="/${user.id}/blog">
<div id="createBlog" class="tabcontent">
  <h3>Create New Blog</h3>
    <input type="text" name="title" />
    <br/><br/>
    <textarea id="widgEditor" name="blogContent"></textarea>
    <br/>
  <button type="submit" >Create Blog</button>
</div>
</form>

<form id="myBlogsForm" method="POST" action="">
  <div id="myBlogs" class="tabcontent">
    <h3>My Blogs</h3>

    <button class="accordion">${blogs[0].title}</button>
    <div class="panel">
    <p>${blogs[0].blogContent}</p>
    </div>

      <button class="accordion">${blogs[1].title}</button>
          <div class="panel">
          <p>${blogs[1].blogContent}</p>
          </div>

      <button class="accordion">${blogs[2].title}</button>
          <div class="panel">
          <p>${blogs[2].blogContent}</p>
          </div>
  </div>
</form>

<script>
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";

}

var acc = document.getElementsByClassName("accordion");
var i;
for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}
</script>
     
</body>
</html> 