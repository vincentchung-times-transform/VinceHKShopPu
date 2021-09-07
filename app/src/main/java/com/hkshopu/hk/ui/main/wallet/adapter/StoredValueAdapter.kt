package com.HKSHOPU.hk.ui.main.wallet.adapter


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.HKSHOPU.hk.R
import com.HKSHOPU.hk.data.bean.AddValueHistoryBean
import com.HKSHOPU.hk.utils.extension.inflate
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class StoredValueAdapter (): RecyclerView.Adapter<StoredValueAdapter.TopProductLinearHolder>(){
    private var mData: ArrayList<AddValueHistoryBean> = ArrayList()
//    private var newData: ArrayList<ProductSearchBean> = ArrayList()
    var itemClick : ((id: String) -> Unit)? = null

    fun setData(list : ArrayList<AddValueHistoryBean>){
        list?:return
        mData = list
        notifyDataSetChanged()
    }
    fun add(list: ArrayList<AddValueHistoryBean>) {
        list?:return
        mData.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductLinearHolder {
        val v = parent.context.inflate(R.layout.item_stored_value_record,parent,false)

        return TopProductLinearHolder(v)
    }
    override fun getItemCount(): Int {
        return mData.size
    }

    fun removeAt(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
    }
    fun clear() {
        val size = mData.size
        mData.clear()
        notifyItemRangeRemoved(0, size)
    }
    override fun onBindViewHolder(holder: TopProductLinearHolder, position: Int) {
        val item = mData.get(position)
        holder.bindShop(item)
    }
    inner class TopProductLinearHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iv_product_icon = itemView.find<ImageView>(R.id.iv_product_icon)
        val tv_date = itemView.find<TextView>(R.id.tv_date)
        val tv_stored_value = itemView.find<TextView>(R.id.tv_stored_value)
        var tv_add_value_order_status = itemView.find<TextView>(R.id.tv_add_value_order_status)

        fun bindShop(storedValueBean : AddValueHistoryBean){

            when(storedValueBean.change.toString()){
                "788"->{
                    iv_product_icon.setImageResource(R.mipmap.petit_podium)
                }
                "398"->{
                    iv_product_icon.setImageResource(R.mipmap.petit_coins)
                }
                "158"->{
                    iv_product_icon.setImageResource(R.mipmap.petit_coin)
                }
            }

            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val actual_finished_at: Date = format.parse(storedValueBean.created_at.toString())
            var actual_finished_at_result =  SimpleDateFormat("dd/MM/yyyy").format(actual_finished_at)
            tv_date.setText(actual_finished_at_result.toString())

            tv_stored_value.setText(storedValueBean.change.toString())
            when(storedValueBean.order_status.toString()){
                "Pending Payment"->{
                    tv_add_value_order_status.visibility = View.VISIBLE
                    tv_add_value_order_status.setText("繼續付款")
                    tv_add_value_order_status.setTextColor(ContextCompat.getColor(itemView.context,R.color.hkshop_color))
                }
                "Recharging"->{
                    tv_add_value_order_status.visibility = View.VISIBLE
                    tv_add_value_order_status.setText("審查中")
                   tv_add_value_order_status.setTextColor(ContextCompat.getColor(itemView.context,R.color.purple_7B61FF))
                }
                "Charge Failed"->{
                    tv_add_value_order_status.visibility = View.VISIBLE
                    tv_add_value_order_status.setText("付款失敗")
                    tv_add_value_order_status.setTextColor(ContextCompat.getColor(itemView.context,R.color.bright_red))
                }
                "Charge Completed"->{
                    tv_add_value_order_status.visibility = View.GONE
                }
            }
            itemView.setOnClickListener {

            }

        }
    }
}