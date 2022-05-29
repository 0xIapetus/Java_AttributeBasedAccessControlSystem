$(document).ready(function(){
    $('#myForm').submit(function(){
        var myObject = {

            resource : $('#resource').val(),
            subject: $('#subject').val(),
            action : $('#action').val(),
            location: $('#location').val(),
            time : $('#time').val(),
            ipAddress: $('#ipAddress').val(),
            ipAddress1: $('#ipAddress1').val(),
            device: $('#device').val()

        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            async : false,
            data: JSON.stringify(myObject),
            url: "http://localhost:8080/evaluate",
            success: function(responseData){

                alert(responseData.message);
            },
            error: function(){}
        });
    });
});
