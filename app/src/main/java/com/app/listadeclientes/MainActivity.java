package com.app.listadeclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.listadeclientes.adapter.ClienteAdapter;
import com.app.listadeclientes.dao.ClienteSqliteOpenHelper;
import com.app.listadeclientes.model.Cliente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declarando propriedades
    private ListView lst;
    private ClienteSqliteOpenHelper bd;
    ArrayList<Cliente> listaDeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criando uma instância da classe ClienteSqliteOpenHelper
        bd = new ClienteSqliteOpenHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Carregando na inicialização a lista de clientes
        lst = findViewById(R.id.listaClientesId);
        listaDeCliente = bd.pesquisarTodos();
        ClienteAdapter adapter = new ClienteAdapter(this,listaDeCliente);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EdtClienteActivity.class);
                intent.putExtra("ID", listaDeCliente.get(position).getId());
                startActivity(intent);
            }
        });
    }

    // Selecionando o menu cliente
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Atribuindo condição de acesso aos menus da aplicação
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_adicionar){
            Intent intent = new Intent(MainActivity.this, AddClienteActivity.class);
            startActivity(intent);
        }else if(id == R.id.menu_info){
            Intent intent = new Intent(MainActivity.this, InfoAppActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
