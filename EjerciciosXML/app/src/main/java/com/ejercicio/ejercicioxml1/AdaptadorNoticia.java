package com.ejercicio.ejercicioxml1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AdaptadorNoticia extends ArrayAdapter<Noticia> {
    public AdaptadorNoticia(@NonNull Context context, @NonNull List<Noticia> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Noticia noticia = getItem(position);
        ViewHolder holder;

        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.noticia_layout, null);
            holder = new ViewHolder();
            holder.titulo = item.findViewById(R.id.tvTituloNoticia);
            holder.texto = item.findViewById(R.id.tvTextoNoticia);
            holder.tvLink = item.findViewById(R.id.tvLink);
            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        ImageView img = holder.img;
//        img.setImageDrawable(localidad.getImagen());
        TextView titulo = holder.titulo;
        titulo.setText(noticia.getTitulo());
        TextView texto = holder.texto;
        texto.setText(noticia.getDescripcion());
        TextView tvLink = holder.tvLink;
        tvLink.setText(noticia.getLink());

//
//        item.setOnClickListener(v -> {
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(holder.link));
//            getContext().startActivity(i);
//        });

        return item;
    }

    private static class ViewHolder {
        ImageView img;
        TextView titulo, texto, tvLink;
    }



}
