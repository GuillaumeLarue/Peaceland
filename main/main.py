import datetime
import json
import random

import numpy as np

class Message:
    timestamp = datetime.datetime.now()
    n = 1
    lat_min = -90.
    lat_max = 90.
    lon_min = -180.
    lon_max = 180.
    peacewatcher_long, peacewatcher_lat = np.random.uniform(lat_min, lat_max, n), np.random.uniform(lon_min, lon_max, n)
    citoyen_id = random.randint(0, 8000000)
    citoyen_peacescore = random.randint(0, 100)
    # words_arounds = random.randint(0, 100)

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    # create object
    laptop1 = Message()
    laptop1.timestamp = datetime.datetime.now()
    laptop1.citoyen_id = 13241234124
    laptop1.citoyen_peacescore = 13
    # laptop1.words_arounds = 42
    n = 1
    lat_min = -90.
    lat_max = 90.
    lon_min = -180.
    lon_max = 180.
    laptop1.peacewatcher_long = np.random.uniform(lat_min, lat_max, n).tolist()[0]
    laptop1.peacewatcher_lat = np.random.uniform(lon_min, lon_max, n).tolist()[0]

    # convert to JSON string
    jsonStr = json.dumps(laptop1.__dict__, indent=4, sort_keys=True, default=str, )

    # print json string
    print(jsonStr)