printjson(db.people1.aggregate([{$group : {_id: "$sex","AvgWeight": {"$avg": "$weight"},"AvgHeight": {"$avg": "$height"}}},]).toArray())
