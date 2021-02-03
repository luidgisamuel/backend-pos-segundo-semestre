const express = require('express')
const router = express.Router()
const jwt = require('jsonwebtoken')

const User = require('../model/user')

const { TOKEN_SECRET, TOKEN_EXPIRATION } = require('../config/token')

router.post('/', async (req, res) => {
    try {
        const { email, password } = req.body

        if (!(email && password)) {
            return res.status(400).send({
                error: 'E-mail ou senha não informados'
            })
        }

        const user = await User.findOne({ where: { email } })

        if (!user || password !== user.password) {
            return res.status(400).send({
                error: 'E-mail ou senha inválidos'
            })
        }

        const userToken = jwt.sign({
            id: user.id,
            email: user.email,
            type: user.type
        },
        TOKEN_SECRET, {
            expiresIn: TOKEN_EXPIRATION
        })

        return res.status(200).send({
            message: 'Usuário autenticado com sucesso',
            token: userToken
        })
    }
    catch (err) {
        return res.status(500).send({
            error: 'Falha no processo de autenticação'
        })
    }
})

module.exports = router