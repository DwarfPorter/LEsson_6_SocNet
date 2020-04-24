package ru.geekbrains.socnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataSource();
    }

    private void initDataSource() {
        SocialDataSource sourceData = new SocSourceBuilder()
                .setResources(getResources())
                .build();

        final SocialChangableSource sourceChangableData = new SocChangableSource(sourceData);

        final SocnetAdapter socnetAdapter = initList(sourceChangableData);

        Button add = findViewById(R.id.buttonAdd);
        Button delete = findViewById(R.id.buttonDel);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceChangableData.add();
                socnetAdapter.notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceChangableData.delete();
                socnetAdapter.notifyDataSetChanged();
            }
        });
    }

    private SocnetAdapter initList(SocialDataSource data) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        SocnetAdapter adapter = new SocnetAdapter(data);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        // Установим слушателя
        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,
                        String.format("Позиция - %d", position),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return adapter;
    }
}
