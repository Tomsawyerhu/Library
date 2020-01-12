/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";
    var literature,science,education,military,art,histor;
    $.get("/admin/bookNum",{},function (response) {
        literature=response.literature;
        science=response.science;
        education=response.education;
        military=response.military;
        art=response.art;
        histor=response.histor;
    });
    var mainApp = {

        initFunction: function () {
            /*MENU
            ------------------------------------*/
            $('#main-menu').metisMenu();

            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });

            /* MORRIS BAR CHART
			-----------------------------------------*/
            Morris.Bar({
                element: 'morris-bar-chart',
                data: [{
                    y: '2014',
                    a: 500

                }, {
                    y: '2015',
                    a: 1005

                }, {
                    y: '2016',
                    a: 520

                }, {
                    y: '2017',
                    a: 789

                }, {
                    y: '2018',
                    a: 654

                }, {
                    y: '2019',
                    a: 1000

                }, {
                    y: '2020',
                    a: 678

                }],
                xkey: 'y',
                ykeys: ['a'],
                labels: ['Visits'],
                barColors: [
                    '#22a7f0','#1abc9c',
                    '#A8E9DC'
                ],
                hideHover: 'auto',
                resize: true
            });



            /* MORRIS DONUT CHART
			----------------------------------------*/
            Morris.Donut({
                element: 'morris-donut-chart',
                data: [{
                    label: "literature",
                    value: literature
                }, {
                    label: "science",
                    value: science
                }, {
                    label: "art",
                    value: art
                }, {
                    label: "education",
                    value: education
                }, {
                    label: "military",
                    value: military
                }, {
                    label: "history",
                    value: histor
                }],
                colors: [
                    '#22a7f0','#1abc9c',
                    '#A8E9DC','#02C874',
                    '#AAAAFF','#80FFFF'
                ],
                resize: true
            });

            /* MORRIS AREA CHART
			----------------------------------------*/

            Morris.Area({
                element: 'morris-area-chart',
                data: [{
                    period: '2014 ',
                    regist: 2666,

                }, {
                    period: '2015',
                    regist: 2778,

                }, {
                    period: '2016',
                    regist: 4912,

                }, {
                    period: '2017',
                    regist: 3767,

                }, {
                    period: '2018',
                    regist: 6810,

                }, {
                    period: '2019',
                    regist: 5670,

                }, {
                    period: '2020',
                    regist: 4820,

                }],
                xkey: 'period',
                ykeys: ['regist'],
                labels: ['regist'],
                pointSize: 2,
                hideHover: 'auto',
                pointFillColors:['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors:['#22a7f0','#1abc9c'],
                resize: true
            });

            /* MORRIS LINE CHART
			----------------------------------------*/
            Morris.Line({
                element: 'morris-line-chart',
                data: [
                    { y: '2014', a: 50},
                    { y: '2015', a: 165},
                    { y: '2016', a: 150},
                    { y: '2017', a: 175},
                    { y: '2018', a: 80},
                    { y: '2019', a: 90},
                    { y: '2020', a: 100},

                ],


                xkey: 'y',
                ykeys: ['a'],
                labels: ['Visits'],
                fillOpacity: 0.6,
                hideHover: 'auto',
                behaveLikeLine: true,
                resize: true,
                pointFillColors:['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors:['gray','#1abc9c']

            });


            $('.bar-chart').cssCharts({type:"bar"});
            $('.donut-chart').cssCharts({type:"donut"}).trigger('show-donut-chart');
            $('.line-chart').cssCharts({type:"line"});

            $('.pie-thychart').cssCharts({type:"pie"});


        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction();
        $("#sideNav").click(function(){
            if($(this).hasClass('closed')){
                $('.navbar-side').animate({left: '0px'});
                $(this).removeClass('closed');
                $('#page-wrapper').animate({'margin-left' : '260px'});

            }
            else{
                $(this).addClass('closed');
                $('.navbar-side').animate({left: '-260px'});
                $('#page-wrapper').animate({'margin-left' : '0px'});
            }
        });
    });

}(jQuery));
