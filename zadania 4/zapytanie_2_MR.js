var map2 = function() {
    for(var i=0;i<this.credit.length;i++)
        emit(this.credit[i].currency, parseInt(this.credit[i].balance));
};
var red2 = function(k, val) {
    return Array.sum(val);
};
db.people1.mapReduce(map2,red2,{out: "total"})
printjson(db.total.find({}).toArray())