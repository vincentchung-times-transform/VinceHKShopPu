package com.HKSHOPU.hk.ui.main.payment.activity

import android.content.Intent
import android.os.Bundle
import com.HKSHOPU.hk.Base.BaseActivity
import com.HKSHOPU.hk.component.*
import com.HKSHOPU.hk.databinding.*

import com.HKSHOPU.hk.ui.main.seller.shop.activity.ShopmenuActivity
import com.HKSHOPU.hk.utils.rxjava.RxBus
import com.tencent.mmkv.MMKV

//import kotlinx.android.synthetic.main.activity_main.*

class FpsPayAuditActivity : BaseActivity() {

    private lateinit var binding: ActivityFpspayauditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFpspayauditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initClick()
    }

    private fun initView() {

    }

    private fun initClick() {
        binding.ivBackClick.setOnClickListener {
            finish()
        }

        binding.ivGopurchaselist.setOnClickListener {

            MMKV.mmkvWithID("myOderList").putString("myOderList", "PurchaseListFragment")

            RxBus.getInstance().post(EventShopmenuToSpecificPage(1))
            RxBus.getInstance().post(EventRefreshUserInfo())
            RxBus.getInstance().post(EventRefreshShoppingCartItemCount())

            val intent = Intent(this, ShopmenuActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding.ivGohomepage.setOnClickListener {
            val intent = Intent(this, ShopmenuActivity::class.java)
            startActivity(intent)
            RxBus.getInstance().post(EventShopmenuToSpecificPage(0))
            finish()
        }
    }

    public override fun onDestroy() {
        // Stop service when done
        super.onDestroy()
    }

}