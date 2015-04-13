
var ChartsHandler = {
	
	lineCtx: null,

	pieCtx : null,
    
   	objectsChartRender : function () {
        console.log('hello');
   
		var data = [
		    {
		        value: 24,
		        color:"#F7464A",
		        highlight: "#FF5A5E",
		        label: "Air Conditioners"
		    },
		    {
		        value: 7,
		        color: "#46BFBD",
		        highlight: "#5AD3D1",
		        label: "Clothes Iron"
		    },
		    {
		        value: 7,
		        color: "#FDB45C",
		        highlight: "#FFC870",
		        label: "Hair Straightener"
		    },
		    {
		        value: 5,
		        color: "#F2D600",
		        highlight: "#FFC870",
		        label: "Light of Couple Bedroom"
		    },
		    {
		        value: 10,
		        color: "#70B500",
		        highlight: "#FFC870",
		        label: "Oven"
		    },
		    {
		        value: 3,
		        color: "#FF9F1A",
		        highlight: "#FFC870",
		        label: "Light of Kitchen"
		    },
		    {
		        value: 15,
		        color: "#EB5A46",
		        highlight: "#FFC870",
		        label: "TV"
		    },
		    {
		        value: 4,
		        color: "#C377E0",
		        highlight: "#FFC870",
		        label: "Light of Living Room"
		    },
		    {
		        value: 9,
		        color: "#0079BF",
		        highlight: "#FFC870",
		        label: "Shaving Machine"
		    }
		]

		pieCtx = $("#objects-chart").get(0).getContext("2d");
		var myPieChart = new Chart(pieCtx).Pie(data,null);

		$("#objects-chart").addClass("ready");
    },

    generalChartRender : function(){
    	var generalChartData = {
	    labels: [
	    		"03/01", 
    			"03/02",
    			"03/03",
    			"03/04",
    			"03/05",
    			"03/06",
    			"03/07",
    			"03/08",
    			"03/09",
    			"03/10",
    			"03/11",
    			"03/12",
    			"03/13",
    			"03/14",
    			"03/15",
    			"03/16",
    			"03/17",
    			"03/18",
    			"03/19",
    			"03/20",
    			"03/21",
    			"03/22",
    			"03/23",
    			"03/24", 
				"03/25",
				"03/26",
    			"03/27",
    			"03/28",
    			"03/29",
    			"03/30",
    			"03/31"
			],
		    datasets: [
		        {
		            label: "My Second dataset",
		            fillColor: "rgba(151,187,205,0.2)",
		            strokeColor: "rgba(151,187,205,1)",
		            pointColor: "rgba(151,187,205,1)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(151,187,205,1)",
		            data: [
		            	5, 
		            	2, 
		            	3, 
		            	1, 
		            	1,
		            	2, 
		            	5, 
		            	7, 
		            	1, 
		            	2,
		            	2, 
		            	1, 
		            	3, 
		            	7, 
		            	4,
		            	1, 
		            	2, 
		            	2, 
		            	1, 
		            	1,
		            	4, 
		            	8, 
		            	1, 
		            	2, 
		            	1,
		            	1, 
		            	2, 
		            	3, 
		            	6, 
		            	1,
		            	2
	            	]
		        }
		    ]
		};

		// Get context with jQuery - using jQuery's .get() method.
		lineCtx = $("#general-chart").get(0).getContext("2d");
		// This will get the first returned node in the jQuery collection.
		var myLineChart = new Chart(lineCtx).Line(generalChartData, {
	    	bezierCurve: false
		});
    }
}


$( document ).ready(function() {
    console.log( "ready!" );
    
    $(document).foundation();

	ChartsHandler.generalChartRender();

	$('#general-charts').on('toggled', function (event, tab) {
	    if($(tab).attr('id') == "panel-objects" && !$("#objects-chart").hasClass("ready")){
	    	ChartsHandler.objectsChartRender();
	    }
	});

});