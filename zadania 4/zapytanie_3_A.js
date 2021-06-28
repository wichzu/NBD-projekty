printjson(db.people1.aggregate([{$group:{_id: "$job"}}]).toArray())
