
var ObjectsSectionHandler = {

	renderRoomsSelect: function(){
		$.getJSON( "/data/rooms.json", function( data ) {
		    var template = $('#rooms-template').html();
			Mustache.parse(template);  
			var html = Mustache.to_html(template, {rooms: data});
			$('#rooms-select').html(html);

			template = $('#rooms-modal-template').html();
			Mustache.parse(template);  
			html = Mustache.to_html(template, {rooms: data});
			$('#rooms-modal-select').html(html);
		});
	},

	getObjectsByRoom: function(room, objects){
		var selectedObjs = [];
		var i = 0;

		$.each(objects, function( index, value ) {
		  	if(value.roomId == room){
		  		selectedObjs[i] = value;
		  		i++;
		  	}
		});

		return selectedObjs;
	},

	renderObjectsList: function(room){
		$.getJSON( "/data/objects.json", function( data ) {
			var objects = null;
			if(room == 0){
			    objects = data;
			}else{
				objects = ObjectsSectionHandler.getObjectsByRoom(room, data);
			}

			var template = '{{#objects}}<li><a href="/object/?id={{id}}">{{title}}<span class="label state {{turned}}"></span></a></li>{{/objects}}';
			Mustache.parse(template);  
			var html = Mustache.to_html(template, {objects: objects});
			$('#objects-list').html(html);
		});
	},

	initiateObjectsSection: function(){
		ObjectsSectionHandler.renderRoomsSelect();
    	ObjectsSectionHandler.renderObjectsList(0);

    	$('#rooms-select').on('change', function() {
		  	ObjectsSectionHandler.renderObjectsList($(this).val()); 
		});  

		$('#submit-add').on('click', function(){
			$.post('/add', $('#add-form').serialize());
			ObjectsSectionHandler.renderObjectsList($('#rooms-select').val()); 
			$('#close-modal').trigger('click');
		})	
	}
}

var DashboardSectionHandler = {
	
	myLineChart: null,

	myPieChart : null,
    
   	objectsChartRender : function (monthIndex) {
   		if(DashboardSectionHandler.myPieChart != null)
    		DashboardSectionHandler.myPieChart.destroy();
   		
   		$.getJSON( "/data/objects.json", function( data ) {
   			var colorPicker = ['#F7464A', '#46BFBD', '#FDB45C', '#F2D600', '#70B500', '#FF9F1A', '#EB5A46', '#C377E0', '#0079BF'];  
   			var chartData = [];

			$.each(data, function( index, value ) {  	
				chartData.push({
			        value: value.month[monthIndex].consumption,
			        color: colorPicker[Math.floor(Math.random() * colorPicker.length)],
			        highlight: "#d6dadc",
			        label: value.title
			    });
			});

			var pieCtx = $("#objects-chart").get(0).getContext("2d");
			DashboardSectionHandler.myPieChart = new Chart(pieCtx).Pie(chartData,null);
			$("#objects-chart").addClass("ready");
   		});	
    },

    generalChartRender : function(monthIndex){
    	if(DashboardSectionHandler.myLineChart != null)
    		DashboardSectionHandler.myLineChart.destroy();

    	$.getJSON( "/data/consumption.json", function( data ) {

    		$('#goal').html(data[monthIndex].goal);
    		$('#consumed').html(data[monthIndex].consumed);
    		$('#progress-bar').attr('style', 'width:'+data[monthIndex].consumed+'%');
    		
   			var generalChartData = {
		    	labels: data[monthIndex].labels,
			    datasets: [
			        {
			            label: "My Second dataset",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: data[monthIndex].consumption
			        }
			    ]
			};
			var lineCtx = $("#general-chart").get(0).getContext("2d");
			DashboardSectionHandler.myLineChart = new Chart(lineCtx).Line(generalChartData, {
			    bezierCurve: false
			});
			$("#general-chart").addClass("ready");
   		});	
    },

    initiateDashboardSection : function(){
    	
    	DashboardSectionHandler.generalChartRender($('#month-select').val());

		$('#general-charts').on('toggled', function (event, tab) {
			
			if($(tab).attr('id') == "panel-general" && !$("#general-chart").hasClass("ready")){
				DashboardSectionHandler.generalChartRender($('#month-select').val());
			}

			if($(tab).attr('id') == "panel-objects" && !$("#objects-chart").hasClass("ready")){
				DashboardSectionHandler.objectsChartRender($('#month-select').val());
			}
		
		});

		$('#month-select').on('change', function() {

			//Handle Line Chart
			if($('#panel-general').hasClass('active')){
				DashboardSectionHandler.generalChartRender($(this).val());
			}else{
				$("#general-chart").removeClass("ready");
			}

			//Handle Pie Chart
			if($('#panel-objects').hasClass('active')){
				DashboardSectionHandler.objectsChartRender($(this).val());	
			}else{
				$("#objects-chart").removeClass("ready");
			}
		  	 
		});
    }
}

var ObjectVisualizationHander = {

	myLineChart: null,

	getObjectById : function(objId, objects){
		var obj = null;		
		$.each(objects, function( index, value ) {
		  	if(value.id == objId){
		  		obj = value;	  	
		  	}
		});
		return obj;
	},

	objLineChartRender : function(objId){
		if(ObjectVisualizationHander.myLineChart != null)
    		ObjectVisualizationHander.myLineChart.destroy();

    	$.getJSON( "/data/objects.json", function( data ) {

    		var obj = ObjectVisualizationHander.getObjectById(objId, data);
    		$('#obj-title').html(obj.title);
    		$('#obj-desc').html(obj.description);
    		$('#switch').addClass(obj.turned);
    		$('#switch-obj-id').val(objId);
    		
    		var labels = [];
    		var cons = [];
    		var i = 0;
    		$.each(obj.month, function( index, value ) {
			  	labels[i] = value.title;
			  	cons[i] = value.consumption;
			  	i++;
			});

   			var objChartData = {
		    	labels: labels,
			    datasets: [
			        {
			            label: "Object Dataset",
			            fillColor: "rgba(151,187,205,0.2)",
			            strokeColor: "rgba(151,187,205,1)",
			            pointColor: "rgba(151,187,205,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(151,187,205,1)",
			            data: cons
			        }
			    ]
			};
			var lineCtx = $("#obj-chart").get(0).getContext("2d");
			ObjectVisualizationHander.myLineChart = new Chart(lineCtx).Line(objChartData, {
			    bezierCurve: false
			});
   		});	
    },

	initiateObjSection : function(){

		var SearchString = window.location.search.substring(1);
		var KeyValuePair = SearchString.split('=');
		var id = KeyValuePair[1];

		ObjectVisualizationHander.objLineChartRender(id);

		// switch toggle
		$('#switch').click(function() {
		
			var switchValue = null;

			if($(this).hasClass('off')){
				$(this).removeClass('off');
				$(this).addClass('on');
				switchValue = 'on';
			}else{
				$(this).removeClass('on');
				$(this).addClass('off');
				switchValue = 'off';
			}

			$('#switch-value').val(switchValue);
			$.post('/switch', $('#switch-form').serialize());
			ObjectsSectionHandler.renderObjectsList($('#rooms-select').val()); 

		});
	}

}