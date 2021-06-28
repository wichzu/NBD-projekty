var map3 = function() {emit(this.job, null);};
var red3 = function(k, v) {};
db.people.mapReduce(map3,red3, {out: "jobs"})
printjson(db.jobs.find({},{id:1}).toArray())