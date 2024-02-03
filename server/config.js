const mongoose = require("mongoose");
const connect = mongoose.connect("mongodb://127.0.0.1:27017/authentication");
const genresString ="Game show,News,Interview,Politics,Sitcom,Paranormal,Talk,Entertainment,Comedy,Newsmagazine,Basketball,Public affairs,Sports talk,Soap,Health,Football";

const genresArray = genresString.split(",");

const genreCountsObject = {};
genresArray.forEach((genre) => {
  genreCountsObject[genre.trim()] = { type: Number, default: 0 };
});
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
  genre_counts: {
    type: genreCountsObject,
    default: Object.fromEntries(genresArray.map((genre) => [genre.trim(), 0])),
  },
  watch_history: {
    type: [watchHistorySchema],
    required: false,
  },
});

const collection = new mongoose.model("users", LoginSchema);
module.exports = collection;
