var path = require('path');

var jsEntryPath = path.resolve(__dirname, 'src', 'index.js');
var buildPath = path.resolve(__dirname, 'public', 'build');

module.exports = {
  entry: [
    jsEntryPath
  ],
  output: {
    path: buildPath,
    filename: 'bundle.js'
  },

  module: {
    rules: [
        {
            test: /\.js$/,
            exclude: /(node_modules)/,
            use: {
              loader: 'babel-loader',
              options: {
                presets: ['@babel/preset-env']
              }
            }
          },
          {
              test: /\.css$/i,
              use: ['style-loader', 'css-loader'],
          }
    ]
  }
};