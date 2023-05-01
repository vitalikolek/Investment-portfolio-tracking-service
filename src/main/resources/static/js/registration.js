$(function() {
    $("#register").submit(function(event) {
        event.preventDefault();

        let customer = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            role: $("#role").val()
        };

        $.ajax({
            type: "POST",
            url: "/register",
            data: JSON.stringify(customer),
            contentType: "application/json",
            success: function() {
                console.log('Comment added successfully');
            },
            error: function() {
                console.log("Error adding comment");
            }
        });
    });
});
