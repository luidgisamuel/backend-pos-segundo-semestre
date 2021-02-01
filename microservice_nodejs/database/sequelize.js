const { Sequelize } = require('sequelize')

const {
    DB_NAME,
    DB_USER,
    DB_PASSWORD,
    DB_HOST,
    DB_PORT,
    DB_DIALECT,
    DB_FREEZE_TABLE_NAME,
    DB_TIMESTAMPS
} = require('../config/database')

const sequelize = new Sequelize(DB_NAME, DB_USER, DB_PASSWORD, {
    host: DB_HOST,
    port: DB_PORT,
    dialect: DB_DIALECT,
    define: {
        freezeTableName: DB_FREEZE_TABLE_NAME,
        timestamps: DB_TIMESTAMPS
    }
})

module.exports = sequelize