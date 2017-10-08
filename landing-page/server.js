var express = require('express');
var app = express();
var path = require('path');
var public = __dirname + "/";

app.get('/', function(req, res) {
    res.sendFile(path.join(public + "index.html"));
});

app.use('/', express.static(public));

app.listen(process.env.PORT || 3000);