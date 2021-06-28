db.people1.mapReduce(
    function () {
        this.credit.forEach(cc => {
            emit(cc.currency, parseInt(cc.balance));
        });
    },
    function (key, values) {
        const SumOfFunds = Array.sum(values);
        const AverageOfFunds = SumOfFunds / values.length;

        return { SumOfFunds, AverageOfFunds};
    },
    {
        query: {
            nationality: 'Poland',
            sex: 'Female'
        },
        out: 'PolishFemales'
    }
);
printjson(db.PolishFemales.find().toArray())