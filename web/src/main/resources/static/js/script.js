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
           success:function (data, status, xhr){

           },
           error: function(err) {
               console.log(err);
               alert('An error occurred while downloading the file.');
           }
       })
    });

    $("#uploadBtn").click(function (){
        $('#uploadInput').click();
    });

    $("#uploadInput").change(function (event){
        event.preventDefault();

        var formData = new FormData();
        formData.append("file",event.target.files[0])

        $.ajax({
            url:'http://localhost:8082/import',
            type:'POST',
            data: formData,
            processData: false,
            contentType: false,
            success:function (){
                window.location.reload();
            }
        });
    });

    $("#pills-export-tab").click(function (){
        $.ajax({
            url:'http://localhost:8081/export/files',
            type:'GET',
            contentType:'application/json',
            success:function (data){
                const tbody = $("#exportBody");
                tbody.empty();

                if(data.length > 0){
                    data.forEach(item => {
                        tbody.append(`
                                <tr>
                                    <td>${item.filename}</td>
                                    <td>
                                        <button type="button" class="btn btn-sm btn-success" id="exportFileBtn" data-id="${item.logId}"><i class="bi bi-cloud-arrow-down"></i></button>
                                        <button type="button" class="btn btn-sm btn-danger" id="exportFileDeleteBtn" data-id="${item.logId}"><i class="bi bi-trash"></i></button>
                                    </td>
                                </tr>
                            `);
                    });
                }
            },
            error:function (err){
                console.log(err);
            }
        })
    });

    $("#exportBody").on('click','#exportFileBtn',function (){
       const id = $(this).attr("data-id");
       window.location.href = `http://localhost:8081/export/download/${id}`;
    });

    $("#exportBody").on('click','#exportFileDeleteBtn',function (){
       const id =  $(this).attr("data-id");
       $.ajax({
           url:'http://localhost:8081/export/file/'+id,
           type:'DELETE',
           success:function (){
               $("#pills-export-tab").trigger('click');
           }
       });
    });
});