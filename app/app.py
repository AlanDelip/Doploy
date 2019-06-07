from flask import Flask
from pymongo import MongoClient
import datetime

app = Flask(__name__)

client = MongoClient('mongodb://db:27017/')


@app.route('/')
@app.route('/index')
@app.route('/home')
def home():
    db = client.test_database
    collection = db.test_collection
    post = {"author": "Mike",
            "text": "My first blog post!",
            "tags": ["mongodb", "python", "pymongo"],
            "date": datetime.datetime.utcnow()}
    collection.insert_one(post)

    list = []
    for post in collection.find():
        list.append(post)

    return str(list)


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
