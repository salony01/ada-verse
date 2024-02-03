const mongoose = require("mongoose");
const connect = mongoose.connect("mongodb://127.0.0.1:27017/authentication");

connect
  .then(() => {
    console.log("Database connected successfully");
  })
  .catch(() => {
    console.log("database can't connect");
  });
const LoginSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  password: {
    type: String,
    required: true,
  },
});

const collection = new mongoose.model("users", LoginSchema);
module.exports = collection;
