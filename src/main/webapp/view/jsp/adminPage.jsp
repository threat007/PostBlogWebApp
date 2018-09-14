<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}
td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<h2>Welcome ${user.fullName}</h2>

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'userList')">User List</button>
  <button class="tablinks" id="myBlogBtn" onclick="openCity(event, 'userBlogs')">Review Blogs</button>
</div>

<div id="userList" class="tabcontent">
    <form name="userListForm" class="modal-content animate" method="" action="">
        Option to create a new user

        <h2>Admin Users</h2>
        <table>
          <tr>
            <th>Admin User</th>
            <th>Actions</th>
          </tr>

          <tr>
            <td>Centro comercial Moctezuma</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Remove From Admin</button></td>
          </tr>
          <tr>
            <td>Ernst Handel</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Remove From Admin</button></td>
          </tr>
          <tr>
            <td>Island Trading</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Remove From Admin</button></td>
          </tr>
          <tr>
            <td>Laughing Bacchus Winecellars</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Remove From Admin</button></td>
          </tr>
          <tr>
            <td>Magazzini Alimentari Riuniti</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Remove From Admin</button></td>
          </tr>
        </table>

        <br/><br/>

        <h2>Blog Users</h2>

        <table>
          <tr>
            <th>User Name</th>
            <th>Actions</th>
          </tr>
          <tr>
              <td id="user0" style="display:block">${userList[0].fullName}</td>
              <td id="userEdit0" style="display:none"><input type="text">${userList[0].fullName}</input></td>
              <td><button type="button" onclick="showEdit()">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
            </tr>
          <tr>
            <td>${userList[1].fullName}</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
          </tr>
          <tr>
            <td>${userList[2].fullName}</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
          </tr>
          <tr>
            <td>Island Trading</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
          </tr>
          <tr>
            <td>Laughing Bacchus Winecellars</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
          </tr>
          <tr>
            <td>Magazzini Alimentari Riuniti</td>
            <td><button type="button">Edit</button><button type="button">Delete</button><button type="button">Make Admin</button></td>
          </tr>
        </table>
    </form>
</div>


<form id="reviewBlogsForm" method="POST" action="">
  <div id="userBlogs" class="tabcontent">
    <h3>User Blogs</h3>

    <button class="accordion">Blog One</button>
    <div class="panel">
        <p>Blog One Contents
        <button type="submit" name="approveBtn"> Approve </button><button type="submit" name="rejectBtn"> Reject </button>
        </p>
    </div>

     <button class="accordion">Blog Two</button>
     <div class="panel">
        <p>Blog Two Contents
        <button type="submit" name="approveBtn"> Approve </button><button type="submit" name="rejectBtn"> Reject </button>
        </p>
     </div>

     <button class="accordion">Blog Three</button>
     <div class="panel">
         <p>Blog Three Contents
         <button type="submit" name="approveBtn"> Approve </button><button type="submit" name="rejectBtn"> Reject </button>
         </p>
     </div>

  </div>
</form>

<script type="text/javascript">

function showEdit() {
    var u = document.getElementById("user0");
    var ud = document.getElementById("userEdit0");
    u.style.display = 'none';
    ud.style.display = 'block';
}

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