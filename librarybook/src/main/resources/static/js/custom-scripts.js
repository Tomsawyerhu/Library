/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

$(function () {
    "use strict";
    var literature,science,education,military,art,histor;
    $.ajax({
        type:"get",
        url:"/admin/bookNum",
        dataType:"json",
        data:{},
        success:
            function(response) {
            response=eval(response);
            literature = response["literature"];
            science = response["science"];
            education = response["education"];
            military = response["military"];
            art = response["art"];
            histor = response["histor"];




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
                $.ajax({
                    type:"get",
                    url:"/admin/yearlyVisit",
                    dataType:"json",
                    data:{},
                    success:
                        function(response) {
                        response=eval(response);

                /* MORRIS BAR CHART
           -----------------------------------------*/
                Morris.Bar({
                    element: 'morris-bar-chart',
                    data: [{
                        y: '2014',
                        a: response["2014"]

                    }, {
                        y: '2015',
                        a: response["2015"]

                    }, {
                        y: '2016',
                        a: response["2016"]

                    }, {
                        y: '2017',
                        a: response["2017"]

                    }, {
                        y: '2018',
                        a: response["2018"]

                    }, {
                        y: '2019',
                        a: response["2019"]

                    }, {
                        y: '2020',
                        a: response["2020"]

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




                            /* MORRIS LINE CHART
                            ----------------------------------------*/
                            Morris.Line({
                                element: 'morris-line-chart',
                                data: [
                                    { y: '2014', a: response["2014"]},
                                    { y: '2015', a: response["2015"]},
                                    { y: '2016', a: response["2016"]},
                                    { y: '2017', a: response["2017"]},
                                    { y: '2018', a: response["2018"]},
                                    { y: '2019', a: response["2019"]},
                                    { y: '2020', a: response["2020"]},

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

                            $.ajax({
                                type: "get",
                                url: "/admin/yearlyRegist",
                                dataType: "json",
                                data: {},
                                success:
                                    function (response) {
                                        response = eval(response);


                                        /* MORRIS AREA CHART
                                        ----------------------------------------*/

                                        Morris.Area({
                                            element: 'morris-area-chart',
                                            data: [{
                                                period: '2014 ',
                                                regist: response["2014"],

                                            }, {
                                                period: '2015',
                                                regist: response["2015"],

                                            }, {
                                                period: '2016',
                                                regist: response["2016"],

                                            }, {
                                                period: '2017',
                                                regist: response["2017"],

                                            }, {
                                                period: '2018',
                                                regist: response["2018"],

                                            }, {
                                                period: '2019',
                                                regist: response["2019"],

                                            }, {
                                                period: '2020',
                                                regist: response["2020"],

                                            }],
                                            xkey: 'period',
                                            ykeys: ['regist'],
                                            labels: ['regist'],
                                            pointSize: 2,
                                            hideHover: 'auto',
                                            pointFillColors: ['#ffffff'],
                                            pointStrokeColors: ['black'],
                                            lineColors: ['#22a7f0', '#1abc9c'],
                                            resize: true
                                        });
                                    }
                            });


                        }
                });



            }
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
