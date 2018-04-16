$(document).ready(function(){
    var listStud = new Array();;
    var listAttendance = new Array();;

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
                    for (var i = 0; i < prevOption.length; i++) {
                        prevOption[i].remove();
                    }

                    var table = $("#students");
                    for (var i = 0; i < result; i++) {
                        var tr = $('<tr>');
                        var td1 = $('<td>');
                        var td2 = $('<td>');
                        td1.attr('data-id', student_ids[i]);
                        td1.text(names[i]);
                        var check  = $('<input>');
                        check.attr('type', 'checkbox');
                        td2.append(check);
                        tr.append(td1);
                        tr.append(td2);
                        table.append(tr);

                        listStud[i] = td1;
                        listAttendance[i] = check;
                    }
                    $('#schedules').trigger('change');
                } else {
                    alert("Bad");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

    $("#upd_attendance").click(function() {
        var schedules =  $("#schedules").val();
        var number =  $("#number").val();
        var count = listStud.length;
        var resultStudent = "";
        for (var i = 0; i < count; i++) {
            if (listAttendance[i].prop("checked")) {
                if (resultStudent == "") {
                    resultStudent += listStud[i].attr('data-id');
                } else {
                    resultStudent += "," + listStud[i].attr('data-id');
                }
            }
        }
        $.ajax
        ({
            type: "POST",
            data: {'schedules': schedules, 'number': number, 'resultStudent': resultStudent},
            url: 'UpdAttendance',
            success: function (serverData) {
                alert('Данные были успешно сохранены');
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $("#schedules").change(function() {
        getAttendance();
    });

    $("#number").change(function() {
        getAttendance();
    });

    function getAttendance() {
        var schedules =  $("#schedules").val();
        var number =  $("#number").val();
        $.ajax
        ({
            type: "POST",
            data: {'schedules': schedules, 'number': number},
            url: 'GetAttendance',
            success: function (serverData) {
                var result = serverData.result.split(",");
                var count1 = result.length;
                var count2 = listStud.length;

                for (var j = 0; j < count2; j++) {
                    listAttendance[j].prop('checked', false);
                }

                for (var i = 0; i < count1; i++) {
                    for (var j = 0; j < count2; j++) {
                        if (listStud[j].attr('data-id') == result[i]) {
                            listAttendance[j].prop('checked', true);
                            break;
                        }
                    }
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

});