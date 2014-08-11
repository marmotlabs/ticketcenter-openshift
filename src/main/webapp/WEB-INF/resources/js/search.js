// On DOM load, change the date format of the jQuery calendars to match the date format of the Spring conversion service
$(document).ready(function() {
    $("#datepicker-between").datepicker({
        dateFormat: 'dd-mm-yy'
    });
});

$(document).ready(function() {
    $("#datepicker-and").datepicker({
        dateFormat: 'dd-mm-yy'
    });
});