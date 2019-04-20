import time
import json
from flask import Flask, flash, redirect, request, url_for, jsonify
from flask_sqlalchemy import SQLAlchemy

DBNAME = 'doploy'
DBUSER = 'testuser'
DBPASS = 'testpass'
DBHOST = 'db'
DBPORT = '5432'

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = \
    'postgresql+psycopg2://{user}:{passwd}@{host}:{port}/{db}'.format(
        user=DBUSER,
        passwd=DBPASS,
        host=DBHOST,
        port=DBPORT,
        db=DBNAME)
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.secret_key = 'doploy'

db = SQLAlchemy(app)


class students(db.Model):
    id = db.Column('student_id', db.Integer, primary_key=True)
    name = db.Column(db.String(100))
    city = db.Column(db.String(50))
    addr = db.Column(db.String(200))

    def __init__(self, name, city, addr):
        self.name = name
        self.city = city
        self.addr = addr


def database_initialization_sequence():
    db.create_all()
    test_rec = students(
        'John Doe',
        'Los Angeles',
        '123 Foobar Ave')

    db.session.add(test_rec)
    db.session.rollback()
    db.session.commit()


@app.route('/', methods=['GET', 'POST'])
def home():
    if request.method == 'POST':
        if not request.form['name'] or not request.form['city'] or not request.form['addr']:
            flash('Please enter all the fields', 'error')
        else:
            student = students(
                request.form['name'],
                request.form['city'],
                request.form['addr'])

            db.session.add(student)
            db.session.commit()
            flash('Record was succesfully added')
            return redirect(url_for('home'))
    result = students.query.all()
    all_rows = []
    for row in result:
        new_row = {"name": row.name, "city": row.city, "addr": row.addr}
        all_rows.append(new_row)
    return json.dumps(all_rows), 200


if __name__ == '__main__':
    dbstatus = False
    while dbstatus == False:
        try:
            db.create_all()
        except:
            time.sleep(2)
        else:
            dbstatus = True
    database_initialization_sequence()
    app.run(debug=True, host='0.0.0.0')