var express = require('express');
var bodyParser = require('body-parser');
var jf = require('jsonfile');
var util = require('util');

var app = express();

app.use(express.static('.'));
app.use(bodyParser.urlencoded({
	extended: true
}));
app.use(bodyParser.json());

app.get('/', function (req, res) {
	console.log('GET /');
	res.sendFile(__dirname + '/index.html');
});

app.get('/object',function(req,res){
	console.log('GET /object');
  	res.sendFile(__dirname + '/object.html');
});

app.post('/switch', function(req, res) { 
	console.log('POST /switch');

    var id = req.body.id;
    var turned = req.body.turned;
    var index = id-1;

    var file = __dirname + '/data/objects.json';
	var objs = jf.readFileSync(file);
	objs[index].turned = turned;
	jf.writeFileSync(file, objs);
});

app.post('/add', function(req, res) { 
	console.log('POST /add', req.body);

	var file = __dirname + '/data/objects.json';
	var objs = jf.readFileSync(file);

	var newId = objs.length + 1;

	var newObj = {
		title: req.body.title,
	    serial: req.body.serial,
	    id: newId,
	    description: req.body.description,
	    turned: "off",
    	roomId: req.body.roomId,
    	month: [
  			{
    			title: "January",
        		consumption: "0"
      		},
      		{
        		title: "February",
        		consumption: "0"
      		},
      		{
        		title: "March",
        		consumption: "0"
      		}
    	]
	}

	objs[objs.length] = newObj;
	jf.writeFileSync(file, objs);
});

var server = app.listen(3000, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Listening at http://%s:%s', host, port);

});