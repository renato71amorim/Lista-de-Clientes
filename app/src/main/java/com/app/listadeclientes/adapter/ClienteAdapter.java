package com.app.listadeclientes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.listadeclientes.R;
import com.app.listadeclientes.model.Cliente;

import java.util.ArrayList;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    // Declarando propriedades
    private final Context context;
    private final ArrayList<Cliente> elementos;

    // Construtor com parâmetros
    public ClienteAdapter(Context context, ArrayList<Cliente> lstClientes) {
        super(context, R.layout.lista_personalizada, lstClientes);
        this.context = context;
        this.elementos = lstClientes;
    }

    // Configurando o arquivo lista_personalizada.xml em uma view
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.lista_personalizada, parent, false);

        TextView nome = rowView.findViewById(R.id.txtNomeEdtId);
        TextView email = rowView.findViewById(R.id.txtEmailEdtId);
        TextView telefone = rowView.findViewById(R.id.txtTelefoneEdtId);

        nome.setText(elementos.get(position).getNome());
        email.setText(elementos.get(position).getEmail());
        telefone.setText(elementos.get(position).getTelefone());

        return rowView;
    }
}
