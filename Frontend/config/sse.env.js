'use strict'
const { merge } = require('webpack-merge');
const DevENV = require('./prod.env')

module.exports = merge(DevENV, {
  NODE_ENV: '"development"',
  API_URL: 'http://localhost/SeDev.Api/api'
  //API_URL: 'http://sedev.ch/api'
})
