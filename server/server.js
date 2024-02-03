

const express = require("express");
const path = require("path");
const bcrypt = require("bcrypt");
const collection = require("./config");
const app = express();
// app.set("view engine", "ejs");
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
// app.set('view engine','ejs');
app.get("/", (req, res) => {
  res.json({ "CORRECT": ["login please" ]})
});
app.get("/signup", (req, res) => {
  res.json({ "CORRECT": ["signup please"]})
});
app.get("/api", (req, res) => {
  res.json({"uers":["1"]})
});

app.post("/signup", async (req, res) => {
  const data = {
    name: req.body.username,
    password: req.body.password,
  };
  const existingUser = await collection.findOne({ name: data.name });
  if (existingUser) {
    res.json({ "CORRECT": "please login woth another name" });
  } else {
    const saltRounds=10;
    const hashedPassword=await bcrypt.hash(data.password,saltRounds);
    data.password=hashedPassword;
    const userdata = await collection.insertMany(data);
    console.log(userdata);
    res.json(userdata);
  }
});
app.post("/login",async(req,res)=>{
  try{
    const check=await collection.findOne({name:req.body.username});
    if(!check){
      return res.json({ "CORRECT": "username can't be found" });
    }
    const isPasswordMatch=await bcrypt.compare(req.body.password,check.password);
    if(isPasswordMatch){
      return res.json({"CORRECT":"yes"})
    }
    else{
      return res.json({ "CORRECT": "nob" })
    }
  }
  catch{
    res.json({ "CORRECT": "noo" })
  }
})
const port = 5000;
app.listen(5000, () => {
  console.log("servr started on port 5000");
});


// const express=require('express')
// const app=express()
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


