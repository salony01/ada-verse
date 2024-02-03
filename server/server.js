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
