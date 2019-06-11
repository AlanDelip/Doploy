const express = require('express');
const mysql = require('mysql');

const app = express();
let connection = mysql.createConnection({
    host: 'db',
    user: 'test',
    password: 'test',
    database: 'doploy'
});
connection.connect();

const PORT = 4000;

app.get('/', function (req, res) {
    connection.query('SELECT * FROM students', (err, rows, fields) => {
        if (err) throw err;
        let result = [];
        rows.forEach(row => {
            result.push(row);
        });
        res.json(result);
    })
});

app.listen(PORT, function () {
    console.log('Your node js server is running on PORT:', PORT);
});