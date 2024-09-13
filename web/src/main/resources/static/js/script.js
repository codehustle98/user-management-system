$(document).ready(function (){

    $("#saveBtn").click(function (){
        const name = $("#name").val();
        const email = $("#email").val();
        const gender = $("#gender").val();
        const age = $("#age").val();

        const user = {
            'name':name,
            'email':email,
            'gender':gender,
            'age':age
        }

        $.ajax({
            url: "/add",
            type:"POST",
            data: JSON.stringify(user),
            contentType:'application/json',
            success: function (){
                $("#exampleModal").modal('toggle');
                window.location.reload();
            }
        })
    });

    $("#exportBtn").click(function (){
       $.ajax({
           url:"http://localhost:8081/export",
           type: "GET",
           xhrFields: {
               responseType: 'blob'
           },
           success:function (data, status, xhr){
               const filename = "users";
               var disposition = xhr.getResponseHeader('Content-Disposition');

               var link = document.createElement('a');
               link.href = window.URL.createObjectURL(data);
               link.download = filename;
               document.body.appendChild(link);
               link.click();
               document.body.removeChild(link);
           },
           error: function() {
               alert('An error occurred while downloading the file.');
           }
       })
    });
});