package com.casterio.materialmotionresponsive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setAdapter(new ItemArrayAdapter());
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {
        private ArrayList<Item> adapItemList;
        int numOfItems = 15;

        public ItemArrayAdapter() {
            adapItemList = new ArrayList<>(numOfItems);
            for(int i=0; i< numOfItems; i++) {
                adapItemList.add(new Item("Item " + i));
            }
        }

        @Override
        public int getItemCount() {
            return adapItemList == null ? 0 : adapItemList.size();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
            TextView item = holder.item;
            item.setText(adapItemList.get(listPosition).getName());
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView item;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                item = (TextView) itemView.findViewById(R.id.row_item);
            }
            @Override
            public void onClick(View view) {
                Log.d(getLocalClassName(), "onClick() returned " + getLayoutPosition() + " | " + adapItemList.get(getLayoutPosition()).getName());
            }
        }
    }

    private class Item {

        private String name;

        public Item(String n) {
            name = n;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
