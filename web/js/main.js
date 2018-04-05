$(document).ready(function(){

    $(window).load(function(){
        getSchedules();
        getStudents();
    });

    function getSchedules() {
        $.ajax
        ({
            type: "POST",
            data: {},
            url: 'GetSchedule',
            success: function (serverData) {
                var result = serverData.result;
                if (result >= 0) {
                    var schedule_ids = serverData.schedule_ids.split(",");
                    var names = serverData.names.split(",");

                    var prevOption = $("#schedules").children();
                    for (var i = 0; i < prevOption.length; i++)
                    {
                        prevOption[i].remove();
                    }

                    var select = $("#schedules");
                    for (var i = 0; i < result; i++) {
                        var option = $('<option>');
                        option.attr('value', schedule_ids[i]);
                        option.text(names[i]);
                        select.append(option);
                    }
                }
                else {
                    alert("Bad");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

    function getStudents() {
        $.ajax
        ({
            type: "POST",
            data: {},
            url: 'GetStudents',
            success: function (serverData) {
                var result = serverData.result;
                if (result >= 0) {
                    var student_ids = serverData.student_ids.split(",");
                    var names = serverData.names.split(",");

                    var prevOption = $("#students").children();
                    for (var i = 0; i < prevOption.length; i++)
                    {
                        prevOption[i].remove();
                    }

                    var table = $("#students");
                    for (var i = 0; i < result; i++) {
                        var tr = $('<tr>');
                        var td1 = $('<td>');
                        var td2 = $('<td>');
                        td1.attr('data-id', student_ids[i]);
                        td1.text(names[i]);
                        tr.append(td1);
                        tr.append(td2);
                        table.append(tr);
                    }
                }
                else {
                    alert("Bad");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

});