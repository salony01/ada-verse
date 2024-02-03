const express=require('express')
const app=express()
const fetch = require("node-fetch")
app.get("/api" ,(req,res)=>{
    res.json({"users":["1","2","3"]})
})
app.listen(5000,()=>{
    console.log("server started on port 5000")
})

function sortByKey(array, key) {
    return array.sort(function(a, b) {
      var x = a[key];
      var y = b[key];
      return ((x > y) ? -1 : ((x < y) ? 1 : 0));
    });
  }

app.post("/movie", (req,res)=>{
    
    let typee= "ott", show="movie", gen="Drama", ratSort=true;
    let reccApiUrl = "https://cla-recommendation.lgads.tv/recommendation/popular?type="+typee+"&genre="+gen+"&showType="+show+"&limit=10";
    fetch(reccApiUrl, {
        method: 'GET',
        headers : {
            'Authorization':'Bearer 8BMgmyHiG'
        }
    } ).then( res => {
        return res.json()
    } )
    .then(data => {
        
        let dataList= data["ott_recommendations"]["contents"];
        if(ratSort)
        {
             dataList= sortByKey(dataList, 'rating')
        }
        res.json(dataList);
    }
    )
    .catch(error=>  console.log('Error while fetching from api' ));

} )


app.post("/mov", (req,res)=>{
    
    let typee= "ott", show="movie", gen="Drama";
    let reccApiUrl = "https://cla-recommendation.lgads.tv/recommendation/popular?type="+typee+"&genre="+gen+"&showType="+show+"&limit=1";
    let dataProcess;
    fetch(reccApiUrl, {
        method: 'GET',
        headers : {
            'Authorization':'Bearer 8BMgmyHiG'
        }
    } ).then( res => {
        return res.json()
    } )
    .then(data=> res.json(data)  )
    .catch(error=>  console.log('Error while fetching from api' ));

} )



