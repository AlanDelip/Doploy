const express = require('express');
const app = express();

const PORT = 4000;

const Pool = require('pg').Pool;
const pool = new Pool({
    user: 'testuser',
    password: 'testpass',
    host: 'db',
    database: 'doploy',
    port: 5432
});

app.get('/', function (req, res) {
    pool.query('SELECT * FROM users', (error, results) => {
        if (error) {
            throw error
        }
        res.status(200).json(results.rows)
    })
});

app.listen(PORT, function () {
    console.log('Your node js server is running on PORT:', PORT);
});
