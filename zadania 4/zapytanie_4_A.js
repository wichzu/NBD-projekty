printjson(
    db.people1.aggregate([
        {
            $addFields: {
                bmi: {
                    $multiply: [
                        10000,
                        {
                            $divide: [
                                { $toDouble: "$weight" },
                                { $multiply: [{ $toDouble: "$height" }, { $toDouble: "$height" }] }
                            ]
                        }
                    ]
                }
            }
        },
        {
            $group: {
                _id: "$nationality",
                min_bmi: { $min: "$bmi" },
                avg_bmi: { $avg: "$bmi" },
                max_bmi: { $max: "$bmi" },
            }
        }
    ]).toArray()
);