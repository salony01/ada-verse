const mongoose = require("mongoose");
const connect = mongoose.connect("mongodb://127.0.0.1:27017/authentication");

connect
  .then(() => {
    console.log("Database connected successfully");
  })
  .catch(() => {
    console.log("database can't connect");
  });
const watchHistorySchema = new mongoose.Schema({
  id: {
    type: String,
    required: false,
  },
  timestamp: {
    type: String,
    required: false,
  },
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
  last_watch: {
    type: String,
    default:'none',
  },
  watch_history: {
    type: [watchHistorySchema],
    required: false,
  },
});

const collection = new mongoose.model("users", LoginSchema);
module.exports = collection;
