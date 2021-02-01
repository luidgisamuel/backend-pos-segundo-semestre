const express = require('express')
const bodyParser = require('body-parser')

const { SERVER_PORT } = require('./config/server')

const authController = require('./controller/auth')

const app = express()

app.use(bodyParser.json())

app.use('/auth', authController)

app.listen(SERVER_PORT, () => {
    console.log(`NodeJS microservice running on port ${SERVER_PORT}...`)
})