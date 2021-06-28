  
printjson(
    db.people1.mapReduce(
		function() {
			emit(this.sex, { weight: parseFloat(this.weight), height: parseFloat(this.height) }); 
		},
		function(keys, vals) { 
			let weights = vals.map(v => v.weight);
			let heights = vals.map(v => v.height);
			
			const avg = arr => arr.reduce((a, b) => a + b) / arr.length;
			
			return {
				weight: avg(weights),
				height: avg(heights)
			}; 
		},
		{
			out: { inline: 1 }
		}
	)
)