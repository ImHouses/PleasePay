const Sequelize = require("sequelize");

const sequelize = new Sequelize("pleasepaytest", "jcasas", "please_pay_123", {
    host: "localhost",
    dialect: "postgres",
    port: 5432
});

module.exports = sequelize;