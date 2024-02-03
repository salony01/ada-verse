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


const fetch = require("node-fetch")
app.get("/api" ,(req,res)=>{
    res.json({"users":["1","2","3"]})
})


function sortByKey(array, key) {
    return array.sort(function(a, b) {
      var x = a[key];
      var y = b[key];
      return ((x > y) ? -1 : ((x < y) ? 1 : 0));
    });
  }


async function getUrl(dataId)
{
  let epgApiUrl = "https://cla-epg.lgads.tv/epg/ott?contentId="+dataId;
  let contentUrl;
  const response= await fetch(epgApiUrl, {
    method: 'GET',
    headers : {
        'Authorization':'Bearer 8BMgmyHiG'
    }
})

const epgData = await response.json();
contentUrl= epgData['result'][0]['links'][0]['urls']['web'];
  return contentUrl;
}

async function f(dataList, limit)
{
  let responseData=[]
  for(var i=0; i<limit; i++)
        {
            let dataId= dataList[i]['id'];
            let currContent=dataList[i];
            const contentUrl= await getUrl(dataId);
            currContent.contentUrl=contentUrl;
            responseData[i]=currContent;
        }      
        return responseData;

}

async function fun2(req)
{
  // let typee= "ott", show="movie", gen="Drama",limit=10;
    let typee= "ott", show=req.body.showType, gen=req.body.genre, limit=10;
    let reccApiUrl = "https://cla-recommendation.lgads.tv/recommendation/popular?type="+typee+"&genre="+gen+"&showType="+show+"&limit="+limit;
  const response= await fetch(reccApiUrl, {
    method: 'GET',
    headers : {
        'Authorization':'Bearer 8BMgmyHiG'
    }
})

const reccData = await response.json();
    let dataList= reccData["ott_recommendations"]["contents"];
        let responseData= await f(dataList,limit);
            return responseData;
}

async function fun3(req)
{
  // let typee= "ott", show="movie", gen="Drama",limit=10, ratSort=true, yearSort=false;
    let typee= "ott", show=req.body.showType, gen=req.body.genre, ratSort=req.body.ratSort, yearSort=req.body.yearSort, limit=10;
    let reccApiUrl = "https://cla-recommendation.lgads.tv/recommendation/popular?type="+typee+"&genre="+gen+"&showType="+show+"&limit="+limit;
  const response= await fetch(reccApiUrl, {
    method: 'GET',
    headers : {
        'Authorization':'Bearer 8BMgmyHiG'
    }
})

const reccData = await response.json();
    let dataList= reccData["ott_recommendations"]["contents"];
        let responseData= await f(dataList,limit);
            // console.log("fin", responseData)
            //sort
            if(ratSort)
            {
              responseData= sortByKey(responseData, 'rating')
            }
            if(yearSort)
            {
              responseData= sortByKey(responseData, 'releaseYear')
            }
            return responseData;
}

app.post("/genrePref", async (req,res)=>{

  let responseData=await fun2(req);
  let returning={}
        returning.ott_recommendations={};
        returning.ott_recommendations.contents=responseData;
    res.json(returning);
    

} )

app.post("/genreSort", async (req,res)=>{

  let responseData=await fun3(req);
  let returning={}
        returning.ott_recommendations={};
        returning.ott_recommendations.contents=responseData;
    res.json(returning);
    

} )


app.listen(5000,()=>{
    console.log("server started on port 5000")
})

