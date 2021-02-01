const { DataTypes } = require('sequelize')
const sequelize = require('../database/sequelize')

const User = sequelize.define('user', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        allowNull: false,
        autoIncrement: true
    },
    email: {
        type: DataTypes.STRING(60),
        allowNull: false,
        unique: true
    },
    password: {
        type: DataTypes.STRING(60),
        allowNull: false
    },
    type: {
        type: DataTypes.STRING(15),
        allowNull: false
    }
})

module.exports = User