/**
 * Created by arun on 1/6/17.
 */
$(document).ready(function () {
    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
    $("#login").click(function (e) {
        e.preventDefault();
        var username = $("#username").val();
        var userpassword = $("#userpassword").val();
        if (username == "") {

        }
        if (userpassword == "") {

        }
        if (username != "" && userpassword != "") {

            var credential = JSON.stringify({
                userName: username,
                password: userpassword
            });
            $.ajax({

                type: "POST",
                dataType: 'text',
                contentType: 'application/json',
                url: "rest/login/dologin",
                data: credential,

                success: function (data) {
                    alert("Success");
                    window.location.replace("/html/bid.html");
                },
                error: function (data) {
                    alert("Failed");
                    $("#password").val(data);
                }
            });
        }
    })
});