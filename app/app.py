from typing import List, Dict
from flask import Flask
import mysql.connector
import json

app = Flask(__name__)


def students() -> List[Dict]:
    config = {
        'user': 'test',
        'password': 'test',
        'host': 'db',
        'port': '3306',
        'database': 'userdb'
    }
    connection = mysql.connector.connect(**config)
    cursor = connection.cursor()
    cursor.execute('SELECT * FROM students')
    results = [{name: city} for (name, city) in cursor]
    cursor.close()
    connection.close()

    return results


@app.route('/')
def index() -> str:
    return json.dumps({'students': students()})


if __name__ == '__main__':
    app.run(host='0.0.0.0')
