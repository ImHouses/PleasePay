package com.juancasasm.models.dao

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Accounts : Table() {
    val name: Column<String> = varchar("account_name", 50)
    val description: Column<String> = varchar("account_description", 500)
    val currencyId: Column<Int> = integer("currency_id")
    val userId: Column<Int> = integer("user_id")
    val billingCycle: Column<Int> = integer("billing_cycle")
    val nextBillingDate: Column<DateTime> = date("next_billing_date")
    val prevBillingDate: Column<DateTime> = date("prev_billing_date")
    val totalPendingPayments: Column<Int> = integer("total_pending_payments")
    val totalAmountOfPending: Column<Int> = integer("total_amount_of_pending")
    val totalAmountOfPaid: Column<Int> = integer("total_amount_of_paid")
    val totalAmountOfNotApproved: Column<Int> = integer("total_amount_of_not_approved")
}