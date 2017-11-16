<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/components/newAmcharts/style.css" type="text/css">
        <script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/serial.js" type="text/javascript"></script>

        <script>
            var chart;
            //var graph;

            var chartData = ${ipAcessList};


            AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.addTitle("登录IP次数统计图", 16);
                chart.dataProvider = chartData;
               // chart.marginLeft = 10;
                chart.categoryField = "IP";
                //chart.dataDateFormat = "YYYY";

                // listen for "dataUpdated" event (fired when chart is inited) and call zoomChart method when it happens
                //chart.addListener("dataUpdated", zoomChart);

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                //categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
                //categoryAxis.minPeriod = "YYYY"; // our data is yearly, so we set minPeriod to YYYY
                categoryAxis.title ="IP";
             	categoryAxis.dashLength = 3;
                categoryAxis.minorGridEnabled = true;
                categoryAxis.minorGridAlpha = 0.1;//0.1 
                categoryAxis.gridAlpha = 0.1;
                categoryAxis.minorGridAlpha = 0.1;
                categoryAxis.axisAlpha = 0;
                categoryAxis.minorGridEnabled = true;
                categoryAxis.inside = true;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.title = "访问次数";
                categoryAxis.dashLength = 3;
                valueAxis.tickLength = 0;
                valueAxis.axisAlpha = 0;
                valueAxis.showFirstLabel = false;
                valueAxis.showLastLabel = false;
                chart.addValueAxis(valueAxis);

                // GRAPH
                graph = new AmCharts.AmGraph();
                graph.dashLength = 3;
                graph.lineColor = "#00CC00";
                graph.valueField = "AT";
                graph.dashLength = 3;
                graph.bullet = "round";
                graph.balloonText = "[[category]]<br><b><span style='font-size:14px;'>value:[[AT]]</span></b>";
                chart.addGraph(graph);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.valueLineEnabled = true;
                chartCursor.valueLineBalloonEnabled = true;
                chart.addChartCursor(chartCursor);

                // SCROLLBAR
                var chartScrollbar = new AmCharts.ChartScrollbar();
                chart.addChartScrollbar(chartScrollbar);

                // HORIZONTAL GREEN RANGE
                var guide = new AmCharts.Guide();
                guide.value = 10;
                guide.toValue = 20;
                guide.fillColor = "#00CC00";
                guide.inside = true;
                guide.fillAlpha = 0.2;
                guide.lineAlpha = 0;
                valueAxis.addGuide(guide);
                
               

                chart.creditsPosition = "top-right";

                // WRITE
                chart.write("chartdiv");
            });

            // this method is called when chart is first inited as we listen for "dataUpdated" event
            function zoomChart() {
                // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
                chart.zoomToDates(new Date(1972, 0), new Date(1984, 0));
            }
        </script>
    </head>

    <body>
        <div id="chartdiv" style="width:100%; height:400px;"></div>
    </body>

</html>