<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
<script src="../ckeditor.js"></script>
<script src="js/sample.js"></script>
<link rel="stylesheet" href="css/samples.css">
<link rel="stylesheet" href="toolbarconfigurator/lib/codemirror/neo.css">
<style>
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

<h2>Welcome Full Name</h2>

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'createBlog')">Create Blog</button>
  <button class="tablinks" onclick="openCity(event, 'myBlogs')">My Blogs</button>
</div>

<div id="createBlog" class="tabcontent">
  <h3>Create New Blog</h3>
  <div class="adjoined-bottom">
      <div class="grid-container">
          <div class="grid-width-100">
              <div id="editor">
                  <h1>Hello world!</h1>
                  <p>I'm an instance of <a href="https://ckeditor.com">CKEditor</a>.</p>
              </div>
          </div>
      </div>
  </div>
  <button type="submit" value="Create Blog" />
</div>

<div id="myBlogs" class="tabcontent">
  <h3>My Blogs</h3>
  
  <button class="accordion">Section 1<img src="xyz.png" alt="Edit" /></button>
  <div class="panel">
  <p>Blog contecta comes here.... </p>
</div>

<button class="accordion">Section 2</button>
<div class="panel">
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>

<button class="accordion">Section 3</button>
<div class="panel">
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>
</div>

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