package com.HKSHOPU.hk.data.bean

import com.google.gson.annotations.SerializedName

class AddValueHistoryBean {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("order_status")
    var order_status: String = ""

    @SerializedName("wallet_id")
    var wallet_id: String = ""

    @SerializedName("change")
    var change: String = ""

    @SerializedName("action")
    var action: String = ""

    @SerializedName("status")
    var status: Int = -999

    @SerializedName("order_number")
    var order_number: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("created_at")
    var created_at: String = ""


}