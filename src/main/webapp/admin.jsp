<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="WEB-INF/table_users_tag.tld" prefix="tut"%>

<!DOCTYPE html>
<html lang="en">

   <html>
      <head>
         <meta charset="utf-8">
             <meta name="viewport" content="width=device-width, initial-scale=1">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
         <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
         <link rel="stylesheet" href="style.css" />
         <script src="script.js"></script>
         <title>   Admin Home Page   </title>
      </head>
      <body>
      <div class="container">
         <p align="right">
                  Admin ${adminLastName} (<a href="logout">Logout</a>)
         </p>
         <p align="left">
                        <a href="add_user">Add new user</a>
         </p>

         <tut:display-table />

         <div class="modal fade" id="confirmDelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
           <div class="modal-dialog modal-dialog-centered">
             <div class="modal-content">
               <div class="modal-header">
                 <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
               Are you sure?
               </div>
               <div class="modal-footer">
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                 <a class="btn btn-danger btn-ok">Yes</a>
               </div>
             </div>
           </div>
         </div>
        <script>
          $('#confirmDelete').on('show.bs.modal', function(e) {
              $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
          });
          </script>
      </body>
   </html>

