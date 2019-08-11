package com.nullstop.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nullstop.R;
import com.nullstop.activity.model.Product;

import org.w3c.dom.Text;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.CustomViewHolder> {

    private Product[] dataList;
    private Context context;

    public CustomAdaptor(Context context, Product[] dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtProduct;
        TextView txtQty;
        TextView txtPrice;

        //private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtProduct = mView.findViewById(R.id.product);
            txtQty = mView.findViewById(R.id.quantity);
            txtPrice = mView.findViewById(R.id.price);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtProduct.setText("Name: " + dataList[position].product_name);
        holder.txtQty.setText("Quantity : " + Integer.toString(dataList[position].product_quantity));
        holder.txtPrice.setText("Price : " + Double.toString(dataList[position].product_cost * dataList[position].product_quantity));

    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }
}
