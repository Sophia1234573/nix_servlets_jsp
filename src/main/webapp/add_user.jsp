<%@ page contentType="text/html; charset=utf-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

   <html>
      <head>
         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">

         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
             <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
             <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
             <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
             <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

         <title>   Add User   </title>
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" type="text/css" />
             <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" type="text/javascript"></script>

             <script type="text/javascript">
                          $(function () {
                              $('#birthday').datepicker({
                                  format: "dd/mm/yyyy"
                              });
                          });
                      </script>
         <style>
                 label.error.fail-alert {
         border: 2px solid red;
         border-radius: 4px;
         line-height: 1;
         padding: 2px 0 6px 6px;
         background: #ffe6eb;
         }
         input.valid.success-alert {
         border: 2px solid #4CAF50;
         color: green;
         }
             </style>
      </head>
      <body>

<div class="container">
    <p align="right">
        Admin ${adminLastName} (<a href="logout">Logout</a>)


    <p class="h2">Add user</p>
    <form id="basic-form" action="/add_user" method="post">

    <div class="mb-3">
        <label for="login" class="form-label">Login</label>
        <input type="text" class="form-control" id="login" name="login" value="" >
    </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="" >
        </div>

        <div class="mb-3">
            <label for="password_again" class="form-label">Password again</label>
            <input type="password" class="form-control" id="password_again" name="password_again" value="" >
        </div>

    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" value="">
    </div>
        <div class="mb-3">
            <label for="first_name" class="form-label">First Name</label>
            <input type="text" class="form-control" id="first_name" name="first_name" value="">
        </div>

        <div class="mb-3">
            <label for="last_name" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="last_name" name="last_name" value="">
        </div>

        <div class="mb-3">
            <label for="birthday" class="form-label">Birthday</label>
            <input type="text" class="form-control" id="birthday" name="birthday" placeholder="Enter your birthday in the format dd/mm/yyyy" value="">
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" name="role">
              <option value="user">User</option>
              <option value="admin">Admin</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Ok</button>
        <a href="admin.jsp" class="btn btn-secondary">Cancel</a>
</form>
<script>
$(document).ready(function() {

$("#basic-form").validate({
errorClass: "error fail-alert",
validClass: "valid success-alert",

rules: {
login : {
required: true,
minlength: 3
},
password: {
required: true,
minlength: 5
},
password_again: {
required: true,
minlength: 5,
equalTo: "#password"
},
email: {
required: true,
email: true
},
first_name: {
required: true
},
last_name: {
required: true
},
birthday: {
required: true
},
role: {
required: true
}
},
messages : {
    login: {
    required: "Please, enter your login.",
    minlength: "Length of your login is less then 3 characters."
    },
    password: {
    required: "Please, enter your password.",
    minlength: "Length of your password is less then 5 characters.",
    },
    password_again: {
    required: "Please, confirm your password.",
    minlength: "Length of your password is less then 5 characters.",
    equalTo: "The password does not match the entered value."
    },
    email: {
    required: "Please, enter your email.",
    email: "Entered email is not valid. The email should be in the format: abc@domain.tld"
    },
    first_name: {
    required: "Please, enter your first name."
    },
    last_name: {
    required: "Please, enter your last name."
    },
    birthday: {
    required: "Please, enter your birthday in the format dd/mm/yyyy."
    },
    role: {
    required: "Please, select the role."
    },
    },
});
});

</script>

  </div>
 </body>
</html>

