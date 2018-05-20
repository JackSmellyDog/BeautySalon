$(document).ready(function () {
    $('#datetimepicker').datetimepicker({
        format: 'YYYY-MM-DD HH:00',
        icons: {
            time: 'glyphicon glyphicon-time',
            date: 'glyphicon glyphicon-calendar',
            up: 'glyphicon glyphicon-chevron-up',
            down: 'glyphicon glyphicon-chevron-down',
            //previous: 'glyphicon glyphicon-chevron-left',
            previous: 'glyphicon glyphicon-backward',
            next: 'glyphicon glyphicon-chevron-right',
            today: 'glyphicon glyphicon-screenshot',
            clear: 'glyphicon glyphicon-trash',
            close: 'glyphicon glyphicon-remove'
        }
    });

    $('#carouselControls').carousel({
        interval: 2000
    });
});