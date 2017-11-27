var express = require('express');
var app = express();
var path = require('path');
var public = __dirname + "/";

app.get('/', function(req, res) {
    res.sendFile(path.join(public + "index.html"));
});

app.get('/sitemap', function(req, res) {
    res.sendFile(path.join(public + "sitemap.xml"));
});

app.get('/google60902b301fbc68c0.html', function(req, res) {
    res.sendFile(path.join(public + "google60902b301fbc68c0.html"));
});

app.use('/', express.static(public));

app.listen(process.env.PORT || 3000);