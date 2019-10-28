package com.juancasasm.models

import org.joda.time.LocalTime

data class Account(
    val id: Int,
    val name: String,
    val description: String,
    val userId: Int,
    val currencyId: Int,
    val billingCycle: String,
    val nextBillingDate: LocalTime,
    val prevBillingDate: LocalTime,
    val totalPendingPayments: Int,
    val totalAmountOfPending: Int,
    val totalAmountOfPaid: Int,
    val totalAmountOfNotApproved: Int
)