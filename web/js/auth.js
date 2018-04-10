function authAjax() {
    var mail =  $("#e-mail").val();
    var password = $("#password").val();
    if (mail !== "" && password !== "") {
        $.ajax({
            url: "Authentication",
            data: {"mail": mail, "password": password},
            type: 'POST',
            success: function(serverData){
                var result = serverData.result;
                if (result == -1) {
                    $("#error_msg1").text("Неверный e-mail");
                } else if (result == -2) {
                    $("#error_msg1").text("Неверный пароль");;
                }
                else if (result == 0) {
                } else {
                    var url = "http://localhost:8080/edit.html";
                    $(location).attr('href',url);
                }
            },
            error: function () {

            }
        });
    }
}

function regAjax() {
    var fio =  $("#fio").val();
    var mail =  $("#r_e-mail").val();
    var password1 =  $("#password1").val();
    var password2 =  $("#password2").val();
    var number =  $("#number").val();
    var name = $("#name").val();
    if (fio !== "" && mail !== "" && password1 !== "" && password2 !== "" && number !== "" && name !== "") {
        if (password1 === password2) {
            $.ajax({
                url: "Registration",
                data: {"fio": fio, "mail": mail,  "password": password1, "number": number, "name": name},
                type: 'POST',
                success: function(serverData){
                    var result = serverData.result;
                    if (result == -1) {
                        $("#error_msg2").text("Группа не добавлена");
                    } else if (result == -2) {
                        $("#error_msg2").text("Пользователь не добавлен");
                    }
                    else if (result == 0) {

                    } else {
                        location.reload();
                    }
                },
                error: function () {

                }
            });
        }
        else {
            $("#error_msg2").text("Пароли не совпадают");
        }
    }
}

$(document).ready(function() {

    $("#click_reg").click(function () {
        $("#auth_box").css('visibility', 'hidden');
        $("#reg_box").css('visibility', 'visible');
        $("#form1").trigger("reset");
        $("#error_msg1").text("");
    });

    $("#click_return").click(function () {
        $("#auth_box").css('visibility', 'visible');
        $("#reg_box").css('visibility', 'hidden');
        $("#form2").trigger("reset");


    });
});